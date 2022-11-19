package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.Shogi.Piece;

public class movePiece extends GameAction {
    Piece moving;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public movePiece(GamePlayer player, Piece p) {
        super(player);
        moving = p;
    }
}
