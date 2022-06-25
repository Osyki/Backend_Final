////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Expansion;
import com.cscloi.card_db.service.CardsService;
import com.cscloi.card_db.service.ExpansionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class DefaultExpansionController implements ExpansionController{
    @Autowired
    private ExpansionService expansionService;

    public DefaultExpansionController(ExpansionService expansionService) {
        this.expansionService = expansionService;
    }

    @Operation(summary = "GET all expansions")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Expansion> all() {
        List<Expansion> expansion = expansionService.all(500);
        return expansion;
    }

    @Operation(summary = "GET all expansion owned by a game")
    @RequestMapping(value = "/game/{gameID}", method = RequestMethod.GET)
    public List<Expansion> all_for_a_user(@PathVariable String gameID) {
        List<Expansion> expansion = expansionService.all_of_a_Game(gameID);
        if (expansion != null) {
            return expansion;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Requested expansions was not retrieved.");
    }

    @Operation(summary = "GET a expansion by it's unique identifier.")
    @RequestMapping(value = "/{expansionID}", method = RequestMethod.GET)
    public Expansion get(@PathVariable String expansionID) {
        Expansion expansion =expansionService.get(expansionID);

        if (expansion != null) {
            return expansion;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested expansion was not found.");

    }

    @Operation(summary = "Creates a new expansion")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Expansion create(@RequestBody Expansion input){
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No expansion provided.");
        }

        if (input.isValid()) {
            Expansion result = expansionService.create(input);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. expansion not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid expansion data specified.");
    }

    @Operation(summary = "Updates or modifies existing expansion")
    @RequestMapping(value = "/update/{expansionID}", method = RequestMethod.PUT)
    public Expansion update(@PathVariable String expansionID, @RequestBody Expansion input) {
        if ((expansionID == null) || (expansionID.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No expansion id provided.");
        }
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No expansion data provided.");
        }

        if (input.isValid()) {
            Expansion result = expansionService.update(expansionID, input);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Expansion not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Expansion data specified.");
    }

    @Operation(summary = "Removes an existing expansion")
    @RequestMapping(value = "/delete/{expansionID}", method = RequestMethod.DELETE)
    public Expansion delete(@PathVariable String expansionID) {
        if ((expansionID == null) || (expansionID.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Card id provided.");
        }

        Expansion result = expansionService.delete(expansionID);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "expansion with requested id was not found");
    }




}
