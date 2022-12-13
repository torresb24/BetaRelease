package com.example.alpharelease.Shogi;

import android.util.Log;
import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.R;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.PromoteAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;
import com.example.alpharelease.Shogi.Actions.SurrenderAction;
import com.example.alpharelease.Shogi.Piece;
import com.example.alpharelease.Shogi.ShogiGameState;
import java.util.ArrayList;
/**
 *
 * @author Kathryn Weidman
 * @author Emma Kelly
 * @author Brent Torres
 * @author Matthew Tran
 *
 * @version 12/13/2022
 *
 * */
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
        ShogiGameState state = ((ShogiGameState)super.state);
        if(state.isInCheckmate()){
            if(state.getWhoseTurn() == 0) {
                return "Second Player Wins! ";
            }
            if(state.getWhoseTurn() == 1) {
                return "First Player Wins! ";
            }
        }
        return null;
    }

    /**
     * Determine whether selected space is a valid move for selected piece
     *
     * @param action
     *     the player interaction that selects the piece
     * @return boolean
     *     true - if the selected move is inside bounds/valid
     *     false - if the selected movie is outside bounds/invalid
     *
     */
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
            int check = 0;
            // Get orig tile
            if (check == 0) {
                for (Tile t : board.getTiles()) {
                    if (t.getPiece() != null) {
                        if (t.getPiece().isSelected()) {
                            fromHere = t;
                            check = 1;
                            break;
                        }
                    }
                }
            } //chck
            if (check == 0) {
                for (Tile t : board.getGrave0()) {
                    if (t.getPiece() != null) {
                        if (t.getPiece().isSelected()) {
                            fromHere = t;
                            check = 1;
                            break;
                        }
                    }
                }
            } //chck
            if (check == 0) {
                for (Tile t : board.getGrave1()) {
                    if (t.getPiece() != null) {
                        if (t.getPiece().isSelected()) {
                            fromHere = t;
                            check = 1;
                            break;
                        }
                    }
                }
            } // chck
            // [End] get orig tile

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

                Piece p = goThere.getPiece();
                if (p != null) {
                    p.setAlive(false);
                    p.setOnBoard(false);
                    p.changeTeams();
                    board.addToGrave(p,state.getWhoseTurn());
                }

                state.changeTurn(1 - fromHere.getPiece().getThePlayer()); //Change turn
                if(!fromHere.getPiece().isAlive()){
                    fromHere.getPiece().setAlive(true);
                    // change moveset since changed team
                    fromHere.getPiece().setMoveNumAfterDrop();
                    fromHere.getPiece().changeDirection();
                }
                if(!fromHere.getPiece().isOnBoard()){
                    fromHere.getPiece().setOnBoard(true);
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

        if (action instanceof PromoteAction){
            fromHere = board.getTile(((PromoteAction) action).SelectedIndex);

            if(!board.canPromote(fromHere)){
                return false;
            }

            board.promote(fromHere, state,state.getWhoseTurn());
            state.changeTurn(1 - fromHere.getPiece().pieceType.getPlayer()); //Change turn
            fromHere.getPiece().setSelected(false);
            fromHere = null;
            state.setSelecting(true);
            board.impossAllTiles();
            return true;
        }
        //TODO: Make other actions (post-promotion, not yet implemented)
        return false;
    }

    //  Check and check conditions during/after check are not yet implemented.
    public boolean checkCheck() {
        return false;
    }
}
