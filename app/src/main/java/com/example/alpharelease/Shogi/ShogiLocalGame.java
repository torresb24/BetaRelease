package com.example.alpharelease.Shogi;

import android.util.Log;

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
        ArrayList<Tile> possibleTiles = new ArrayList<>();
        Tile fromHere = null;

        if (action instanceof SelectPieceAction) {
            int tileIndex = ((SelectPieceAction)action).selected;
            fromHere = board.getTile(tileIndex);
            Piece piece = fromHere.getPiece();

            board.checkMoves(fromHere);
            possibleTiles.addAll(board.getPossibleTiles());
            piece.setSelected(possibleTiles.size() != 0); //False if this piece can't move.

            if (piece.isSelected()) {
                state.setSelecting(false);

                Log.d("SELECTED A PIECE", "Selected a piece! " + fromHere.getTileIndex() +
                        " and turn is " + state.getWhoseTurn());
                return true;
            }

            Log.d("SELECTED A PIECE (BAD)", "Selected bad!!! " + fromHere.getTileIndex() +
                    " and turn is " + state.getWhoseTurn());

            return false;  //This piece can't move. Try again
        }

        if (action instanceof MovePieceAction) {
            Tile goThere = board.getTile(((MovePieceAction) action).destination);
            for (Tile t : board.getTiles()) {
                if (t.getPiece() != null) {
                    if (t.getPiece().isSelected()) {
                        fromHere = t;
                        break;
                    }
                }
            }

            if (fromHere == null || goThere == null) {
                return false;
            }

            board.checkMoves(fromHere);
            possibleTiles.addAll(board.getPossibleTiles());

            if (possibleTiles.contains(goThere)) {

                if (goThere.isOccupied() && (goThere.getPiece().pieceType == Piece.GAME_PIECES.KING
                        || goThere.getPiece().pieceType == Piece.GAME_PIECES.OPP_KING)) {
                    state.setInCheckmate(true);
                } //They snatched the king!

                state.changeTurn(1 - fromHere.getPiece().pieceType.getPlayer()); //Change turn

                Piece p = goThere.getPiece();
                if (p != null) {
                    p.setAlive(false);
                    p.setOnBoard(false);
                }
                goThere.setPiece(fromHere.getPiece());
                fromHere.getPiece().setSelected(false);
                fromHere.setPiece(null);

                board.impossAllTiles();
                state.setSelecting(true);

                Log.d("MOVE A PIECE", "Moved to "  + goThere.getTileIndex() +
                        " and turn is " + state.getWhoseTurn());

                return true;
            }

            Log.d("MOVE A PIECE (BAD)", "Tried to move to "  + goThere.getTileIndex() +
                    " and turn is " + state.getWhoseTurn());

            state.setSelecting(false);
            return false; //Can't move because chosen place is invalid
        } //End of movepieceaction case

        //TODO: MAKE OTHER ACTIONS
        return false;
    }

    public boolean checkCheck() {
        return false;
    }
}
