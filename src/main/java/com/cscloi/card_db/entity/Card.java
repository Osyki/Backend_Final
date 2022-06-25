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
public class Card {

    private Long card_pk;
    private String card_id;
    private Long expansion_fk;
    private String card_name;



    public boolean isValid() {
        if ((getCard_id() == null || getCard_id().isEmpty())) {
            return false;
        }
        if (getExpansion_fk() == null) {
            return false;
        }
        if ((getCard_name() == null) || (getCard_name().isEmpty())) {
            return false;
        }
        return true;
    }
}
