/**
 * @author Jonathan Rubio
 * @version 1.0
 * @since 2022-06-23
 */

package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class DefaultGameController implements GameController {

    @Autowired
    private GameService gameService;

    @Override
    public List<Game> all() {
        return gameService.all(MAX_ITEMS);
    }

    @Override
    public List<Game> all(String user_id) {
        return gameService.all_of_a_User(MAX_ITEMS, user_id);
    }

    @Override
    public Game get(String game_id) {
        return gameService.get(game_id);
    }

    @Override
    public Game create(Game game) {
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game provided.");
        }

        if (game.isValid()) {
            Game result = gameService.create(game);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Game not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game data specified.");
    }

    @Override
    public Game update(String game_id, Game game) {
        if ((game_id == null) || (game_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game id provided.");
        }
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game data provided.");
        }

        if (game.isValid()) {
            Game result = gameService.update(game_id, game);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Game not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game data specified.");
    }

    @Override
    public Game delete(String game_id) {
        if ((game_id == null) || (game_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game id provided.");
        }

        Game result = gameService.delete(game_id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game with requested id was not found");
    }
}
