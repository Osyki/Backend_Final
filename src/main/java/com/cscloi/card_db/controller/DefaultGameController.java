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
public class DefaultGameController implements GameController{

    @Autowired
    private GameService gameService;

    @Override
    public List<Game> all() {
        return gameService.all(MAX_ITEMS);
    }

    @Override
    public List<Game> all(String userid) {
        return gameService.all_of_a_User(MAX_ITEMS, userid);
    }

    @Override
    public Game get(String id) {
        return gameService.get(id);
    }

    @Override
    public Game create(Game game) {
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece provided.");
        }

        if (game.isValid()) {
            Game result = gameService.create(game);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Game piece not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game piece data specified.");
    }

    @Override
    public Game update(String id, Game game) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece id provided.");
        }
        if (game == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece data provided.");
        }

        if (game.isValid()) {
            Game result = gameService.update(id, game);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Title not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game piece data specified.");
    }

    @Override
    public Game delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece id provided.");
        }

        Game result = gameService.delete(id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game piece with requested id was not found");
    }
}
