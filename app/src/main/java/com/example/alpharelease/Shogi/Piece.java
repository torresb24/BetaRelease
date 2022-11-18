package com.example.alpharelease.Shogi;

import com.example.alpharelease.R;

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

public class Piece {

    public enum GAME_PIECES {
        KING(R.drawable.king, 1), GOLD_GENERAL(R.drawable.gold_gen, 2), SILVER_GENERAL(R.drawable.silv_gen, 2),
        PROMOTED_SILVER_GENERAL(R.drawable.promoted_silv_gen, 2), BISHOP(R.drawable.bishop, 1),
        PROMOTED_BISHOP(R.drawable.promoted_bishop, 1), ROOK(R.drawable.rook, 1),
        PROMOTED_ROOK(R.drawable.promoted_rook, 1), LANCE(R.drawable.lance, 2), PROMOTED_LANCE(R.drawable.promoted_lance, 2),
        KNIGHT(R.drawable.knight, 2), PROMOTED_KNIGHT(R.drawable.promoted_knight, 2),
        PAWN(R.drawable.pawn, 9), PROMOTED_PAWN(R.drawable.promoted_pawn, 9);

        private final int id;
        private final int amount;

        GAME_PIECES(int drawableID, int _amount) {
            this.id = drawableID;
            this.amount = _amount;
        }

        public int getID() {
            return this.id;
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

    public int row, col;

    public Piece(GAME_PIECES type, DIRECTION dir) {
        row = col = -1;
        pieceType = type;
        directionMovement = dir;
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

    public void setRow(int row) {
        this.row = row;
    }



}
