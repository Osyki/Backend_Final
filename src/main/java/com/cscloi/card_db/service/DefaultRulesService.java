//Keith Geneva
package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.DefaultRulesDao;
import com.cscloi.card_db.dao.RulesDao;
import com.cscloi.card_db.entity.Rules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultRulesService implements RulesService {

    @Autowired
    private RulesDao dao;

    @Override
    public List<Rules> all(int limit) {
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
    public Rules get(String rulesID) {
        if ((rulesID == null) || (rulesID.isEmpty())) {
            return null;
        }

        Optional<Rules> model = dao.get(rulesID);
        if (model.isPresent()) {
            return model.get();
        }

        return null;
    }

    @Override
    public Rules of_a_Game(String gameID) {
        if ((gameID == null) || (gameID.isEmpty())) {
            return null;
        }

        Optional<Rules> model = dao.get_of_a_game(gameID);
        if (model.isPresent()) {
            return model.get();
        }

        return null;
    }

    @Override
    public Rules create(Rules input) {
        if (input == null) {
            return null;
        }

        if (input.isValid()) {
            Optional<Rules> model = dao.save(input);
            if (model.isPresent()) {
                return model.get();
            }
        }

        return null;
    }

    @Override
    public Rules update(String rulesID, Rules input) {
            Rules existing = get(rulesID);
            if (existing == null) {
                return null;
            }

            Optional<Rules> model = dao.save(rulesID, input);
            if (model.isPresent()) {
                return model.get();
            }
            return null;
        }

        @Override
        public Rules delete (String rulesID){
            if ((rulesID == null) || (rulesID.isEmpty())) {
                return null;
            }

            Optional<Rules> existing = dao.delete(rulesID);
            if (existing.isPresent()) {
                return existing.get();
            }
            return null;
        }
    }
