package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.User;

import java.util.Optional;
import java.util.stream.Stream;

public interface UserDao {
    /**
     * Returns all users.
     *
     * @param limit The maximum number of users to return.
     * @return A list of all the users.
     */
    Stream<User> all(int limit);

    /**
     * Gets a user by it's unique identifier.
     *
     * @param userID The unique identifier
     * @return The user if found, otherwise returns null.
     */
    Optional<User> get(String userID);

    /**
     * Creates a new user.
     *
     * @param input The new user.
     * @return The new user if successful, otherwise returns an empty optional.
     */
    Optional<User> save(User input);

    /**
     * Creates a new user.
     *
     * @param userID The existing id of the user to update.
     * @param input  The new user.
     * @return The new user if successful, otherwise returns an empty optional.
     */
    Optional<User> save(String userID, User input);

    /**
     * Deletes or removes a user.
     *
     * @param userID The unique id of the user to remove.
     * @return The removed user if successful, otherwise returns an empty optional.
     */
    Optional<User> delete(String userID);
}
