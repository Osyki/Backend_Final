package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.GamePiece;

import java.util.List;

public interface GamePieceService {
    /**
     * Returns all game pieces.
     * @param limit The maximum number of game pieces to return.
     * @return A list of all game pieces.
     */
    List<GamePiece> all(int limit);

    /**
     * Gets a game piece by it's unique identifier.
     * @param id The unique identifier.
     * @return The game piece if found, otherwise returns null.
     */
    GamePiece get(String id);

    /**
     * Creates a new game piece.
     * @param gamePiece The new game piece.
     * @return The new game piece if successul, otherwise returns null.
     */
    GamePiece create(GamePiece gamePiece);

    /**
     * Updates an existing game piece.
     * @param id The unique id to update or change.
     * @param gamePiece The update game piece information.
     * @return The game piece if successful, otherwise returns null.
     */
    GamePiece update(Long id, GamePiece gamePiece);

    /**
     * Deletes or removes a game piece.
     * @param id The unique id of the game piece to remove.
     * @return The removed game piece if successful, otherwise returns null.
     */
    GamePiece delete(Long id);
}
