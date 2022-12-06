package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.Shogi.Piece;

public class SelectPieceAction extends GameAction {
    public int selected;

    /**
     * Constructor for GameAction
     *
     * @param player the player who created the action
     */
    public SelectPieceAction(GamePlayer player, int selectedIndex) {
        super(player);
        this.selected = selectedIndex;
    }
}
