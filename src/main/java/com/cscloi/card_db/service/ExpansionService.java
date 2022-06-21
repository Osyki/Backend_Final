package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.Expansion;
import com.cscloi.card_db.entity.User;

import java.util.List;

public interface ExpansionService {

    /**
     * Returns all expansions.
     *
     * @param limit The maximum number of expansions to return.
     * @return A list of all the expansions.
     */
    List<Expansion> all(int limit);

    /**
     * Returns all expansions for a game.
     *
     * @param limit The maximum number of expansions to return. gameID The game that owns the decks
     * @return A list of all the expansions.
     */
    List<Expansion> all_of_a_Game(int limit, String gameID);

    /**
     * Gets a expansion by it's unique identifier.
     *
     * @param expansionID The unique identifier
     * @return The expansion if found, otherwise returns null.
     */
    Expansion get(String expansionID);

    /**
     * Creates a new expansion.
     *
     * @param input The new expansion.
     * @return The new expansion if successful, otherwise returns an empty optional.
     */
    Expansion create(Expansion input);

    /**
     * Updates an expansion.
     *
     * @param expansionID The existing id of the expansion to update.
     * @param input       The updated expansion.
     * @return The new expansion if successful, otherwise returns an empty optional.
     */
    Expansion update(String expansionID, Expansion input);

    /**
     * Deletes or removes a expansion.
     *
     * @param expansionID The unique id of the expansion to remove.
     * @return The removed expansion if successful, otherwise returns an empty optional.
     */
    Expansion delete(String expansionID);
}