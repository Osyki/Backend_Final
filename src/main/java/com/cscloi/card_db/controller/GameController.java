/**
 * @author Jonathan Rubio
 * @version 1.0
 * @since 2022-06-23
 */

package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Game;
import io.swagger.v3.oas.annotations.Operation;;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Tag(name = "Game")
public interface GameController {
    int MAX_ITEMS = 500;

    @Operation(summary = "Get all games.")
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    public List<Game> all();

    @Operation(summary = "Get all games from specific user id.")
    @RequestMapping(value = "/users/{user_id}/games", method = RequestMethod.GET)
    public List<Game> all(@PathVariable String user_id);

    @Operation(summary = "Get game by unique id")
    @RequestMapping(value = "/games/{game_id}", method = RequestMethod.GET)
    public Game get(@PathVariable String game_id);

    @Operation(summary = "Create new game.")
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    public Game create(@RequestBody Game game);

    @Operation(summary = "Update existing game.")
    @RequestMapping(value = "/games/{game_id}", method = RequestMethod.PUT)
    public Game update(@PathVariable String game_id, @RequestBody Game game);

    @Operation(summary = "Remove existing game.")
    @RequestMapping(value = "/games/{game_id}", method = RequestMethod.DELETE)
    public Game delete(@PathVariable String game_id);
}
