package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.infoMessage.GameState;
import com.example.alpharelease.R;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kathryn Weidman
 * @author Emma Kelly
 * @author Brent Torres
 * @author Matthew Tran
 *
 * @version 11/22/2022
 *
 * */

public class ShogiGameState extends GameState {
    /**
     External Citation
     Date: 18 November 2022
     Problem: Didn't understand how enum worked super well so code
     involving pieces was unsustainable and clumsy
     Resource: Nathaniel Hopper
     Solution: Reference the game_piece type rather than it's R.drawable id
     */

    private boolean isInCheck, isInCheckmate, selecting;
    private final Board board;
    private int whoseTurn;
    private final Graveyard grave_1;
    private final Graveyard grave_2;
    public ArrayList<Piece> pieces1;
    public ArrayList<Piece> pieces2;
    public ArrayList<Tile> tileArray;

    /**
     * Current state of the game constructor
     */
    public ShogiGameState() { //Cntr
        whoseTurn = first();
        board = new Board();
        grave_1 = new Graveyard();
        grave_2 = new Graveyard();
        pieces1 = new ArrayList<>();
        pieces2 = new ArrayList<>();
        tileArray = new ArrayList<>();
        selecting = true;

        assignPieces();
    }

    /**
     * Current state of the game deep copy constructor
     */
    public ShogiGameState(ShogiGameState orig) {
        this.whoseTurn = orig.whoseTurn;
        this.board = orig.board;
        this.selecting = orig.selecting;
        this.grave_1 = orig.grave_1;
        this.grave_2 = orig.grave_2;
        this.pieces1 = new ArrayList<>();
        this.pieces2 = new ArrayList<>();
        this.tileArray = new ArrayList<>();

        // for loop through
        this.pieces1.addAll(orig.pieces1);
        this.pieces2.addAll(orig.pieces2);
        this.tileArray.addAll(orig.tileArray);
    }

    /**
     * creates an arraylist of pieces for each player
     */
    private void assignPieces() {
        for (Piece.GAME_PIECES piece : Piece.GAME_PIECES.values()) {
            for (int i = 0; i < piece.getAmount(); i++) {
                switch (piece.getPlayer()) {
                    case 0:
                        pieces1.add(new Piece(piece, Piece.DIRECTION.FORWARD)); //player 1
                        break;
                    case 1:
                        pieces2.add(new Piece(piece, Piece.DIRECTION.BACKWARD)); //player 2
                        break;
                }
            } // for i
        } // for pieces
        initPieces(pieces1);
        initPieces(pieces2);
    }


    /**
     * Assigns rows and columns to each piece for initial setup for each player
     * Promotion pieces are unassigned due to them not existing on the board at that time
     *
     * @param piece the arraylist belonging to a specific player containing all their pieces
     */
    private void initPieces(ArrayList<Piece> piece) { //board is 9x9 tiles
        //front row is 9 pawns
        //middle row is 1 space, bishop, 5 spaces, rook, 1 space (left to right from players pov)
        //back row is lance, knight, silver, gold, king, gold, silver, knight, lance
        int pawnNum = 0, lanceNum = 0, knightNum = 0, goldNum = 0, silvNum = 0;
        int backRow = -1, middleRow = -1, frontRow = -1, bishopCol = -1, rookCol = -1;

        switch (piece.get(0).directionMovement) {
            case FORWARD: //Forward facing pieces
                backRow = 8;
                middleRow = 7;
                frontRow = 6;
                bishopCol = 1;
                rookCol = 7;
                break;
            case BACKWARD: //Backward facing pieces
                backRow = 0;
                middleRow = 1;
                frontRow = 2;
                bishopCol = 7;
                rookCol = 1;
                break;
        }
        for (Piece p : piece) {
            switch (p.pieceType) { //What kind of piece is it
                case PROMOTED_BISHOP: case PROMOTED_LANCE: case PROMOTED_KNIGHT:
                case PROMOTED_PAWN: case PROMOTED_ROOK: case PROMOTED_SILVER_GENERAL:
                case OPP_PROMOTED_BISHOP: case OPP_PROMOTED_SILVER_GENERAL:
                case OPP_PROMOTED_LANCE: case OPP_PROMOTED_PAWN:
                case OPP_PROMOTED_KNIGHT: case OPP_PROMOTED_ROOK:
                    //if it's a promoted piece skip it
                    break;
                case PAWN: case OPP_PAWN:
                    p.setRow(frontRow); //up down
                    p.setCol(pawnNum); //side to side
                    pawnNum++;
                    break;
                case BISHOP: case OPP_BISHOP:
                    p.setRow(middleRow);
                    p.setCol(bishopCol);
                    break;
                case ROOK: case OPP_ROOK:
                    p.setRow(middleRow);
                    p.setCol(rookCol);
                    break;
                case LANCE: case OPP_LANCE:
                    p.setRow(backRow);
                    if (lanceNum == 0) {
                        p.setCol(0);
                    } else {
                        p.setCol(8);
                    }
                    lanceNum++;
                    break;
                case KNIGHT: case OPP_KNIGHT:
                    p.setRow(backRow);
                    if (knightNum == 0) {
                        p.setCol(1);
                    } else {
                        p.setCol(7);
                    }
                    knightNum++;
                    break;
                case SILVER_GENERAL: case OPP_SILVER_GENERAL:
                    p.setRow(backRow);
                    if (silvNum == 0) {
                        p.setCol(2);
                    } else {
                        p.setCol(6);
                    }
                    silvNum++;
                    break;
                case GOLD_GENERAL: case OPP_GOLD_GEN:
                    p.setRow(backRow);
                    if (goldNum == 0) {
                        p.setCol(3);
                    } else {
                        p.setCol(5);
                    }
                    goldNum++;
                    break;
                case KING: case OPP_KING:
                    p.setRow(backRow);
                    p.setCol(4);
                    break;
            }
        }//End direction switch
        board.assignTile(piece); //Assign each piece to a tile
    }

    /**
     * randomly chooses a player to go first
     *
     * @return 0 if player 1 goes first, 1 if player 2 goes first
     */
    public int first() {
        Random rand = new Random();
        int i = rand.nextInt(11);
        return i % 2;
    }

    /**     VARIOUS GETTERS AND SETTERS     */
    public void changeTurn(int playerID) {
        this.whoseTurn = playerID;
    }

    public int getWhoseTurn() {
        return whoseTurn;
    }

    public void setSelecting(boolean selecting) {
        this.selecting = selecting;
    }

    public boolean isSelecting() {
        return selecting;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Piece> getPieceArray(int id) {
        if (id == 0) {
            return pieces1;
        }
        return pieces2;
    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public boolean isInCheckmate() {
        return isInCheckmate;
    }

    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }

    public void setInCheckmate(boolean inCheckmate) {
        isInCheckmate = inCheckmate;
    }
}
