package com.example.alpharelease.Shogi;

import android.util.Log;
import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.alpharelease.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;
import com.example.alpharelease.R;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ShogiDumbCompPlayer extends GameComputerPlayer {
    /**
     External Citation
     Date: 23 November 2022
     Problem: Computer was incredibly buggy and would stall/crash at irregular intervals
     Resource: Nathaniel Hopper
     Solution: The computer was iterating an incredible number of times.
                Made it shuffle through the possible tiles instead of the whole board.
     */

    private ShogiGameState state;
    private ArrayList<Tile> possibleTiles = new ArrayList<>();

    public ShogiDumbCompPlayer(String name) {
        super(name);
    }

    /**
     * Receives information about the gamestate and then makes a random move based on that
     *
     * @param info the information typically containing the gamestate
     */
    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof ShogiGameState) {
            state = new ShogiGameState((ShogiGameState) info);

            if (state.getWhoseTurn() != this.playerNum) {
                Log.d("dumbComputer", "It's not the computer's turn.");
                return;
            } // Not Dumb Computer Player's turn

            if (state.getWhoseTurn() == this.playerNum) { //The computers turn
                sleep(1);

                Random rand = new Random();
                Board board = state.getBoard();
                Tile fromThisTile = null, goToTile = null;
                ArrayList<Piece> pieces = state.getPieceArray(this.playerNum);
                int randIndex = rand.nextInt(pieces.size());
                if (state.isSelecting()) {
                    while (pieces.get(randIndex) == null || !pieces.get(randIndex).isAlive() ||
                            !pieces.get(randIndex).isOnBoard()) {
                        //Chosen piece either is null, dead, or not on board so try again
                        randIndex = rand.nextInt(pieces.size());
                    }

                    fromThisTile = board.getTile(pieces.get(randIndex).getCol(), pieces.get(randIndex).getRow());

                    game.sendAction(new SelectPieceAction(this, fromThisTile.getTileIndex()));

                    board.checkMoves(fromThisTile);
                    possibleTiles.addAll(board.getPossibleTiles());
                    state.setSelecting(false);

                    Log.d("dumbComputer", "Computer Player chose " + fromThisTile.getTileIndex());

                    Collections.shuffle(possibleTiles);

                    if (possibleTiles.size() == 0) {
                        state.setSelecting(true);
                        return;
                    }

                    goToTile = possibleTiles.get(0);

                    int index = board.getTiles().indexOf(goToTile);
                    //Log.d("computerTrack", "Computer sent a piece to: " + goToTile.getTileIndex());
                    Log.d("dumbComputer", "Computer Player sent piece to " + goToTile.getTileIndex());

                    game.sendAction(new MovePieceAction(this, index));
                    possibleTiles.clear();

                    sendInfo(state);
                }
            }
        } else if (info instanceof NotYourTurnInfo) {
            state.setSelecting(true);
            Log.d("dumbComputer", "It's not the computer's turn.");
            this.sendInfo(state);
            return;

        } else if (info instanceof IllegalMoveInfo) {
            state.setSelecting(false);
            Log.d("dumbComputer", "Computer Player attempted illegal move.");
            this.sendInfo(state);
            return;

        } else {
            Log.d("dumbComputer", "Computer Player unknown error.");
            return;
        }

        Log.d("dumbCheck", "Game Info Class returned: " + info.getClass());
    }
}
