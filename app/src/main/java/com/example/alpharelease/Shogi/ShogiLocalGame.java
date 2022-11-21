package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.R;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;
import com.example.alpharelease.Shogi.Actions.SurrenderAction;
import com.example.alpharelease.Shogi.Piece;
import com.example.alpharelease.Shogi.ShogiGameState;

import java.util.ArrayList;

public class ShogiLocalGame extends LocalGame {

    Tile fromHere;

    public ShogiLocalGame() {
        super();
        super.state = new ShogiGameState();
    }

    public ShogiLocalGame(ShogiGameState estate) {
        super();
        super.state = new ShogiGameState(estate);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new ShogiGameState((ShogiGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return playerIdx == ((ShogiGameState)state).getWhoseTurn();
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        ShogiGameState state = ((ShogiGameState)super.state);
        Board board = state.getBoard();

        if (action instanceof SelectPieceAction) {
            int tileIndex = ((SelectPieceAction)action).selected;
            fromHere = board.getTile(tileIndex);
            Piece piece = fromHere.getPiece();

            //If it's stuck (just in case)
            if (board.checkMoves(fromHere).size() == 0) {
                piece.setSelected(false); //Can't move. Give up.
            } else { //If it can move
                piece.setSelected(true);
            }
            return piece.isSelected();
        }

        if (action instanceof MovePieceAction) {
            Tile goThere = board.getTile(((MovePieceAction)action).destination);

            if (fromHere == null || goThere == null) {
                return false;
            }

            if (fromHere.isOccupied()) {
                Piece p = fromHere.getPiece();
                p.setAlive(false);
                p.setOnBoard(false);
            }

            goThere.setPiece(fromHere.getPiece());
            goThere.setOccupied(true);

            fromHere.setPiece(null);
            fromHere.setOccupied(false);

            state.changeTurn(1 - state.getWhoseTurn());
        }



        //TODO: MAKE OTHER ACTIONS
        return false;
    }

    public boolean checkMate() {
        if  (((ShogiGameState) state).pieces2.size() == 1) {
            return true;
        }

        return false;
    }
}
