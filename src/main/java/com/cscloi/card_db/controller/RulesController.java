//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Rules;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Tag(name = "Rules")
public interface RulesController {

    int MAX_ITEMS_PER_REQUEST = 500;

    @Operation(summary = "Get all rules",
            description = "Returns a list of Rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of Rules is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Rules where found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @RequestMapping(value = "/rules", method = RequestMethod.GET)
    public List<Rules> all();

    @Operation(summary = "Get rules by unique id",
            description = "Returns rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rules are returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Rules were found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "rules_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "The rules id")
            }
    )
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.GET)
    public Rules get(@PathVariable String rules_id);

    @Operation(summary = "Get rules by game id",
            description = "Returns rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rules are returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Rules were found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "game_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "The Game id")
            }
    )
    @RequestMapping(value = "/games/{game_id}/rules", method = RequestMethod.GET)
    public Rules of_a_Game(@PathVariable String game_id);

    @Operation(summary = "Create new rules",
            description = "Returns Rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rules are returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "rules",
                            allowEmptyValue = false,
                            required = false,
                            description = "The Rules to be created")
            }
    )
    @RequestMapping(value = "/rules", method = RequestMethod.POST)
    public Rules create(@RequestBody Rules rules);

    @Operation(summary = "Update existing rules",
            description = "Returns Rules after update",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A Rules is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Deck was found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "rules_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "Id of the Rules to be updated"),
                    @Parameter(
                            name = "rules",
                            allowEmptyValue = false,
                            required = false,
                            description = "The updated Rules")
            }

    )
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.PUT)
    public Rules update(@PathVariable String rules_id, @RequestBody Rules rules);

    @Operation(summary = "Remove existing rules",
            description = "Returns the deleted Rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Rules are returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Rules were found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "rules_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "Id of the rules to be deleted")
            }

    )
    @RequestMapping(value = "/rules/{rules_id}", method = RequestMethod.DELETE)
    public Rules delete(@PathVariable String rules_id);


}
