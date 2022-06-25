/**
 * @author Jonathan Rubio
 */

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DefaultGameDao implements GameDao {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    /**
     * Returns all games.
     *
     * @param limit The maximum number of games to return.
     * @return A list of all the games.
     */
    @Override
    public List<Game> all(int limit) {
        String sql = ""
                + "SELECT * "
                + "FROM games "
                + "LIMIT "
                + limit;
        return jdbcTemplate.query(sql, new RowMapper<Game>() {
            @Override
            public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Game.builder()
                        .game_pk(rs.getString("game_pk"))
                        .game_id(rs.getString("game_id"))
                        .game_name(rs.getString("game_name"))
                        .creator_name(rs.getString("creator_name"))
                        .build();
            }
        });
    }

    /**
     * Returns all games owned by a user
     *
     * @param limit  The maximum number of games to return.
     * @param userID ID of user who owns the games.
     * @return A list of all the games.
     */
    @Override
    public List<Game> all_of_a_User(int limit, String userID) {
        String sql = ""
                + "SELECT game_pk, game_id, game_name, creator_name "
                + "FROM userownedgames "
                + "INNER JOIN users u on userownedgames.user_fk = u.user_pk "
                + "INNER JOIN games g on userownedgames.game_fk = g.game_pk "
                + "WHERE user_fk = :user_id "
                + "LIMIT "
                + limit;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userID);
        return jdbcTemplate.query(sql, params, new RowMapper<Game>() {
            @Override
            public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
                return Game.builder()
                        .game_pk(rs.getString("game_pk"))
                        .game_id(rs.getString("game_id"))
                        .game_name(rs.getString("game_name"))
                        .creator_name(rs.getString("creator_name"))
                        .build();
            }
        });
    }

    /**
     * Gets a game by it's unique identifier.
     *
     * @param gameID The unique identifier
     * @return The game if found, otherwise returns null.
     */
    @Override
    public Game get(String gameID) {
        String sql = "SELECT * "
                + "FROM games "
                + "WHERE game_pk = :game_pk;";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("game_pk", gameID);

        List<Game> games = jdbcTemplate.query(sql, params, new RowMapper<Game>() {
            @Override
            public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Game(rs.getString("game_pk"),
                        rs.getString("game_id"),
                        rs.getString("game_name"),
                        rs.getString("creator_name"));
            }
        });

        if (games.isEmpty()) {
            return null;
        }
        return games.get(0);
    }

    /**
     * Creates a new game.
     *
     * @param game The new game.
     * @return The new game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game save(Game game) {
        if (game == null) {
            return null;
        }

        return save(game.getGame_pk(), game);

    }

    /**
     * Creates a new game.
     *
     * @param gameID The existing id of the game to update.
     * @param game   The new game.
     * @return The new game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game save(String gameID, Game game) {
        Game exists = get(gameID);
        String sql;
        if (exists == null) {
            sql = "INSERT INTO games(game_pk,game_id,game_name,creator_name) " + "VALUES (:game_pk, :game_id, :game_name, :creator_name);";
        } else {
            sql = "UPDATE games SET game_pk = :game_pk, "
                    + "game_id = :game_id, "
                    + "game_name = :game_name, "
                    + "creator_name = :creator_name "
                    + "WHERE game_pk = :existing_game_pk;";
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("game_pk", game.getGame_pk());
        params.addValue("game_id", game.getGame_id());
        params.addValue("game_name", game.getGame_name());
        params.addValue("creator_name", game.getCreator_name());
        params.addValue("existing_game_pk", gameID);

        int rows = jdbcTemplate.update(sql, params);
        if (rows == 1) {
            return game;
        }

        throw new RuntimeException("Could not save game piece to database.");
    }

    /**
     * Deletes or removes a game.
     *
     * @param gameID The unique id of the game to remove.
     * @return The removed game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game delete(String gameID) {
        if (gameID.isEmpty()) {
            return null;
        }
        Game existing = get(gameID);
        if (existing.isValid()) {
            String sql = "DELETE FROM games WHERE game_pk = :game_pk;";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("game_pk", gameID);

            int rows = jdbcTemplate.update(sql, params);
            if (rows == 1) {
                return existing;
            }
        }

        throw new RuntimeException("Could not delete game piece from database.");
    }
}
