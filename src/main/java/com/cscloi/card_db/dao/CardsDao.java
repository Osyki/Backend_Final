package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Card;

import java.util.Optional;
import java.util.stream.Stream;

public interface CardsDao {


    /**
     * Returns all cards.
     *
     * @param limit The maximum number of cards to return.
     * @return A list of all the cards.
     */
    Stream<Card> all(int limit);

    /**
     * Returns all cards owned by a user.
     *
     * @param limit  The maximum number of decks to return.
     * @param deckID The user that owns the decks
     * @return A list of all the users.
     */
    Stream<Card> all_for_a_deck(int limit, String deckID);

    /**
     * Gets a card by it's unique identifier.
     *
     * @param cardID The unique identifier
     * @return The card if found, otherwise returns null.
     */
    Optional<Card> get(String cardID);

    /**
     * Creates a new card.
     *
     * @param input The new card.
     * @return The new card if successful, otherwise returns an empty optional.
     */
    Optional<Card> save(Card input);

    /**
     * Creates a new card.
     *
     * @param cardID The existing id of the deck to update.
     * @param input  The new card.
     * @return The new card if successful, otherwise returns an empty optional.
     */
    Optional<Card> save(String cardID, Card input);

    /**
     * Deletes or removes a card.
     *
     * @param cardID The unique id of the card to remove.
     * @return The removed card if successful, otherwise returns an empty optional.
     */
    Optional<Card> delete(String cardID);
}
