////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Expansion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@Slf4j
public class DefaultExpansionDao implements ExpansionDao {

    @Autowired
    private NamedParameterJdbcTemplate provider;


    @Override
    public List<Expansion> all(int limit) {
        // @formatter:off

        String sql = ""
                + "SELECT * "
                + "FROM expansions "
                + "LIMIT " + limit;
        // @formatter:on

        List<Expansion> expansion = provider.query(sql, new RowMapper<Expansion>() {
            @Override
            public Expansion mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Expansion.builder()
                        .expansion_id(rs.getString("expansion_id"))
                        .expansion_name(rs.getString("expansion_name"))
                        .expansion_pk(rs.getLong("expansion_pk"))
                        .build();
            }
        });
        return expansion;

    }

    @Override
    public List<Expansion> all_of_a_Game(String gameID) {

        // @formatter:off

        String sql = ""
                + "SELECT * "
                + "FROM games "
                + "INNER JOIN expansions ON games.game_pk = expansions.game_fk "
                + "WHERE game_id = :gameID;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", gameID);

        // @formatter:on
        List<Expansion> expansion = provider.query(sql, params, new RowMapper<Expansion>() {
            @Override
            public Expansion mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Expansion.builder()
                        .expansion_id(rs.getString("expansion_id"))
                        .expansion_name(rs.getString("expansion_name"))
                        .expansion_pk(rs.getLong("expansion_pk"))
                        .build();
            }
        });
        return expansion;

    }

    @Override
    public Expansion get(String expansionID) {

        String sql = "SELECT * "
                + "FROM expansions "
                + "WHERE expansion_id = :id;";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", expansionID);

        List<Expansion> expansion = provider.query(sql, params, new RowMapper<Expansion>() {
            @Override
            public Expansion mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Expansion(rs.getLong("expansion_pk"),
                        rs.getString("expansion_id"),
                        rs.getLong("game_fk"),
                        rs.getString("expansion_name"));

            }
        });

        if (expansion.isEmpty()) {
            return null;
        }
        return expansion.get(0);

    }

    @Override
    public Expansion save(Expansion input) {
        if (input == null) {
            return null;
        }

        return save(input.getExpansion_id(), input);
    }

    @Override
    public Expansion save(String expansionID, Expansion input) {
        Expansion existing = get(expansionID);
        String sql = null;
        if (existing == null) {
            sql = "INSERT INTO expansions (expansion_id, expansion_name, game_fk) "
                    + "VALUES (:expansion_id,:expansion_name,:game_fk);";
        } else {
            sql = "UPDATE expansions SET expansion_id = :expansion_id, " +
                    "expansion_name = :expansion_name, " +
                    "game_fk =  :game_fk "
                    + "WHERE expansion_id = :expansionID;";
        }

        //SQL
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("expansion_id", input.getExpansion_id());
        parameters.addValue("expansion_name", input.getExpansion_name());
        parameters.addValue("game_fk", input.getGame_fk());
        parameters.addValue("expansionID", expansionID);

        int rows = provider.update(sql, parameters);
        if (rows == 1) {
            return existing;
        }


        throw new RuntimeException("Could not save card to database.");

    }

    @Override
    public Expansion delete(String expansionID) {
        if (expansionID.isEmpty()) {
            return null;
        }
        Expansion existing = get(expansionID);
        if (existing.isValid()) {
            String sql = "DELETE FROM expansions WHERE expansion_id = :id;";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("id", expansionID);

            int rows = provider.update(sql, params);
            if (rows == 1) {
                return existing;
            }
        }

        throw new RuntimeException("Could not delete game piece from database.");

    }
}
