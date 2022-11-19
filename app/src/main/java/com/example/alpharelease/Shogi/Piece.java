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
        KING(R.drawable.king, 1, 0), GOLD_GENERAL(R.drawable.gold_gen, 2, 0),
        SILVER_GENERAL(R.drawable.silv_gen, 2, 0),
        PROMOTED_SILVER_GENERAL(R.drawable.promoted_silv_gen, 2, 0),
        BISHOP(R.drawable.bishop, 1, 0), PROMOTED_BISHOP(R.drawable.promoted_bishop, 1, 0),
        ROOK(R.drawable.rook, 1, 0), PROMOTED_ROOK(R.drawable.promoted_rook, 1, 0),
        LANCE(R.drawable.lance, 2, 0), PROMOTED_LANCE(R.drawable.promoted_lance, 2, 0),
        KNIGHT(R.drawable.knight, 2, 0), PROMOTED_KNIGHT(R.drawable.promoted_knight, 2, 0),
        PAWN(R.drawable.pawn, 9, 0), PROMOTED_PAWN(R.drawable.promoted_pawn, 9, 0),

        OPP_KING(R.drawable.opp_king, 1, 1), OPP_GOLD_GEN(R.drawable.opp_gold_gen, 2, 1),
        OPP_SILVER_GENERAL(R.drawable.opp_silv_gen, 2, 1),
        OPP_PROMOTED_SILVER_GENERAL(R.drawable.opp_promo_silv, 2, 1),
        OPP_BISHOP(R.drawable.opp_bish, 1, 1), OPP_PROMOTED_BISHOP(R.drawable.opp_promo_bish, 1, 1),
        OPP_ROOK(R.drawable.opp_rook, 1, 1), OPP_PROMOTED_ROOK(R.drawable.opp_promo_rook, 1, 1),
        OPP_LANCE(R.drawable.opp_lance, 2, 1), OPP_PROMOTED_LANCE(R.drawable.opp_promo_lance, 2, 1),
        OPP_KNIGHT(R.drawable.opp_knight, 2, 1), OPP_PROMOTED_KNIGHT(R.drawable.opp_promo_knight, 2, 1),
        OPP_PAWN(R.drawable.opp_pawn, 9, 1), OPP_PROMOTED_PAWN(R.drawable.opp_promo_pawn, 9, 1);

        private final int id;
        private final int amount;
        private final int player;

        GAME_PIECES(int drawableID, int amount, int player) {
            this.id = drawableID;
            this.amount = amount;
            this.player = player;
        }

        public int getPlayer() {
            return player;
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

    private int row, col;
    private boolean isAlive, isOnBoard;

    public Piece(GAME_PIECES type, DIRECTION dir) {
        row = col = -1;
        pieceType = type;
        directionMovement = dir;
        isAlive = true;
        firstIsOnBoard(type);
    }

    private void firstIsOnBoard(GAME_PIECES type) {
        switch (type.getID()) {
            case R.drawable.promoted_pawn: case R.drawable.promoted_lance: case R.drawable.promoted_rook:
            case R.drawable.promoted_bishop: case R.drawable.promoted_knight: case R.drawable.promoted_silv_gen:
            case R.drawable.promoted_gold_gen: case R.drawable.opp_promo_pawn: case R.drawable.opp_promo_lance:
            case R.drawable.opp_promo_rook: case R.drawable.opp_promo_bish: case R.drawable.opp_promo_knight:
            case R.drawable.opp_promo_silv: case R.drawable.opp_promo_gold:
                isOnBoard = false;
                break;
            default:
                isOnBoard = true;
                break;
        }
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

    public boolean isAlive() {
        return isAlive;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public DIRECTION getDirection() {
        return directionMovement;
    }
}
