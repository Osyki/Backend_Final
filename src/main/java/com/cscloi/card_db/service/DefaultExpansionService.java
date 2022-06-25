////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.service;

import com.cscloi.card_db.dao.CardsDao;
import com.cscloi.card_db.dao.ExpansionDao;
import com.cscloi.card_db.entity.Card;
import com.cscloi.card_db.entity.Expansion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class DefaultExpansionService implements ExpansionService {

    @Autowired
    private ExpansionDao dao;


    @Override
    public List<Expansion> all(int limit) {
        if (limit <= 0) {
            return Collections.emptyList();
        }
        if (limit > 1000) {
            limit = 1000;
        }
        return dao.all(limit);
    }

    @Override
    public List<Expansion> all_of_a_Game(String gameID) {
        if (gameID == null || gameID.isEmpty()) {
            return null;

        } else {
            List<Expansion> result = dao.all_of_a_Game(gameID);
            if (!result.isEmpty()) {
                return result;
            } else {
                return null;
            }
        }
    }

    @Override
    public Expansion get(String expansionID) {
        if ((expansionID == null) || (expansionID.isEmpty())) {
            return null;
        }

        Expansion model = dao.get(expansionID);
        if (model.isValid()) {
            return model;
        }

        return null;
    }

    @Override
    public Expansion create(Expansion input) {
        if (input == null) {
            return null;
        }

        if (input.isValid()) {
            return dao.save(input);
        }

        throw new RuntimeException("Could not create new card.");
    }

    @Override
    public Expansion update(String expansionID, Expansion input) {
        Expansion existing = get(expansionID);
        if (existing == null) {
            return null;
        }

        Expansion model = dao.save(expansionID, input);
        if (model.isValid()) {
            return model;
        }
        return null;
    }

    @Override
    public Expansion delete(String expansionID) {
        if ((expansionID == null) || (expansionID.isEmpty())) {
            return null;
        }
        Expansion existing = dao.delete(expansionID);
        if (existing.isValid()) {
            return existing;
        }
        return null;
    }
}
