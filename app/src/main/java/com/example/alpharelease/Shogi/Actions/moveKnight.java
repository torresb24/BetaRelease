package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;

public class moveKnight extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public moveKnight(GamePlayer player) {
        super(player);
    }
}