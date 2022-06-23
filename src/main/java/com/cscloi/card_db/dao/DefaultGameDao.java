package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Game;

import java.util.List;

//fixme

public class DefaultGameDao implements GameDao {
    /**
     * Returns all games.
     *
     * @param limit The maximum number of games to return.
     * @return A list of all the games.
     */
    @Override
    public List<Game> all(int limit) {
        return null;
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
        return null;
    }

    /**
     * Gets a game by it's unique identifier.
     *
     * @param gameID The unique identifier
     * @return The game if found, otherwise returns null.
     */
    @Override
    public Game get(String gameID) {
        return null;
    }

    /**
     * Creates a new game.
     *
     * @param game The new game.
     * @return The new game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game save(Game game) {
        return null;
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
        return null;
    }

    /**
     * Deletes or removes a game.
     *
     * @param gameID The unique id of the game to remove.
     * @return The removed game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game delete(String gameID) {
        return null;
    }
}
