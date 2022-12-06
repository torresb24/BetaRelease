package com.example.alpharelease.Shogi.Actions;

import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;

public class MovePieceAction extends GameAction {

    public int destination;

    /**
     * Constructor for GameAction
     *
     * @param player the player who created the action
     */
    public MovePieceAction(GamePlayer player, int destinationIndex) {
        super(player);
        destination = destinationIndex;
    }
}
