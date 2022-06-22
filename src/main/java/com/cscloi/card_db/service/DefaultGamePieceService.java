package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.GamePiece;

import java.util.List;

public class DefaultGamePieceService implements GamePieceService{
    /**
     * Returns all game pieces.
     * @param limit The maximum number of game pieces to return.
     * @return A list of all game pieces.
     */
    @Override
    public List<GamePiece> all(int limit) {
        return null;
    }

    /**
     * Gets a game piece by it's unique identifier.
     *
     * @param id The unique identifier.
     * @return The game piece if found, otherwise returns null.
     */
    @Override
    public GamePiece get(String id) {
        return null;
    }

    /**
     * Creates a new game piece.
     *
     * @param gamePiece The new game piece.
     * @return The new game piece if successul, otherwise returns null.
     */
    @Override
    public GamePiece create(GamePiece gamePiece) {
        return null;
    }

    /**
     * Updates an existing game piece.
     *
     * @param id        The unique id to update or change.
     * @param gamePiece The update game piece information.
     * @return The game piece if successful, otherwise returns null.
     */
    @Override
    public GamePiece update(Long id, GamePiece gamePiece) {
        return null;
    }

    /**
     * Deletes or removes a game piece.
     *
     * @param id The unique id of the game piece to remove.
     * @return The removed game piece if successful, otherwise returns null.
     */
    @Override
    public GamePiece delete(Long id) {
        return null;
    }
}
