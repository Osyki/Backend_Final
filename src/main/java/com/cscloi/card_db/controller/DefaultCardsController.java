////////////// done by Bishoy SOliman Hanna ///////////////////


package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Rules;
import com.cscloi.card_db.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@Slf4j
public class DefaultCardsController implements CardsController {

    @Autowired
    private CardsService cardsService;

    public DefaultCardsController(CardsService cardsService) {
        this.cardsService = cardsService;
    }

    @Operation(summary = "GET all Cards")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Card> all() {
        List<Card> card = cardsService.all(500);
        return card;
    }

    @Operation(summary = "GET all cards owned by a user")
    @RequestMapping(value = "/deck/{deckID}", method = RequestMethod.GET)
    public List<Card> all_for_a_user(@PathVariable String deckID) {
        List<Card> card = cardsService.all_for_a_user(deckID);
        if (card != null) {
            return card;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested cards was not retrieved.");
    }

    @Operation(summary = "GET a card by it's unique identifier.")
    @RequestMapping(value = "/{cardID}", method = RequestMethod.GET)
    public Card get(@PathVariable String cardID) {
        Card card =cardsService.get(cardID);

        if (card != null) {
            return card;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested card was not found.");

    }

    @Operation(summary = "Creates a new card")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Card create(@RequestBody Card input){
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No card provided.");
        }

        if (input.isValid()) {
            Card result = cardsService.create(input);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. card not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid card data specified.");
    }

    @Operation(summary = "Updates or modifies existing Card")
    @RequestMapping(value = "/update/{cardID}", method = RequestMethod.PUT)
    public Card update(@PathVariable String cardID, @RequestBody Card input) {
        if ((cardID == null) || (cardID.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Card id provided.");
        }
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Card data provided.");
        }

        if (input.isValid()) {
            Card result = cardsService.update(cardID, input);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Card not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Card data specified.");
    }

    @Operation(summary = "Removes an existing Card")
    @RequestMapping(value = "/delete/{cardID}", method = RequestMethod.DELETE)
    public Card delete(@PathVariable String cardID) {
        if ((cardID == null) || (cardID.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Card id provided.");
        }

        Card result = cardsService.delete(cardID);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card with requested id was not found");
    }

}
