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
}
