package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Game;

import java.util.Optional;
import java.util.stream.Stream;

public interface DeckDao {

    /**
     * Returns all decks.
     *
     * @param limit The maximum number of decks to return.
     * @return A list of all the decks.
     */
    Stream<Deck> all(int limit);
    /**
     * Returns all decks owned by a user.
     *
     * @param limit  The maximum number of decks to return.
     * @param userID The user that owns the decks
     * @return A list of all the decks owned by a user.
     */
    Stream<Deck> all_for_a_user(int limit, String userID);

    /**
     * Gets a deck by it's unique identifier.
     *
     * @param deckID The unique identifier
     * @return The deck if found, otherwise returns null.
     */
    Optional<Deck> get(String deckID);

    /**
     * Creates a new deck.
     *
     * @param input The new deck.
     * @return The new deck if successful, otherwise returns an empty optional.
     */
    Optional<Deck> save(Deck input);

    /**
     * Creates a new deck.
     *
     * @param deckID The existing id of the deck to update.
     * @param input  The new deck.
     * @return The new user if successful, otherwise returns an empty optional.
     */
    Optional<Deck> save(String deckID, Deck input);

    /**
     * Deletes or removes a deck.
     *
     * @param deckID The unique id of the deck to remove.
     * @return The removed deck if successful, otherwise returns an empty optional.
     */
    Optional<Deck> delete(String deckID);
}