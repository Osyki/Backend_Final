//Keith Geneva
package com.cscloi.card_db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rules {
    private String rules_id;
    private String game_id;
    private String rule_text;

    public boolean isValid() {
        if ((getRules_id() == null || getRules_id().isEmpty())) {return false;}
        if (getGame_id() == null) {return false;}
        if ((getRule_text() == null) || (getRule_text().isEmpty())) {return false;}
        return true;
    }
}