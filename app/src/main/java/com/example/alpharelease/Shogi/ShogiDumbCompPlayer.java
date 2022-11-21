package com.example.alpharelease.Shogi;

import android.util.Log;

import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;

import java.util.ArrayList;
import java.util.Random;

public class ShogiDumbCompPlayer extends GameComputerPlayer {

    private ShogiGameState state;

    public ShogiDumbCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        state = (ShogiGameState) info;

        if (state.getWhoseTurn() == this.playerNum) { //The computers turn

            sleep(5);

            Random rand = new Random();
            Board board = state.getBoard();
            Tile fromThisTile = null, goToTile = null;
            ArrayList<Piece> pieces = state.getPieceArray(this.playerNum);
            int randIndex = rand.nextInt(pieces.size());

            while (pieces.get(randIndex) == null || !pieces.get(randIndex).isAlive() ||
                    !pieces.get(randIndex).isOnBoard()) {
                //Chosen piece either is null, dead, or not on board so try again
                randIndex = rand.nextInt(pieces.size());
            }
            fromThisTile = board.getTile(pieces.get(randIndex).getCol(), pieces.get(randIndex).getRow());

            game.sendAction(new SelectPieceAction(this, fromThisTile.getTileIndex()));
            board.checkMoves(fromThisTile);

            Log.i("COMP_SELECTED_Piece", "Wheeee compy chose a piece");


            randIndex = rand.nextInt(board.getTiles().size());

            while (!board.getTile(randIndex).isPossible()) { //Pick a random valid spot
                randIndex = rand.nextInt(board.getTiles().size());
            }
            goToTile = board.getTile(randIndex);

            game.sendAction(new MovePieceAction(this, randIndex));
        }
    }
}
