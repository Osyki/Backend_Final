//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Rules;
import com.cscloi.card_db.service.RulesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class DefaultRulesController implements RulesController{
    private final int MAX_ITEMS_PER_REQUEST = 500;

    //@Autowired
    private RulesService service;

    public DefaultRulesController(RulesService service) {
        this.service = service;
    }

    @Operation(summary = "Get all Rules")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Rules> all() {
        List<Rules> rules = service.all(MAX_ITEMS_PER_REQUEST);
        return rules;
    }

    @Operation(summary = "Gets rules by unique id")
    @RequestMapping(value = "/{rules_id}", method = RequestMethod.GET)
    public Rules get(@PathVariable String rules_id) {

        Rules rules = service.get(rules_id);
        if (rules != null) {
            return rules;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested rules was not found.");
    }

    @Operation(summary = "Gets rules by game id")
    @RequestMapping(value = "/game/{game_id}", method = RequestMethod.GET)
    public Rules of_a_Game(@PathVariable String game_id) {

        Rules rules = service.of_a_Game(game_id);                                                                 //FIXME
        if (rules != null) {
            return rules;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested rules was not found.");
    }


    @Operation(summary = "Creates new rules")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Rules create(@RequestBody Rules rules) {
        if (rules == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No rules data provided.");
        }

        if (rules.isValid()) {
            Rules result = service.create(rules);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Rules not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Rules data specified.");
    }

    @Operation(summary = "Updates or modifies existing rules")
    @RequestMapping(value = "/update/{rules_id}", method = RequestMethod.PUT)
    public Rules update(@PathVariable String rules_id, @RequestBody Rules rules) {
        if ((rules_id == null) || (rules_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Rules id provided.");
        }
        if (rules == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Rules data provided.");
        }

        if (rules.isValid()) {
            Rules result = service.update(rules_id, rules);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. Rules not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Rules data specified.");
    }

    @Operation(summary = "Removes an existing rules")
    @RequestMapping(value = "/delete/{rules_id}", method = RequestMethod.DELETE)
    public Rules delete(@PathVariable String rules_id) {
        if ((rules_id == null) || (rules_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No rules id provided.");
        }

        Rules result = service.delete(rules_id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rules with requested id was not found");
    }
}
