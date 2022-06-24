//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Rules;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Tag(name = "Rules")
public interface RulesController {

    int MAX_ITEMS_PER_REQUEST = 500;

    @Operation(summary = "Get all rules")
    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public List<Rules> all();

    @Operation(summary = "Get rules by unique id")
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.GET)
    public Rules get(@PathVariable String rules_id);

    @Operation(summary = "Get rules by game id")
    @RequestMapping(value = "/game/{game_id}/rules", method = RequestMethod.GET)
    public Rules of_a_Game(@PathVariable String game_id);

    @Operation(summary = "Create new rules")
    @RequestMapping(value = "/rules", method = RequestMethod.POST)
    public Rules create(@RequestBody Rules rules);

    @Operation(summary = "Update existing rules")
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.PUT)
    public Rules update(@PathVariable String rules_id, @RequestBody Rules rules);

    @Operation(summary = "Remove existing rules")
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.DELETE)
    public Rules delete(@PathVariable String rules_id);


}
