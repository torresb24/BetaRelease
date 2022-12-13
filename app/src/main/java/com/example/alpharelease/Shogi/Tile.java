package com.example.alpharelease.Shogi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

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

public class Tile implements Serializable {
    private int row, col, tileIndex;
    private float xCoord; // left
    private float yCoord; // top
    private float xCoordEnd; // right
    private float yCoordEnd; // bottom
    private boolean isOccupied, possible;
    private transient Paint tilePaint;
    private transient Paint emptyPaint;
    private transient Paint possPaint;
    private Piece piece;

    /**
     * Constructor for Tile class
     */
    public Tile() {
        row = col = tileIndex = 0;
        xCoord = yCoord = xCoordEnd = yCoordEnd = 0;
        isOccupied = possible = false;
        piece = null;

        tilePaint = new Paint();
        emptyPaint = new Paint();
        emptyPaint.setColor(Color.TRANSPARENT);
        possPaint = new Paint();
        possPaint.setARGB(255/2, 100, 155, 100);

        tilePaint.setColor(emptyPaint.getColor());
    }

    /**
     * Draws rectangles on board to show board information
     *      (whether piece is an enemy, possible, or neither)
     *
     * @param c
     *      the canvas on which to draw the tiles
     */
    public void drawTiles(Canvas c) {
        if (tilePaint == null) {tilePaint = new Paint();}
        if (possPaint == null) {
            possPaint = new Paint();
            possPaint.setARGB(255/2, 100, 155, 100);
        }
        if (emptyPaint == null) {
            emptyPaint = new Paint();
            emptyPaint.setColor(Color.TRANSPARENT);
        }


        if (isPossible()) { //If you can move there color it this color
            this.tilePaint.setColor(possPaint.getColor());
        } else {
            this.tilePaint.setColor(emptyPaint.getColor());
        }
        c.drawRect(this.xCoord, this.yCoord, this.xCoordEnd, this.yCoordEnd, tilePaint);
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    /**
     * Setting coords for x and y directions (front and end for full cube)
     *
     * @param x the x coordinate (top)
     * @param y the y coordinate (top)
     * @param xEnd the x coordinate (end)
     * @param yEnd the x coordinate (end)
     */
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

    /**
     * Checks if to-set piece can be set and sets if provided piece isn't null
     *
     * @param piece
     *     the provided piece (selected)
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        if (piece != null) {
            this.isOccupied = true;
            piece.setRow(this.row);
            piece.setCol(this.col);
            piece.setOnBoard(true);
        } else {
            this.isOccupied = false;
        }
    }

    public Piece getPiece() {
        return this.piece;
    }
}
