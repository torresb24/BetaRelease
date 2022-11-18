package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;

public class ShogiComputerPlayer extends GameComputerPlayer {

    public ShogiComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //TODO: Send move actions to the game with game.sendAction(new Shogi-Something-Action)

    }
}
