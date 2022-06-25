//Keith Geneva
//Jonathan Rubio
//Bishoy Soliman Hanna
package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.User;

import java.util.List;

public interface UserService {
    /**
     * Returns all users.
     *
     * @param limit The maximum number of users to return.
     * @return A list of all the users.
     */
    List<User> all(int limit);

    /**
     * Gets a user by it's unique identifier.
     *
     * @param userId The unique identifier
     * @return The user if found, otherwise returns null.
     */
    User get(String userId);

    /**
     * Creates a new user.
     *
     * @param input The new user.
     * @return The new user if successful, otherwise returns null.
     */
    User create(User input);

    /**
     * Updates an existing user.
     *
     * @param userId The unique id to update or change.
     * @param input  The updated user information.
     * @return The updated user if successful, otherwise returns null;
     */
    User update(String userId, User input);

    /**
     * Deletes or removes a user.
     *
     * @param userId The unique id of the user to remove.
     * @return The removed user if successful, otherwise returns null.
     */
    User delete(String userId);
}
