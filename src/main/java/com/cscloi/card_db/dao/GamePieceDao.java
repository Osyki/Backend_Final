/**
 * @author Jonathan Rubio
 */

package com.cscloi.card_db.dao;

import com.cscloi.card_db.entity.GamePiece;

import java.util.List;

public interface GamePieceDao {

    List<GamePiece> all(int limit);

    GamePiece get(String id);

    List<GamePiece> get(int limit, String gameID);

    GamePiece save(GamePiece gamePiece);

    GamePiece save(String gamePiecePK, GamePiece gamePiece);

    GamePiece delete(String id);
}
