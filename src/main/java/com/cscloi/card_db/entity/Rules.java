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
    private Long rules_pk;
    private String rules_id;
    private Long game_fk;
    private String rule_text;
}