package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.User;

import java.util.Optional;
import java.util.stream.Stream;

public interface CardsService {

    /**
     * Returns all cards.
     *
     * @param limit The maximum number of cards to return.
     * @return A list of all the cards.
     */
    Stream<Game> all(int limit);

    /**
     * Returns all cards owned by a user.
     *
     * @param limit  The maximum number of decks to return.
     * @param deckID The user that owns the decks
     * @return A list of all the users.
     */
    Stream<Deck> all_for_a_user(int limit, String deckID);

    /**
     * Gets a card by it's unique identifier.
     *
     * @param cardID The unique identifier
     * @return The card if found, otherwise returns null.
     */
    Optional<Deck> get(String cardID);

    /**
     * Creates a new card.
     *
     * @param input The new card.
     * @return The new card if successful, otherwise returns an empty optional.
     */
    Optional<Deck> create(Deck input);

    /**
     * Updates a card.
     *
     * @param cardID The existing id of the deck to update.
     * @param input  The updated card.
     * @return The updated card if successful, otherwise returns an empty optional.
     */
    Optional<Deck> update(String cardID, Deck input);

    /**
     * Deletes or removes a card.
     *
     * @param cardID The unique id of the card to remove.
     * @return The removed card if successful, otherwise returns an empty optional.
     */
    Optional<Deck> delete(String cardID);
}

