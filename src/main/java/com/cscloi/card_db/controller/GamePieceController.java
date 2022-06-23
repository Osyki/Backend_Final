package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.GamePiece;
import com.sun.source.doctree.SummaryTree;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Game Piece Service"), servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
@Tag(name="GamePieces")
public interface GamePieceController {
    int MAX_ITEMS = 500;
    @Operation(summary = "Get all game pieces.")
    @RequestMapping(value="/gamepieces", method = RequestMethod.GET)
    public List<GamePiece> all();

    @Operation(summary = "Get game pieces by game id.")
    @RequestMapping(value = "/games/{gameid}/gamepieces", method = RequestMethod.GET)
    public List<GamePiece> all(@PathVariable String gameid);

    @Operation(summary = "Gets a game piece by unique id")
    @RequestMapping(value = "/gamepieces/{id}", method = RequestMethod.GET)
    public GamePiece get(@PathVariable String id);

    @Operation(summary = "Creates a new game piece.")
    @RequestMapping(value = "/gamepieces", method = RequestMethod.POST)
    public GamePiece create(@RequestBody GamePiece gamePiece);

    @Operation(summary = "Updates or modifies an existing game piece.")
    @RequestMapping(value = "/gamepieces/{id}", method = RequestMethod.PUT)
    public GamePiece update(@PathVariable String id, @RequestBody GamePiece gamePiece);

    @Operation(summary = "Removes an existing game piece.")
    @RequestMapping(value = "/gamepieces/{id}", method = RequestMethod.DELETE)
    public GamePiece delete(@PathVariable String id);
}
