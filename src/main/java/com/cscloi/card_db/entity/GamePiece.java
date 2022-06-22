package com.cscloi.card_db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GamePiece {
    private String gamePiecePK;
    private String gamePieceName;
    private String gamePieceDesc;
    private String gamePieceFK;

    public boolean isValid() {
        if (getGamePiecePK() == null || getGamePiecePK().isEmpty()) {
            return false;
        }
        return getGamePieceName() != null && !getGamePieceName().isEmpty();
    }
}
