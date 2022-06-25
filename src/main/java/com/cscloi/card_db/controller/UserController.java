//Keith Geneva
//Jonathan Rubio
//Bishoy Soliman Hanna
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Rules;
import com.cscloi.card_db.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "Game Database"), servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
@Tag(name = "User")
public interface UserController {

    int MAX_ITEMS_PER_REQUEST = 500;

    @Operation(summary = "Get all users",
            description = "Returns a list of users",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A list of users is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Rules.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No users where found with given criteria",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "500",
                            description = "An unplanned error occurred",
                            content = @Content(mediaType = "application/json"))
            }
    )
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> all();

    @Operation(summary = "Get user by unique id",
            description = "Returns rules",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Users is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No Users were found with given criteria",
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
                            description = "The user id")
            }
    )
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET)
    public User get(@PathVariable String user_id);

    @Operation(summary = "Create new user",
            description = "Returns User",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User is returned",
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
                            name = "user",
                            allowEmptyValue = false,
                            required = false,
                            description = "The user to be created")
            }
    )
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user);

    @Operation(summary = "Update existing user",
            description = "Returns a user after update",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A user is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No user was found with given criteria",
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
                            description = "Id of the user to be updated"),
                    @Parameter(
                            name = "user",
                            allowEmptyValue = false,
                            required = false,
                            description = "The updated User")
            }

    )
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
    public User update(@PathVariable String user_id, @RequestBody User user);

    @Operation(summary = "Remove existing user",
            description = "Returns the deleted user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "A user is returned",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Deck.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Request parameters are invalid",
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No user was found with given criteria",
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
                            description = "Id of the user to be deleted")
            }

    )
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable String user_id);

}