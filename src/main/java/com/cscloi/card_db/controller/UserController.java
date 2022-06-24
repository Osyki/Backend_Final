//Keith Geneva
package com.cscloi.card_db.controller;

import com.cscloi.card_db.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
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

    @Operation(summary = "Get all users")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> all();

    @Operation(summary = "Get user by unique id")
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.GET)
    public User get(@PathVariable String user_id);

    @Operation(summary = "Create new user")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user);

    @Operation(summary = "Update existing user")
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
    public User update(@PathVariable String user_id, @RequestBody User user);

    @Operation(summary = "Remove existing user")
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable String user_id);

}