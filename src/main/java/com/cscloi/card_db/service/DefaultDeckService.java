//Keith Geneva
package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.DeckDao;
import com.cscloi.card_db.entity.Deck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DefaultDeckService implements DeckService {

    @Autowired
    private DeckDao dao;

    @Override
    public List<Deck> all(int limit) {
        if (limit <= 0) {
            return Collections.emptyList();
        }
        if (limit > 1000) {
            limit = 1000;
        }

        return dao.all(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Deck> all_for_a_user(int limit, String userID) {
        if (limit <= 0) {
            return Collections.emptyList();
        }
        if (limit > 1000) {
            limit = 1000;
        }

        return dao.all_for_a_user(limit, userID)
                .collect(Collectors.toList());
    }

    @Override
    public Deck get(String deckID) {
        if ((deckID == null) || (deckID.isEmpty())) {
            return null;
        }

        Optional<Deck> model = dao.get(deckID);
        if (model.isPresent()) {
            return model.get();
        }

        return null;
    }

    @Override
    public Deck create(Deck input) {
        if (input == null) {
            return null;
        }

        if (input.isValid()) {
            Optional<Deck> model = dao.save(input);
            if (model.isPresent()) {
                return model.get();
            }
        }

        return null;
    }

    @Override
    public Deck update(String deckID, Deck input) {
        Deck existing = get(deckID);
        if (existing == null) {
            return null;
        }

        Optional<Deck> model = dao.save(deckID, input);
        if (model.isPresent()) {
            return model.get();
        }
        return null;
    }

    @Override
    public Deck delete(String deckID) {
        if ((deckID == null) || (deckID.isEmpty())) {
            return null;
        }

        Optional<Deck> existing = dao.delete(deckID);
        if (existing.isPresent()) {
            return existing.get();
        }
        return null;
    }
}
