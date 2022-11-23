package com.example.alpharelease.Shogi;

import android.util.Log;

import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.alpharelease.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ShogiDumbCompPlayer extends GameComputerPlayer {

    private ShogiGameState state;
    private ArrayList<Tile> possibleTiles = new ArrayList<Tile>();;

    public ShogiDumbCompPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {

        if (info instanceof ShogiGameState) {
            state = new ShogiGameState((ShogiGameState) info);

            if (state.getWhoseTurn() != this.playerNum) {
                Log.d("NOT YOUR TURN, COMPY", "It's not the computer's turn");
                return;
            } //Not compy's turn

            if (state.getWhoseTurn() == this.playerNum) { //The computers turn

                sleep(.5);

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

                    Log.d("COMP_SELECTED_PIECE", "Wheeee compy chose " + fromThisTile.getTileIndex());

                    Collections.shuffle(possibleTiles);

                    if (possibleTiles.size() == 0) {
                        state.setSelecting(true);
                        return;
                    }
                    goToTile = possibleTiles.get(0);

                    int index = board.getTiles().indexOf(goToTile);
                    Log.d("COMP_SELECTED_PLACE", "Wheeee compy sent piece to " + goToTile.getTileIndex());

                    game.sendAction(new MovePieceAction(this, index));
                    possibleTiles.clear();

                    sendInfo(state);
                }
            }
        } else if (info instanceof NotYourTurnInfo) {
            state.setSelecting(true);
            Log.d("NOT COMPUTER TURN", "It was not computer's turn!");
            this.sendInfo(state);
            return;

        } else if (info instanceof IllegalMoveInfo) {
            state.setSelecting(false);
            Log.d("ILLEGAL COMPUTER MOVE", "Woah, hey that's illegal!");
            this.sendInfo(state);
            return;

        } else {
            Log.d("COMPUTER IDK", "What the hell?");
            return;
        }

        Log.d("WTF", "Uhhhh idk man " + info.getClass());
    }
}
