/**
 * @author Jonathan Rubio
 */

package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.GamePiece;
import com.cscloi.card_db.service.GamePieceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Override
    public List<GamePiece> all(String game_id) {
        return gamePieceService.all(MAX_ITEMS, game_id);
    }

    @Override
    public GamePiece get(String game_piece_id) {
        return gamePieceService.get(game_piece_id);
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
    public GamePiece update(String game_piece_id, GamePiece gamePiece) {
        if ((game_piece_id == null) || (game_piece_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game_piece_id provided.");
        }
        if (gamePiece == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game piece data provided.");
        }

        if (gamePiece.isValid()) {
            GamePiece result = gamePieceService.update(game_piece_id, gamePiece);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Title not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid game piece data specified.");
    }

    @Override
    public GamePiece delete(String game_piece_id) {
        if ((game_piece_id == null) || (game_piece_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No game_piece_id provided.");
        }

        GamePiece result = gamePieceService.delete(game_piece_id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game piece with requested id was not found");
    }
}
