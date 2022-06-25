////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Rules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@Slf4j
public class DefaultCardsDao implements CardsDao {

    @Autowired
    private NamedParameterJdbcTemplate provider;

    @Override
    public List<Card> all(int limit) {
        log.info("DAO: limit={}", limit);

        // @formatter:off

        String sql = ""
                + "SELECT * "
                + "FROM cards "
                + "LIMIT " + limit;

        // @formatter:on

        List<Card> card = provider.query(sql, new RowMapper<Card>() {
            @Override
            public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Card.builder()
                        .card_id(rs.getString("card_id"))
                        .card_name(rs.getString("card_name"))
                        .card_pk(rs.getLong("card_pk"))
                        .build();


            }
        });
        return card;
    }

    @Override
    public List<Card> all_for_a_deck(String deckID) {

        log.info("DAO: deckID={}", deckID);

        // @formatter:off

        String sql = ""
                + "SELECT * "
                + "FROM decks "
                + "INNER JOIN cardsindecks USING (deck_fk) "
                + "INNER JOIN cards USING (card_fk) "
                + "Where deck_id = :deckID;";
        // @formatter:on

        List<Card> card = provider.query(sql, new RowMapper<Card>() {
            @Override
            public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Card.builder()
                        .card_id(rs.getString("card_id"))
                        .card_name(rs.getString("card_name"))
                        .build();

            }
        });
        return card;
    }

    @Override
    public Card get(String cardID) {
        String sql = "SELECT * "
                + "FROM cards "
                + "WHERE card_id = :id;";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", cardID);

        List<Card> card = provider.query(sql, params, new RowMapper<Card>() {
            @Override
            public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Card(rs.getLong("card_pk"),
                        rs.getString("card_id"),
                        rs.getLong("expansion_fk"),
                        rs.getString("card_name"));

            }
        });

        if (card.isEmpty()) {
            return null;
        }
        return card.get(0);

    }

    @Override
    public Card save(Card input) {
        if (input == null) {
            return null;
        }

        return save(input.getCard_id(), input);
    }

    @Override
    public Card save(String cardID, Card input) {

        Card existing = get(cardID);
        String sql = null;
        if (existing == null) {
            sql = "INSERT INTO cards (card_id, card_name, expansion_fk) "
                    + "VALUES (:card_id,:card_name,:expansion_fk);";
        } else {
            sql = "UPDATE cards SET card_id = :card_id, " +
                    "card_name = :card_name, " +
                    "expansion_fk =  :expansion_fk "
                    + "WHERE card_id = :cardID;";
        }

        //SQL
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("card_id", input.getCard_id());
        parameters.addValue("card_name", input.getCard_name());
        parameters.addValue("expansion_fk", input.getExpansion_fk());
        parameters.addValue("cardID", cardID);

        int rows = provider.update(sql, parameters);
        if (rows == 1) {
            return existing;
        }


        throw new RuntimeException("Could not save card to database.");
    }

    @Override
    public Card delete(String cardID) {
        if (cardID.isEmpty()) {
            return null;
        }
        Card existing = get(cardID);
        if (existing.isValid()) {
            String sql = "DELETE FROM cards WHERE card_id = :id;";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", cardID);

            int rows = provider.update(sql, params);
            if (rows == 1) {
                return existing;
            }
        }

        throw new RuntimeException("Could not delete game piece from database.");
    }
}
