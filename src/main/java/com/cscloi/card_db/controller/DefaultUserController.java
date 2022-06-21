package com.cscloi.card_db.controller;


import com.cscloi.card_db.entity.User;
import com.cscloi.card_db.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@OpenAPIDefinition(info = @Info(title = "User Service"), servers = {@Server(url = "http://localhost:8080", description = "Local server.")})
@Tag(name = "Users")
@Slf4j
public class DefaultUserController implements UserController{
    private final int MAX_ITEMS_PER_REQUEST = 500;

    //@Autowired
    private UserService service;

    public DefaultUserController(UserService service) {
        this.service = service;
    }

    @Operation(summary = "Get all Users")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> all() {
        List<User> users = service.all(MAX_ITEMS_PER_REQUEST);
        return users;
    }

    @Operation(summary = "Gets user by unique id")
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public User get(@PathVariable String user_id) {

        User user = service.get(user_id);
        if (user != null) {
            return user;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Requested user was not found.");
    }


    @Operation(summary = "Creates a new user")
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User create(@RequestBody User user) {
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user data provided.");
        }

        if (user.isValid()) {
            User result = service.create(user);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. User not saved.");
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid user data specified.");
    }

    @Operation(summary = "Updates or modifies an existing user")
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
    public User update(@PathVariable String user_id, @RequestBody User user) {
        if ((user_id == null) || (user_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No User id provided.");
        }
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user data provided.");
        }

        if (user.isValid()) {
            User result = service.update(user_id, user);
            if (result != null) {
                return result;
            }

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Data persistence failed. User not saved.");
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid User data specified.");
    }

    @Operation(summary = "Removes an existing user")
    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.DELETE)
    public User delete(@PathVariable String user_id) {
        if ((user_id == null) || (user_id.isEmpty())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user id provided.");
        }

        User result = service.delete(user_id);
        if (result != null) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with requested id was not found");
    }
}
