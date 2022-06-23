//Keith Geneva
package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Rules;
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
public class DefaultRulesDao implements RulesDao {

    @Autowired
    private NamedParameterJdbcTemplate provider;

    public DefaultRulesDao(NamedParameterJdbcTemplate provider) {
        this.provider = provider;
    }

    @Override
    public Stream<Rules> all(int limit) {
        String sql = "SELECT rules_id, rule_text, game_id "
                + "FROM rules "
                + "inner join games g on rules.game_fk = g.game_pk "
                + "limit " + limit;
        List<Rules> rules = provider.query(sql, new RowMapper<Rules>() {
            @Override
            public Rules mapRow(ResultSet rs, int rowNum) throws SQLException {
                String rules_id = rs.getString("rules_id");
                String rule_text = rs.getString("rule_text");
                String game_id = rs.getString("game_id");
                Rules model = new Rules(rules_id, game_id, rule_text);
                return model;
            }
        });

        return rules.stream();
    }

    @Override
    public Optional<Rules> get_of_a_game(String gameID) {
        String sql = "SELECT rules_id, rule_text, game_id "
                + "FROM rules "
                + "inner join games on games.game_pk = rules.game_fk "
                + "WHERE game_id = :game_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("game_id", gameID);

        List<Rules> rules = provider.query(sql, parameters, new RowMapper<Rules>() {
            @Override
            public Rules mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Rules(rs.getString("rules_id"),
                        rs.getString("game_id"),
                        rs.getString("rule_text"));
            }
        });

        if (rules.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(rules.get(0));
    }

    @Override
    public Optional<Rules> get(String rulesID) {
        String sql = "SELECT rules_id, rule_text, game_id "
                + "FROM rules "
                + "inner join games on games.game_pk = rules.game_fk "
                + "WHERE rules_id = :rules_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("rules_id", rulesID);

        List<Rules> rules = provider.query(sql, parameters, new RowMapper<Rules>() {
            @Override
            public Rules mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Rules(rs.getString("rules_id"),
                        rs.getString("game_id"),
                        rs.getString("rule_text"));

            }
        });

        if (rules.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(rules.get(0));
    }

    @Override
    public Optional<Rules> save(Rules input) {
        if (input == null) {
            return Optional.empty();
        }

        return save(input.getRules_id(), input);
    }

    @Override
    public Optional<Rules> save(String rulesID, Rules input) {
        if (input == null) {
            return Optional.empty();
        }
        if (input.isValid()) {
            Optional<Rules> existing = get(rulesID);
            String sql = null;
            if (existing.isEmpty()) {
                sql = "INSERT INTO rules (rules_id,rule_text,game_fk) "
                        + "VALUES (:rules_id,:rule_text,:game_fk);";
            } else {
                sql = "UPDATE rules SET rule_text = :rule_text, "
                        + "game_fk = :game_fk "
                        + "WHERE rules_id = :rules_id;";
            }

            // SQL
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("rules_id", input.getRules_id());
            parameters.addValue("rule_text", input.getRule_text());
            parameters.addValue("game_fk", get_fk(input.getGame_id()));

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return get(input.getRules_id());
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Rules> delete(String rulesID) {
        if ((rulesID == null) || (rulesID.isEmpty())) {
            return Optional.empty();
        }

        Optional<Rules> existing = get(rulesID);
        if (existing.isPresent()) {
            String sql = "DELETE FROM rules WHERE rules_id = :rules_id;";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("rules_id", rulesID);

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return existing;
            }
        }
        return Optional.empty();
    }

    // Converts game_id to game_pk, Used by save function
    private Long get_fk(String gameID) {
        String sql = "SELECT game_pk "
                + "FROM games "
                + "WHERE game_id = :game_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("game_id", gameID);

        List<Long> pk = provider.query(sql, parameters, new RowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("game_pk");
            }
        });

        return pk.get(0);
    }

}
