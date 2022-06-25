////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Expansion;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ExpansionDao {

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
     *
     * @return A list of all the expansions.
     */
    List<Expansion> all_of_a_Game(String gameID);

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
    Expansion save(Expansion input);

    /**
     * Creates a new expansion.
     *
     * @param expansionID The existing id of the expansion to update.
     * @param input  The new expansion.
     * @return The new expansion if successful, otherwise returns an empty optional.
     */
    Expansion save(String expansionID, Expansion input);

    /**
     * Deletes or removes a expansion.
     *
     * @param expansionID The unique id of the expansion to remove.
     * @return The removed expansion if successful, otherwise returns an empty optional.
     */
    Expansion delete(String expansionID);
}

