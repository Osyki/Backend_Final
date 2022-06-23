/**
 * @author Jonathan Rubio
 * @version 1.0
 * @since 2022-06-23
 */

package com.cscloi.card_db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private String game_pk;
    private String game_id;
    private String game_name;
    private String creator_name;

    public boolean isValid() {
        if (getGame_pk() == null || getGame_pk().isEmpty()) {
            return false;
        }
        return getGame_name() != null && !getGame_name().isEmpty();
    }
}