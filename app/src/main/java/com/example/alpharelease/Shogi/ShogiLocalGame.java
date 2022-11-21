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
        Tile fromHere = null;

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

            if (goThere.isPossible()) {

                if (goThere.isOccupied() && (goThere.getPiece().pieceType == Piece.GAME_PIECES.KING
                        || goThere.getPiece().pieceType == Piece.GAME_PIECES.OPP_KING)) {
                    state.setInCheckmate(true);
                } //They snatched the king!

                board.impossAllTiles();
                Piece p = goThere.getPiece();
                if (p != null) {
                    p.setAlive(false);
                    p.setOnBoard(false);
                }
                goThere.setPiece(fromHere.getPiece());
                fromHere.getPiece().setSelected(false);
            }

            fromHere.setPiece(null);

            state.changeTurn(1 - state.getWhoseTurn());

            return goThere.isOccupied();
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
