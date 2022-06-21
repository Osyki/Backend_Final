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
    private Long game_id;
    private String rule_text;

    public String getRules_id() {return rules_id;}
    public void setRules_id(String rules_id) {this.rules_id = rules_id;}
    public Long getGame_id() {return game_id;}
    public void setGame_id(Long game_id) {this.game_id = game_id;}
    public String getRule_text() {return rule_text;}
    public void setRule_text(String rule_text) {this.rule_text = rule_text;}

    public Rules(String rules_id, String rule_text, Long game_id) {this.rules_id = rules_id;this.rule_text = rule_text; this.game_id = game_id;}

    public boolean isValid() {
        if ((getRules_id() == null || getRules_id().isEmpty())) {return false;}
        if (getGame_id() == null) {return false;}
        if ((getRule_text() == null) || (getRule_text().isEmpty())) {return false;}
        return true;
    }
}