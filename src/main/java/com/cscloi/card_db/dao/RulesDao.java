package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Rules;

import java.util.Optional;
import java.util.stream.Stream;

public interface RulesDao {

    /**
     * Returns rules for all games.
     *
     * @param limit The maximum number of rules to return.
     * @return A list of all the rules.
     */
    Stream<Rules> all(int limit);

    /**
     * Returns rules owned by a game
     *
     * @param gameID ID of game who owns the rules.
     * @return Rules of the games.
     */
    Optional<Rules> of_a_Game(String gameID);

    /**
     * Gets rules by it's unique identifier.
     *
     * @param rulesID The unique identifier
     * @return The rules if found, otherwise returns null.
     */
    Optional<Rules> get(String rulesID);

    /**
     * Creates new rules.
     *
     * @param input The new rules.
     * @return The new rules if successful, otherwise returns an empty optional.
     */
    Optional<Rules> save(Rules input);

    /**
     * Creates new rules entry.
     *
     * @param rulesID The existing id of the rules to update.
     * @param input  The new rules.
     * @return The new rules if successful, otherwise returns an empty optional.
     */
    Optional<Rules> save(String rulesID, Rules input);

    /**
     * Deletes or removes a rules entry.
     *
     * @param rulesID The unique id of the rules to remove.
     * @return The removed rules if successful, otherwise returns an empty optional.
     */
    Optional<Rules> delete(String rulesID);
}

