package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.User;

import java.util.List;

public interface DeckService {
    /**
     * Returns all decks.
     *
     * @param limit The maximum number of decks to return.
     * @return A list of all the decks.
     */
    List<Game> all(int limit);
    /**
     * Returns all decks owned by a user.
     *
     * @param limit  The maximum number of decks to return.
     * @param userID The user that owns the decks
     * @return A list of all the decks owned by a user.
     */
    List<Deck> all_for_a_user(int limit, String userID);

    /**
     * Gets a deck by it's unique identifier.
     *
     * @param deckID The unique identifier
     * @return The deck if found, otherwise returns null.
     */
    Deck get(String deckID);

    /**
     * Creates a new deck.
     *
     * @param input The new deck.
     * @return The new deck if successful, otherwise returns an empty optional.
     */
    Deck create(User input);

    /**
     * Updates a deck.
     *
     * @param deckID The existing id of the deck to update.
     * @param input  The updated deck.
     * @return The updated deck if successful, otherwise returns an empty optional.
     */
    Deck update(String deckID, User input);

    /**
     * Deletes or removes a deck.
     *
     * @param deckID The unique id of the deck to remove.
     * @return The removed deck if successful, otherwise returns an empty optional.
     */
    Deck delete(String deckID);
}
