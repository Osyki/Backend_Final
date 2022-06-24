//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Tag(name = "Deck")
public interface DeckController {

    int MAX_ITEMS_PER_REQUEST = 500;

    @Operation(summary = "Get all decks")
    @RequestMapping(value = "/decks", method = RequestMethod.GET)
    public List<Deck> all();

    @Operation(summary = "Get list of decks by user id")
    @RequestMapping(value = "/user/{user_id}/decks", method = RequestMethod.GET)
    public List<Deck> all_for_a_user(@PathVariable String user_id);

    @Operation(summary = "Gets deck by unique id")
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.GET)
    public Deck get(@PathVariable String deck_id);

    @Operation(summary = "Create new deck")
    @RequestMapping(value = "/decks", method = RequestMethod.POST)
    public Deck create(@RequestBody Deck deck);

    @Operation(summary = "Update existing deck")
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.PUT)
    public Deck update(@PathVariable String deck_id, @RequestBody Deck deck);

    @Operation(summary = "Remove existing deck")
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.DELETE)
    public Deck delete(@PathVariable String deck_id);

}
