package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;

public class moveBishop extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public moveBishop(GamePlayer player) {
        super(player);
    }
}