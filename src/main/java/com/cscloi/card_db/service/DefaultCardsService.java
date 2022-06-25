////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.CardsDao;
import com.cscloi.card_db.dao.RulesDao;
import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Rules;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultCardsService implements CardsService {

    @Autowired
    private CardsDao dao;


    @Override
    public List<Card> all(int limit) {
        if (limit <= 0) {
            return Collections.emptyList();
        }
        if (limit > 1000) {
            limit = 1000;
        }
        return dao.all(limit);
    }

    @Override
    public List<Card> all_for_a_user(String deckID) {
        if (deckID == null || deckID.isEmpty()) {
            return null;

        } else {
            List<Card> result = dao.all_for_a_deck(deckID);
            if (!result.isEmpty()) {
                return result;
            } else {
                return null;
            }
        }

    }

    @Override
    public Card get(String cardID) {
        if ((cardID == null) || (cardID.isEmpty())) {
            return null;
        }

        Card model = dao.get(cardID);
        if (model.isValid()) {
            return model;
        }

        return null;
    }

    @Override
    public Card create(Card input) {
        if (input == null) {
            return null;
        }

        if (input.isValid()) {
            return dao.save(input);
        }

        throw new RuntimeException("Could not create new card.");

    }

    @Override
    public Card update(String cardID, Card input) {
        Card existing = get(cardID);
        if (existing == null) {
            return null;
        }

        Card model = dao.save(cardID, input);
        if (model.isValid()) {
            return model;
        }
        return null;
    }

    @Override
    public Card delete(String cardID) {
        if ((cardID == null) || (cardID.isEmpty())) {
            return null;
        }
        Card existing = dao.delete(cardID);
        if (existing.isValid()) {
            return existing;
        }
        return null;
    }

}
