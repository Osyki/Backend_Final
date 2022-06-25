////////////// done by Bishoy SOliman Hanna ///////////////////

package com.cscloi.card_db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Expansion {
    private Long expansion_pk;
    private String expansion_id;
    private Long game_fk;
    private String expansion_name;

    public boolean isValid() {
        if ((getExpansion_id() == null || getExpansion_id().isEmpty())) {
            return false;
        }
        if (getGame_fk() == null) {
            return false;
        }
        if ((getExpansion_name() == null) || (getExpansion_name().isEmpty())) {
            return false;
        }
        return true;
    }
}
