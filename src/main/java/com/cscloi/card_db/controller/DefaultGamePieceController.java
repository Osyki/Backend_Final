package com.cscloi.card_db.controller;

import com.cscloi.card_db.dao.GamePieceDao;
import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.GamePiece;
import com.cscloi.card_db.service.GamePieceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class DefaultGamePieceController implements GamePieceController {

    @Autowired
    private GamePieceService gamePieceService;

    @Override
    public List<GamePiece> all() {
        return gamePieceService.all(MAX_ITEMS);
    }

    //FIXME: implement
    @Override
    public List<GamePiece> all(String gameID) {
        return null;
    }

    @Override
    public GamePiece get(String id) {
        return gamePieceService.get(id);
    }

    @Override
    public GamePiece create(GamePiece gamePiece) {
        if (gamePiece == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece provided.");
        }

        if (gamePiece.isValid()) {
            GamePiece result = gamePieceService.create(gamePiece);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Game piece not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game piece data specified.");
    }

    @Override
    public GamePiece update(String id, GamePiece gamePiece) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece id provided.");
        }
        if (gamePiece == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece data provided.");
        }

        if (gamePiece.isValid()) {
            GamePiece result = gamePieceService.update(id, gamePiece);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Title not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game piece data specified.");
    }

    @Override
    public GamePiece delete(String id) {
        if ((id == null) || (id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece id provided.");
        }

        GamePiece result = gamePieceService.delete(id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game piece with requested id was not found");
    }
}
