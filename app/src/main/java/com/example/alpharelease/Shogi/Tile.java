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
 */

public class Tile {
    private int row, col, tileIndex;
    private float xCoord; // left
    private float yCoord; // top
    private float xCoordEnd; // right
    private float yCoordEnd; // bottom
    private boolean isOccupied, possible;
    private Paint tilePaint, occupiedPaint, emptyPaint, possPaint;
    private Piece piece;

    public Tile() {
        row = col = tileIndex = 0;
        xCoord = yCoord = xCoordEnd = yCoordEnd = 0;
        isOccupied = possible = false;
        piece = null;

        tilePaint = new Paint();
        emptyPaint = new Paint();
        emptyPaint.setColor(Color.TRANSPARENT);
        occupiedPaint = new Paint();
        occupiedPaint.setARGB(255/4, 199, 0, 200);
        possPaint = new Paint();
        possPaint.setARGB(255/2, 100, 155, 100);

        tilePaint.setColor(emptyPaint.getColor());
    }

    public void drawTiles(Canvas c) {
        if (isPossible()) { //If you can move there color it this color
            this.tilePaint.setColor(possPaint.getColor());
        } else if (this.isOccupied() &&
                this.piece.directionMovement == Piece.DIRECTION.BACKWARD) { //If occupied by the enemy change color
            this.tilePaint.setColor(occupiedPaint.getColor());
        } else {
            this.tilePaint.setColor(emptyPaint.getColor());
        }
        c.drawRect(this.xCoord, this.yCoord, this.xCoordEnd, this.yCoordEnd, tilePaint);
    }

    /** vvv Various getters and setters vvv */

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setCoords(int x, int y, int xEnd, int yEnd) {
        this.xCoord = x;
        this.yCoord = y;
        this.xCoordEnd = xEnd;
        this.yCoordEnd = yEnd;
    }

    public float getxCoord() {
        return xCoord;
    }

    public float getxCoordEnd() {
        return xCoordEnd;
    }

    public float getyCoord() {
        return yCoord;
    }

    public float getyCoordEnd() {
        return yCoordEnd;
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

    public boolean isPossible() {
        return possible;
    }

    public void setPossible(boolean possible) {
        this.possible = possible;
    }

    public void setTileIndex(int tileIndex) {
        this.tileIndex = tileIndex;
    }

    public int getTileIndex() {
        return tileIndex;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        this.isOccupied = true;
        piece.setRow(this.row);
        piece.setCol(this.col);
        piece.setOnBoard(true);
    }

    public Piece getPiece() {
        return this.piece;
    }

}
