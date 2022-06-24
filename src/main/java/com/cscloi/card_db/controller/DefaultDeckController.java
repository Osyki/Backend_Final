//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.service.DeckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class DefaultDeckController implements DeckController {

    @Autowired
    private DeckService service;

    public DefaultDeckController(DeckService service) {
        this.service = service;
    }

    public List<Deck> all() {
        List<Deck> decks = service.all(MAX_ITEMS_PER_REQUEST);
        return decks;
    }

    public List<Deck> all_for_a_user(@PathVariable String user_id) {
        List<Deck> decks = service.all_for_a_user(MAX_ITEMS_PER_REQUEST, user_id);
        return decks;
    }

    public Deck get(@PathVariable String deck_id) {
        Deck deck = service.get(deck_id);
        if (deck != null) {
            return deck;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested deck was not found.");
    }

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

