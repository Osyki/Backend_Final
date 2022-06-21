//Keith Geneva
package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Service
public class DefaultUserDao implements UserDao {

    @Autowired
    private final NamedParameterJdbcTemplate provider;

    public DefaultUserDao(NamedParameterJdbcTemplate provider) {
        this.provider = provider;
    }

    @Override
    public Stream<User> all(int limit) {
        String sql = "SELECT user_id, user_name "
                + "FROM users "
                + "LIMIT " + limit;
        List<User> users = provider.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                String user_ID = rs.getString("user_ID");
                String user_name = rs.getString("user_name");
                User model = new User(user_ID, user_name);
                return model;
            }
        });

        return users.stream();
    }

    @Override
    public Optional<User> get(String id) {
        String sql = "SELECT user_id, user_name "
                + "FROM users "
                + "WHERE user_id = :user_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", id);

        List<User> users = provider.query(sql, parameters, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new User(rs.getString("user_id"),
                        rs.getString("user_name"));
            }
        });

        if (users.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(users.get(0));
    }

    @Override
    public Optional<User> save(User input) {
        if (input == null) {
            return Optional.empty();
        }

        return save(input.getUser_id(), input);
    }

    @Override
    public Optional<User> save(String id, User input) {
        if (input == null) {
            return Optional.empty();
        }
        if (input.isValid()) {
            Optional<User> existing = get(id);
            String sql = null;
            if (existing.isEmpty()) {
                sql = "INSERT INTO users (user_id, user_name) "
                        + "VALUES (:user_id,:user_name);";
            } else {
                sql = "UPDATE users SET user_id = :user_id, "
                        + "user_name = :user_name";
            }

            // SQL
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", input.getUser_id());
            parameters.addValue("user_name", input.getUser_name());

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return get(input.getUser_id());
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            return Optional.empty();
        }

        Optional<User> existing = get(id);
        if (existing.isPresent()) {
            // DELETE
            String sql = "DELETE FROM rules WHERE rules_id = :rules_id;";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("user_id", id);

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return existing;
            }
        }
        return Optional.empty();
    }

}
