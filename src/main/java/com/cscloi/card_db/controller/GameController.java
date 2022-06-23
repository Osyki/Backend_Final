package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Game;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Game Service"), servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
@Tag(name="Games")
public interface GameController {
    int MAX_ITEMS = 500;

    @Operation(summary = "Get all games.")
    @RequestMapping(value="/games", method = RequestMethod.GET)
    public List<Game> all();

    @Operation(summary = "Get all games from a specific userID.")
    @RequestMapping(value="/users/{userid}/games", method = RequestMethod.GET)
    public List<Game> all(@PathVariable String userid);

    @Operation(summary = "Gets a game by unique id")
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    public Game get(@PathVariable String id);

    @Operation(summary = "Creates a new game.")
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public Game create(@RequestBody Game game);

    @Operation(summary = "Updates or modifies an existing game.")
    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    public Game update(@PathVariable String id, @RequestBody Game game);

    @Operation(summary = "Removes an existing game.")
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    public Game delete(@PathVariable String id);
}
