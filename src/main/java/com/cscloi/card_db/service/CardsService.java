////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.service;

import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Deck;
import com.cscloi.card_db.entity.Game;
import com.cscloi.card_db.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CardsService {

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
    List<Card> all_for_a_user(String deckID);

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
    Card create(Card input);

    /**
     * Updates a card.
     *
     * @param cardID The existing id of the deck to update.
     * @param input  The updated card.
     * @return The updated card if successful, otherwise returns an empty optional.
     */
    Card update(String cardID, Card input);

    /**
     * Deletes or removes a card.
     *
     * @param cardID The unique id of the card to remove.
     * @return The removed card if successful, otherwise returns an empty optional.
     */
    Card delete(String cardID);
}

