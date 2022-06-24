/**
 * @author Jonathan Rubio
 * @version 1.0
 * @since 2022-06-23
 */

package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.GamePiece;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "GamePiece")
public interface GamePieceController {
    int MAX_ITEMS = 500;

    @Operation(summary = "Get all game pieces.")
    @RequestMapping(value = "/game_pieces", method = RequestMethod.GET)
    public List<GamePiece> all();

    @Operation(summary = "Get game pieces by game id.")
    @RequestMapping(value = "/games/{game_id}/game_pieces", method = RequestMethod.GET)
    public List<GamePiece> all(@PathVariable String game_id);

    @Operation(summary = "Get game piece by unique id")
    @RequestMapping(value = "/game_pieces/{game_piece_id}", method = RequestMethod.GET)
    public GamePiece get(@PathVariable String game_piece_id);

    @Operation(summary = "Create new game piece.")
    @RequestMapping(value = "/game_pieces", method = RequestMethod.POST)
    public GamePiece create(@RequestBody GamePiece gamePiece);

    @Operation(summary = "Update existing game piece.")
    @RequestMapping(value = "/game_pieces/{game_piece_id}", method = RequestMethod.PUT)
    public GamePiece update(@PathVariable String game_piece_id, @RequestBody GamePiece gamePiece);

    @Operation(summary = "Remove existing game piece.")
    @RequestMapping(value = "/game_pieces/{game_piece_id}", method = RequestMethod.DELETE)
    public GamePiece delete(@PathVariable String game_piece_id);
}
