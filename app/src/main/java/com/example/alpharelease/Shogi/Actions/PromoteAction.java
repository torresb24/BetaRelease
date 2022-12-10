package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;

public class PromoteAction extends GameAction {

    /**
     * Constructor for GameAction
     *
     * @param player the player who created the action
     */
    public int SelectedIndex;
    public PromoteAction(GamePlayer player, int selectedIndex) {
        super(player);
        this.SelectedIndex = selectedIndex;
    }
}
