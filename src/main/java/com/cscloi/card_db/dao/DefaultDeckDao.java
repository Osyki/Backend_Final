//Keith Geneva
package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Deck;
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
public class DefaultDeckDao implements DeckDao {

    @Autowired
    private NamedParameterJdbcTemplate provider;

    public DefaultDeckDao(NamedParameterJdbcTemplate provider) {
        this.provider = provider;
    }

    @Override
    public Stream<Deck> all(int limit) {
        String sql = "SELECT deck_id, user_id, deck_name "
                + "FROM decks "
                + "inner join users u on decks.user_fk = u.user_pk "
                + "limit " + limit;
        List<Deck> decks = provider.query(sql, new RowMapper<Deck>() {
            @Override
            public Deck mapRow(ResultSet rs, int rowNum) throws SQLException {
                String deck_id = rs.getString("deck_id");
                String deck_name = rs.getString("deck_name");
                String user_id = rs.getString("user_id");
                Deck model = new Deck(deck_id, user_id, deck_name);
                return model;
            }
        });

        return decks.stream();
    }

    @Override
    public Stream<Deck> all_for_a_user(int limit, String userID) {
        String sql = "SELECT deck_id, user_id, deck_name "
                + "FROM decks "
                + "inner join users u on decks.user_fk = u.user_pk "
                + "WHERE user_id = :user_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", userID);

        List<Deck> decks = provider.query(sql, parameters, new RowMapper<Deck>() {
            @Override
            public Deck mapRow(ResultSet rs, int rowNum) throws SQLException {
                String deck_id = rs.getString("deck_id");
                String deck_name = rs.getString("deck_name");
                String user_id = rs.getString("user_id");
                Deck model = new Deck(deck_id, user_id, deck_name);
                return model;
            }
        });

        return decks.stream();
    }

    @Override
    public Optional<Deck> get(String deckID) {
        String sql = "SELECT deck_id, user_id, deck_name "
                + "FROM decks "
                + "inner join users u on decks.user_fk = u.user_pk "
                + "WHERE deck_id = :deck_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("deck_id", deckID);

        List<Deck> decks = provider.query(sql, parameters, new RowMapper<Deck>() {
            @Override
            public Deck mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Deck(rs.getString("deck_id"),
                        rs.getString("user_id"),
                        rs.getString("deck_name"));

            }
        });

        if (decks.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(decks.get(0));
    }

    @Override
    public Optional<Deck> save(Deck input) {
        if (input == null) {
            return Optional.empty();
        }

        return save(input.getDeck_id(), input);
    }

    @Override
    public Optional<Deck> save(String deckID, Deck input) {
        if (input == null) {
            return Optional.empty();
        }
        if (input.isValid()) {
            Optional<Deck> existing = get(deckID);
            String sql = null;
            if (existing.isEmpty()) {
                sql = "INSERT INTO decks (deck_id, user_fk, deck_name) "
                        + "VALUES (:deck_id, :user_fk, :deck_name);";
            } else {
                sql = "UPDATE decks SET user_fk = :user_fk, "
                        + "deck_name = :deck_name "
                        + "WHERE deck_id = :deck_id;";
            }

            // SQL
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("deck_id", input.getDeck_id());
            parameters.addValue("deck_name", input.getDeck_name());
            parameters.addValue("user_fk", get_fk(input.getUser_id()));

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return get(input.getDeck_id());
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Deck> delete(String deckID) {
        if ((deckID == null) || (deckID.isEmpty())) {
            return Optional.empty();
        }

        Optional<Deck> existing = get(deckID);
        if (existing.isPresent()) {
            String sql = "DELETE FROM decks WHERE deck_id = :deck_id;";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("deck_id", deckID);

            int rows = provider.update(sql, parameters);
            if (rows == 1) {
                return existing;
            }
        }
        return Optional.empty();
    }

    // Converts user_id to user_pk, Used by save function
    private Long get_fk(String userID) {
        String sql = "SELECT user_pk "
                + "FROM users "
                + "WHERE user_id = :user_id;";
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("user_id", userID);

        List<Long> pk = provider.query(sql, parameters, new RowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong("user_pk");
            }
        });

        return pk.get(0);
    }

}
