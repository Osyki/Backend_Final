/**
 * @author Jonathan Rubio
 * @version 1.0
 * @since 2022-06-23
 */

package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.GamePieceDao;
import com.cscloi.card_db.entity.GamePiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DefaultGamePieceService implements GamePieceService {
    @Autowired
    private GamePieceDao gamePieceDao;

    /**
     * Returns all game pieces.
     * @param limit The maximum number of game pieces to return.
     * @return A list of all game pieces.
     */
    @Override
    public List<GamePiece> all(int limit) {
        return gamePieceDao.all(limit);
    }

    /**
     * Returns all game pieces by specific game id.
     *
     * @param limit  The maximum number of game pieces to return.
     * @param gameID The id to find game pieces for
     * @return A list of all game pieces.
     */
    @Override
    public List<GamePiece> all(int limit, String gameID) {
        return gamePieceDao.get(limit, gameID);
    }

    /**
     * Gets a game piece by it's unique identifier.
     *
     * @param id The unique identifier.
     * @return The game piece if found, otherwise returns null.
     */
    @Override
    public GamePiece get(String id) {
        GamePiece rs = gamePieceDao.get(id);
        if (rs.isValid()) {
            return rs;
        }

        throw new NoSuchElementException("Game piece with primary key " + id + " not found.");
    }

    /**
     * Creates a new game piece.
     *
     * @param gamePiece The new game piece.
     * @return The new game piece if successful, otherwise returns null.
     */
    @Override
    public GamePiece create(GamePiece gamePiece) {
        if (gamePiece == null) {
            return null;
        }

        if (gamePiece.isValid()) {
            return gamePieceDao.save(gamePiece);
        }

        throw new RuntimeException("Could not create new game piece.");
    }

    /**
     * Updates an existing game piece.
     *
     * @param id        The unique id to update or change.
     * @param gamePiece The update game piece information.
     * @return The game piece if successful, otherwise returns null.
     */
    @Override
    public GamePiece update(String id, GamePiece gamePiece) {
        if (id.isEmpty() || gamePiece == null) {
            return null;
        }

        if (gamePiece.isValid()) {
            return gamePieceDao.save(id, gamePiece);
        }

        throw new RuntimeException("Could not update game piece.");
    }

    /**
     * Deletes or removes a game piece.
     *
     * @param id The unique id of the game piece to remove.
     * @return The removed game piece if successful, otherwise returns null.
     */
    @Override
    public GamePiece delete(String id) {
        if (id.isEmpty()) {
            return null;
        }
        GamePiece deleted = gamePieceDao.delete(id);
        if (deleted.isValid()) {
            return deleted;
        }

        return null;
    }
}
