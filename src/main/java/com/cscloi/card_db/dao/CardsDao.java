////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.Card;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CardsDao {


    /**
     * Returns all cards.
     *
     * @param limit The maximum number of cards to return.
     * @return A list of all the cards.
     */
    List<Card> all(int limit);

    /**
     * Returns all cards owned by a user.
     *
     * @param deckID The user that owns the decks
     * @return A list of all the users.
     */
    List<Card> all_for_a_deck(String deckID);

    /**
     * Gets a card by it's unique identifier.
     *
     * @param cardID The unique identifier
     * @return The card if found, otherwise returns null.
     */
    Card get(String cardID);

    /**
     * Creates a new card.
     *
     * @param input The new card.
     * @return The new card if successful, otherwise returns an empty optional.
     */
    Card save(Card input);

    /**
     * Creates a new card.
     *
     * @param cardID The existing id of the deck to update.
     * @param input  The new card.
     * @return The new card if successful, otherwise returns an empty optional.
     */
    Card save(String cardID, Card input);

    /**
     * Deletes or removes a card.
     *
     * @param cardID The unique id of the card to remove.
     * @return The removed card if successful, otherwise returns an empty optional.
     */
    Card delete(String cardID);
}
