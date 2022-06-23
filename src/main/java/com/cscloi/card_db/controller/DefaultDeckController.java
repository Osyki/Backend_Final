//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.service.DeckService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class DefaultDeckController implements DeckController {
    private final int MAX_ITEMS_PER_REQUEST = 500;

    @Autowired
    private DeckService service;

    public DefaultDeckController(DeckService service) {
        this.service = service;
    }

    @Operation(summary = "Get all Decks")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Deck> all() {
        List<Deck> decks = service.all(MAX_ITEMS_PER_REQUEST);
        return decks;
    }

    @Operation(summary = "Gets deck by user id")
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public List<Deck> all_for_a_user(@PathVariable String user_id) {

        List<Deck> decks = service.all_for_a_user(MAX_ITEMS_PER_REQUEST, user_id);
        return decks;
    }

    @Operation(summary = "Gets deck by unique id")
    @RequestMapping(value = "/{deck_id}", method = RequestMethod.GET)
    public Deck get(@PathVariable String deck_id) {

        Deck deck = service.get(deck_id);
        if (deck != null) {
            return deck;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested deck was not found.");
    }


    @Operation(summary = "Creates a new deck")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Deck create(@RequestBody Deck deck) {
        if (deck == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deck data provided.");
        }

        if (deck.isValid()) {
            Deck result = service.create(deck);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Deck not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid deck data specified.");
    }

    @Operation(summary = "Updates or modifies an existing deck")
    @RequestMapping(value = "/{deck_id}", method = RequestMethod.PUT)
    public Deck update(@PathVariable String deck_id, @RequestBody Deck deck) {
        if ((deck_id == null) || (deck_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deck id provided.");
        }
        if (deck == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deck data provided.");
        }

        if (deck.isValid()) {
            Deck result = service.update(deck_id, deck);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Deck not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid deck data specified.");
    }

    @Operation(summary = "Removes an existing deck")
    @RequestMapping(value = "/{deck_id}", method = RequestMethod.DELETE)
    public Deck delete(@PathVariable String deck_id) {
        if ((deck_id == null) || (deck_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No deck id provided.");
        }

        Deck result = service.delete(deck_id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Deck with requested id was not found");
    }
}

