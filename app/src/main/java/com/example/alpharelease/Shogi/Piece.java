package com.example.alpharelease.Shogi;

import com.example.alpharelease.R;
import java.util.Arrays;

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

public class Piece {

    /**
     External Citation
     Date: 18 November 2022
     Problem: Didn't understand how enum worked super well so code
                involving pieces was unsustainable and clumsy
     Resource: Nathaniel Hopper
     Solution: Reference the game_piece type rather than it's R.drawable id
     */

    public enum GAME_PIECES {
        KING(R.drawable.king, 1, 0), GOLD_GENERAL(R.drawable.gold_gen, 2, 0),
        SILVER_GENERAL(R.drawable.silv_gen, 2, 0),
        PROMOTED_SILVER_GENERAL(R.drawable.promoted_silv_gen, 2, 0),
        BISHOP(R.drawable.bishop, 1, 0), PROMOTED_BISHOP(R.drawable.promoted_bishop, 1, 0),
        ROOK(R.drawable.rook, 1, 0), PROMOTED_ROOK(R.drawable.promoted_rook, 1, 0),
        LANCE(R.drawable.lance, 2, 0), PROMOTED_LANCE(R.drawable.promoted_lance, 2, 0),
        KNIGHT(R.drawable.knight, 2, 0), PROMOTED_KNIGHT(R.drawable.promoted_knight, 2, 0),
        PAWN(R.drawable.pawn, 9, 0), PROMOTED_PAWN(R.drawable.promoted_pawn, 9, 0),

        OPP_KING(R.drawable.king, 1, 1), OPP_GOLD_GEN(R.drawable.gold_gen, 2, 1),
        OPP_SILVER_GENERAL(R.drawable.silv_gen, 2, 1),
        OPP_PROMOTED_SILVER_GENERAL(R.drawable.promoted_silv_gen, 2, 1),
        OPP_BISHOP(R.drawable.bishop, 1, 1), OPP_PROMOTED_BISHOP(R.drawable.promoted_bishop, 1, 1),
        OPP_ROOK(R.drawable.rook, 1, 1), OPP_PROMOTED_ROOK(R.drawable.promoted_rook, 1, 1),
        OPP_LANCE(R.drawable.lance, 2, 1), OPP_PROMOTED_LANCE(R.drawable.promoted_lance, 2, 1),
        OPP_KNIGHT(R.drawable.knight, 2, 1), OPP_PROMOTED_KNIGHT(R.drawable.promoted_knight, 2, 1),
        OPP_PAWN(R.drawable.pawn, 9, 1), OPP_PROMOTED_PAWN(R.drawable.promoted_pawn, 9, 1);

        private final int pieceID;
        private final int amount;
        private final int player;

        GAME_PIECES(int drawableID, int amount, int player) {
            this.pieceID = drawableID;
            this.amount = amount;
            this.player = player;
        }

        public int getPlayer() {
            return player;
        }

        public int getID() {
            return this.pieceID;
        }

        public int getAmount() {
            return this.amount;
        }
    }

    public enum DIRECTION {
        FORWARD, BACKWARD
    }

    public GAME_PIECES pieceType;

    public DIRECTION directionMovement;

    private int row, col;
    private boolean isAlive, isOnBoard, isSelected, isRessurected;
    private final int[] moveNum; //TL = 0, T = 1, TR = 2, L = 3, R = 4, BL = 5, B = 6, BR = 7 for indexes
    /**
     * constructor for Piece class
     *
     * @param type what kind of piece it is (i.e. PAWN, KING, OPP_PAWN, etc.)
     * @param dir the direction the piece is facing (FORWARD or BACKWARD)
     *
     */
    public Piece(GAME_PIECES type, DIRECTION dir) {
        row = col = -1;
        pieceType = type;
        directionMovement = dir;
        isAlive = true;
        isSelected = false;
        moveNum = new int[8];
        isRessurected = false;
        setMoveNum();
        firstIsOnBoard();
    }

    /**
     * Sets which pieces are on the board at initialization
     */
    private void firstIsOnBoard() {
        switch (this.pieceType) {
            case PROMOTED_PAWN: case PROMOTED_LANCE: case PROMOTED_ROOK:
            case PROMOTED_BISHOP: case PROMOTED_KNIGHT: case PROMOTED_SILVER_GENERAL:
            case OPP_PROMOTED_PAWN: case OPP_PROMOTED_LANCE: case OPP_PROMOTED_ROOK:
            case OPP_PROMOTED_BISHOP: case OPP_PROMOTED_KNIGHT: case OPP_PROMOTED_SILVER_GENERAL:
                this.isOnBoard = false;
                break;
            default:
                this.isOnBoard = true;
                break;
        }
    }

    /**
     * Sets how far in each cardinal and ordinal direction a piece can move
     */
    private void setMoveNum() { //TL = 0, T = 1, TR = 2, L = 3, R = 4, BL = 5, B = 6, BR = 7 for indexes
        Arrays.fill(moveNum, 0);

        switch (this.pieceType) {
            case PAWN:
                moveNum[1] = 1;
                break;

            case OPP_PAWN:
                moveNum[6] = 1;
                break;

            case BISHOP: case OPP_BISHOP:
                moveNum[0] = moveNum[2] = moveNum[5] = moveNum[7] = 8;
                break;

            case ROOK: case OPP_ROOK:
                moveNum[1] = moveNum[3] = moveNum[4] = moveNum[6] = 8;
                break;

            case LANCE:
                moveNum[1] = 8;
                break;

            case OPP_LANCE:
                moveNum[6] = 8;
                break;

            case KNIGHT: case OPP_KNIGHT:
                break;

            case SILVER_GENERAL:
                Arrays.fill(moveNum, 1);
                moveNum[3] = moveNum[4] = moveNum[6] = 0;
                break;

            case OPP_SILVER_GENERAL:
                Arrays.fill(moveNum, 1);
                moveNum[1] = moveNum[3] = moveNum[4] = 0;
                break;

            case KING: case OPP_KING:
                Arrays.fill(moveNum, 1);
                break;

            case PROMOTED_ROOK: case OPP_PROMOTED_ROOK:
                Arrays.fill(moveNum, 1);
                moveNum[1] = moveNum[3] = moveNum[4] = moveNum[6] = 8;
                break;

            case PROMOTED_BISHOP: case OPP_PROMOTED_BISHOP:
                Arrays.fill(moveNum, 1);
                moveNum[0] = moveNum[2] = moveNum[5] = moveNum[7] = 8;
                break;

            case PROMOTED_PAWN: case PROMOTED_LANCE: case PROMOTED_KNIGHT:
            case PROMOTED_SILVER_GENERAL: case GOLD_GENERAL:
                Arrays.fill(moveNum, 1);
                moveNum[5] = moveNum[7] = 0;
                break;

            case OPP_PROMOTED_PAWN: case OPP_PROMOTED_LANCE: case OPP_PROMOTED_KNIGHT:
            case OPP_PROMOTED_SILVER_GENERAL: case OPP_GOLD_GEN:
                Arrays.fill(moveNum, 1);
                moveNum[0] = moveNum[2] = 0;
                break;
        }
    }//End setMoveNum

    // Various getters and setters
    public int[] getMoveNum() {
        return moveNum;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setPromoted(boolean p) {
        //this.isPromoted = p;
    }

    public boolean getPromoted() {
        return false;
    } // return isPromoted

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
        if (!onBoard) {
            this.row = this.col = -1;
        } //Take them off the board
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        if (!alive) {
            this.setOnBoard(false);
        } //Take them off the board if dead
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public void Ressurect(){
        this.isRessurected = true;
    }
}
