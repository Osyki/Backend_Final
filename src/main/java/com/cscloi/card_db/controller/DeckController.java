//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
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

@Tag(name = "Deck")
public interface DeckController {

    int MAX_ITEMS_PER_REQUEST = 500;

    @Operation(summary = "Get all decks",
            description = "Returns a list of decks",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of decks is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Decks where found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @RequestMapping(value = "/decks", method = RequestMethod.GET)
    public List<Deck> all();


    @Operation(summary = "Get list of decks by user id",
            description = "Returns a list of decks",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of Decks is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Decks where found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            },
            parameters = {
                    @Parameter(
                            name = "user_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "The user id name")
            }
    )
    @RequestMapping(value = "/users/{user_id}/decks", method = RequestMethod.GET)
    public List<Deck> all_for_a_user(@PathVariable String user_id);


    @Operation(summary = "Gets deck by unique id",
            description = "Returns a deck",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A Deck is returned",
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
                            name = "deck_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "The deck id")
            }
    )
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.GET)
    public Deck get(@PathVariable String deck_id);


    @Operation(summary = "Create new deck",
            description = "Returns a Deck",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A Deck is returned",
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
                            name = "deck",
                            allowEmptyValue = false,
                            required = false,
                            description = "The Deck to be created")
            }
    )
    @RequestMapping(value = "/decks", method = RequestMethod.POST)
    public Deck create(@RequestBody Deck deck);


    @Operation(summary = "Update existing deck",
            description = "Returns a Deck after update",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A Deck is returned",
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
                            name = "deck_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "Id of the deck to be updated"),
                    @Parameter(
                            name = "deck",
                            allowEmptyValue = false,
                            required = false,
                            description = "The updated Deck")
            }

    )
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.PUT)
    public Deck update(@PathVariable String deck_id, @RequestBody Deck deck);


    @Operation(summary = "Remove existing deck",
            description = "Returns the deleted Deck",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A Deck is returned",
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
                            name = "deck_id",
                            allowEmptyValue = false,
                            required = false,
                            description = "Id of the deck to be deleted")
            }

    )
    @RequestMapping(value = "/decks/{deck_id}", method = RequestMethod.DELETE)
    public Deck delete(@PathVariable String deck_id);

}
