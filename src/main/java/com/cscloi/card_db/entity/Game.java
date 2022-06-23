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
    private String game_fk;
    private String game_name;
    private String creator_name;

    public boolean isValid() {
        return true;
    }
}