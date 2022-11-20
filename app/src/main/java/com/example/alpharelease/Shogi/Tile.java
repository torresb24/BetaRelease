package com.example.alpharelease.Shogi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

public class Tile {
    private int row, col, tileIndex;
    private float xCord; // left
    private float yCord; // top
    private float xCordEnd; // right
    private float yCordEnd; // bottom
    private boolean isOccupied;
    private Paint tilePaint, occupiedPaint, emptyPaint, possPaint;
    private Piece piece;

    public Tile() {
        row = col = tileIndex = 0;
        xCord = yCord = xCordEnd = yCordEnd = 0;
        isOccupied = false;
        piece = null;

        tilePaint = new Paint();
        emptyPaint = new Paint();
        emptyPaint.setColor(Color.TRANSPARENT);
        occupiedPaint = new Paint();
        occupiedPaint.setARGB(255/4, 199, 0, 200);
        possPaint = new Paint();
        possPaint.setARGB(255/2, 255, 145, 164);

        tilePaint.setColor(emptyPaint.getColor());
    }


    public void drawEnemyTile(Canvas c) {
        if (this.isOccupied() && this.piece.directionMovement == Piece.DIRECTION.BACKWARD) { //If occupied by the enemy change color
            this.tilePaint.setColor(occupiedPaint.getColor());
        } else {
            this.tilePaint.setColor(emptyPaint.getColor());
        }
        c.drawRect(this.xCord, this.yCord, this.xCordEnd, this.yCordEnd, tilePaint);
    }

    public void drawPossibleMove(Canvas c) {
        c.drawRect(this.xCord, this.yCord, this.xCordEnd, this.yCordEnd, possPaint);
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setCords(int x, int y, int xEnd, int yEnd) {
        this.xCord = x;
        this.yCord = y;
        this.xCordEnd = xEnd;
        this.yCordEnd = yEnd;
    }

    public float getxCord() {
        return xCord;
    }

    public float getxCordEnd() {
        return xCordEnd;
    }

    public float getyCord() {
        return yCord;
    }

    public float getyCordEnd() {
        return yCordEnd;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
