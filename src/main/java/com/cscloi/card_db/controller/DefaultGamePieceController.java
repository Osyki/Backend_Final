package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.GamePiece;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DefaultGamePieceController implements GamePieceController {

    @Override
    public List<GamePiece> all() {
        return null;
    }

    @Override
    public List<GamePiece> all(String gameID) {
        return null;
    }

    @Override
    public GamePiece get(Long id) {
        return null;
    }

    @Override
    public GamePiece create(GamePiece title) {
        return null;
    }

    @Override
    public GamePiece update(String id, GamePiece gamePiece) {
        return null;
    }

    @Override
    public GamePiece delete(String id) {
        return null;
    }
}
