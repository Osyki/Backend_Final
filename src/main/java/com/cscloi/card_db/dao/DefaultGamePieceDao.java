package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.GamePiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class DefaultGamePieceDao implements GamePieceDao{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<GamePiece> all(int limit) {
        String sql = ""
                + "SELECT * "
                + "FROM gamepieces "
                + "LIMIT "
                + limit + ";";
        return jdbcTemplate.query(sql, new RowMapper<GamePiece>() {
            @Override
            public GamePiece mapRow(ResultSet rs, int rowNum) throws SQLException {
                return GamePiece.builder()
                        .gamePiecePK(rs.getString("game_piece_pk"))
                        .gamePieceName(rs.getString("game_piece_name"))
                        .gamePieceDesc(rs.getString("game_piece_desc"))
                        .gamePieceFK(rs.getString("game_fk"))
                        .build();
            }
        });
    }

    @Override
    public GamePiece get(String id) {
        String sql = "SELECT * "
                + "FROM gamepieces "
                + "WHERE game_piece_pk = :game_pk;";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("game_pk", id);

        List<GamePiece> gamePieces = jdbcTemplate.query(sql, params, new RowMapper<GamePiece>() {
            @Override
            public GamePiece mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new GamePiece(rs.getString("game_piece_pk"),
                        rs.getString("game_piece_name"),
                        rs.getString("game_piece_desc"),
                        rs.getString("game_fk"));
            }
        });

        if (gamePieces.isEmpty()) {
            return null;
        }
        return gamePieces.get(0);
    }

    @Override
    public List<GamePiece> get(int limit, String gameID) {
        String sql = "SELECT * "
                + "FROM gamepieces "
                + "INNER JOIN games g ON gamepieces.game_fk = g.game_pk "
                + "WHERE game_pk = :game_pk "
                + "LIMIT " + limit + ";";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("game_pk", gameID);

        List<GamePiece> gamePieces = jdbcTemplate.query(sql, params, new RowMapper<GamePiece>() {
            @Override
            public GamePiece mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new GamePiece(rs.getString("game_piece_pk"),
                        rs.getString("game_piece_name"),
                        rs.getString("game_piece_desc"),
                        rs.getString("game_fk"));
            }
        });

        if (gamePieces.isEmpty()) {
            return null;
        }
        return gamePieces;
    }

    @Override
    public GamePiece save(GamePiece gamePiece) {
        if (gamePiece == null) {
            return null;
        }
        
        return save(gamePiece.getGamePiecePK(), gamePiece);

    }

    @Override
    public GamePiece save(String gamePiecePK, GamePiece gamePiece) {
        GamePiece exists = get(gamePiecePK);
        String sql;
        if (exists == null) {
            sql = "INSERT INTO gamepieces(game_piece_pk,game_piece_name,game_piece_desc,game_fk) "
                    + "VALUES (:game_piece_pk, :game_piece_name, :game_piece_desc, :game_fk);";
        } else {
            sql = "UPDATE gamepieces SET game_piece_pk = :game_piece_pk, "
                    + "game_piece_name = :game_piece_name, "
                    + "game_piece_desc = :game_piece_desc, "
                    + "game_fk = :game_fk "
                    + "WHERE game_piece_pk = :existing_game_piece_pk;";
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("game_piece_pk", gamePiece.getGamePiecePK());
        params.addValue("game_piece_name", gamePiece.getGamePieceName());
        params.addValue("game_piece_desc", gamePiece.getGamePieceDesc());
        params.addValue("game_fk",gamePiece.getGamePieceFK());
        params.addValue("existing_game_piece_pk", gamePiecePK);

        int rows = jdbcTemplate.update(sql,params);
        if (rows == 1) {
            return gamePiece;
        }

        throw new RuntimeException("Could not save game piece to database.");
    }

    @Override
    public GamePiece delete(String id) {
        if (id.isEmpty()) {
            return null;
        }
        GamePiece existing = get(id);
        if (existing.isValid()) {
            String sql = "DELETE FROM gamepieces WHERE game_piece_pk = :game_piece_pk;";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("game_piece_pk", id);

            int rows =jdbcTemplate.update(sql, params);
            if (rows == 1) {
                return existing;
            }
        }

        throw new RuntimeException("Could not delete game piece from database.");
    }
}
