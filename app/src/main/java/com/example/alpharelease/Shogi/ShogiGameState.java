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
 * @version 10/11/2022
 *
 * */

//onDraw

public class ShogiGameState extends GameState {
    /* [other class]/
     * Board (reference to copy constructor)
     * Pieces
     * */

    /**
     * Grave (2)
     * Button (new game)
     * TextView (____'s Turn / ____ Wins!)
     * Turn (keep track) X
     * <p>
     * Current selected piece
     * Selected space
     * Locations
     * <p>
     * Players
     * <p>
     * *** if stalemate ***
     * Previous turn state
     */
    private boolean isInCheck, isInCheckmate;
    private Board board;
    private final int p1 = 0;
    private final int p2 = 1;
    private int whoseTurn;
    private String banner;
    private Graveyard grave_1;
    private Graveyard grave_2;
    public ArrayList<Piece> pieces1;
    public ArrayList<Piece> pieces2;
    public ArrayList<Tile> tileArray;
    public ArrayList<Integer> cords;
    /**
     * Current state of the game constructor
     */

    public ShogiGameState() { //Cntr
        whoseTurn = first();
        board = new Board();
        grave_1 = new Graveyard();
        grave_2 = new Graveyard();
        pieces1 = new ArrayList<Piece>();
        pieces2 = new ArrayList<Piece>();
        tileArray = new ArrayList<>();
        cords = new ArrayList<>();

        assignPieces();
    }

    /**
     * Current state of the game deep copy constructor
     */
    public ShogiGameState(ShogiGameState orig) {
        this.whoseTurn = orig.whoseTurn;
        this.board = orig.board;
        this.grave_1 = orig.grave_1;
        this.grave_2 = orig.grave_2;
        this.pieces1 = new ArrayList<Piece>();
        this.pieces2 = new ArrayList<Piece>();
        this.cords = new ArrayList<Integer>();

        // for loop through
        this.pieces1.addAll(orig.pieces1);
        this.pieces2.addAll(orig.pieces2);
        this.cords.addAll(orig.cords);
    }

    //Make methods for defined actions
    //make pieces
    private void assignPieces() {
        for (Piece.GAME_PIECES piece : Piece.GAME_PIECES.values()) {
            for (int i = 0; i < piece.getAmount(); i++) {
                switch (piece.getPlayer()) {
                    case 0:
                        pieces1.add(new Piece(piece, Piece.DIRECTION.FORWARD)); //player 1 (id = 0)
                        break;
                    case 1:
                        pieces2.add(new Piece(piece, Piece.DIRECTION.BACKWARD)); //player 2 (id = 1)
                        break;
                }
            } // for i
        } // for pieces
        initPieces(pieces1, 0);
        initPieces(pieces2, 1);

    }


    /**
     * Assigns rows and columns to each piece for initial setup for each player
     * Promotion pieces are unassigned due to them not existing on the board at that time
     */
    private void initPieces(ArrayList<Piece> piece, int id) { //board is 9x9 tiles
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

    // see who goes first
    public int first() {
        Random rand = new Random();
        int i = rand.nextInt(11);
        return i % 2;
    }

    /**
     * Determine next turn based on current turn
     */
    public void changeTurn(int id) {
        this.whoseTurn = id;
    }

    public int getWhoseTurn() {
        return whoseTurn;
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
