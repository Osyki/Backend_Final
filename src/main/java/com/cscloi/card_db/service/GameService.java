package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.User;

import java.util.List;

public interface GameService {
    /**
     * Returns all games.
     *
     * @param limit The maximum number of games to return.
     * @return A list of all the games.
     */
    List<Game> all(int limit);

    /**
     * Returns all games owned by a user
     *
     * @param limit   The maximum number of games to return.
     * @param user_ID ID of user who owns the games.
     * @return A list of all the games.
     */
    List<Game> all_of_a_User(int limit, String user_ID);

    /**
     * Gets a game by it's unique identifier.
     *
     * @param gameID The unique identifier
     * @return The game if found, otherwise returns null.
     */
    Game get(String gameID);

    /**
     * Creates a new game.
     *
     * @param input The new game.
     * @return The new game if successful, otherwise returns an empty optional.
     */
    Game create(User input);

    /**
     * Updates a game.
     *
     * @param gameID The existing id of the game to update.
     * @param input  The updated game.
     * @return The updated game if successful, otherwise returns an empty optional.
     */
    Game update(String gameID, User input);

    /**
     * Deletes or removes a game.
     *
     * @param gameID The unique id of the game to remove.
     * @return The removed game if successful, otherwise returns an empty optional.
     */
    Game delete(String gameID);
}

