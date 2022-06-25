/**
 * @author Jonathan Rubio
 */

package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.GameDao;
import com.cscloi.card_db.entity.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultGameService implements GameService {

    @Autowired
    private GameDao gameDao;

    /**
     * Returns all games.
     *
     * @param limit The maximum number of games to return.
     * @return A list of all the games.
     */
    @Override
    public List<Game> all(int limit) {
        return gameDao.all(limit);
    }

    /**
     * Returns all games owned by a user
     *
     * @param limit   The maximum number of games to return.
     * @param user_ID ID of user who owns the games.
     * @return A list of all the games.
     */
    @Override
    public List<Game> all_of_a_User(int limit, String user_ID) {
        return gameDao.all_of_a_User(limit, user_ID);
    }

    /**
     * Gets a game by it's unique identifier.
     *
     * @param gameID The unique identifier
     * @return The game if found, otherwise returns null.
     */
    @Override
    public Game get(String gameID) {
        return gameDao.get(gameID);
    }

    /**
     * Creates a new game.
     *
     * @param input The new game.
     * @return The new game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game create(Game input) {
        return gameDao.save(input);
    }

    /**
     * Updates a game.
     *
     * @param gameID The existing id of the game to update.
     * @param input  The updated game.
     * @return The updated game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game update(String gameID, Game input) {
        return gameDao.save(gameID, input);
    }

    /**
     * Deletes or removes a game.
     *
     * @param gameID The unique id of the game to remove.
     * @return The removed game if successful, otherwise returns an empty optional.
     */
    @Override
    public Game delete(String gameID) {
        return gameDao.delete(gameID);
    }
}
