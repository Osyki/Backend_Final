//Keith Geneva
//Jonathan Rubio
package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.UserDao;
import com.cscloi.card_db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultUserService implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    public List<User> all(int limit) {
        if (limit <= 0) {
            return Collections.emptyList();
        }
        if (limit > 1000) {
            limit = 1000;
        }

        return dao.all(limit)
                .collect(Collectors.toList());
    }

    @Override
    public User get(String userID) {
        if ((userID == null) || (userID.isEmpty())) {
            return null;
        }

        Optional<User> model = dao.get(userID);
        if (model.isPresent()) {
            return model.get();
        }

        return null;
    }

    @Override
    public User create(User input) {
        if (input == null) {
            return null;
        }

        if (input.isValid()) {
            Optional<User> model = dao.save(input);
            if (model.isPresent()) {
                return model.get();
            }
        }

        return null;
    }

    @Override
    public User update(String id, User input) {
        User existing = get(id);
        if (existing == null) {
            return null;
        }

        Optional<User> model = dao.save(id, input);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public User delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return null;
        }

        Optional<User> existing = dao.delete(id);
        if (existing.isPresent()) {
            return existing.get();
        }
        return null;
    }
}
