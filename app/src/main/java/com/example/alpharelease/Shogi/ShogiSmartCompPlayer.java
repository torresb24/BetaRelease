package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;

public class ShogiSmartCompPlayer extends GameComputerPlayer {
    /**
     * Constructor
     *
     * @param
     *      name the player's name (e.g., "John")
     */
    public ShogiSmartCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) { }
}
