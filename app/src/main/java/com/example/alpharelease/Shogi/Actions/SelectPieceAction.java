package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.Shogi.Piece;

public class SelectPieceAction extends GameAction {
    public Piece selected;

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPieceAction(GamePlayer player, Piece selected) {
        super(player);
        this.selected = selected;
    }
}
