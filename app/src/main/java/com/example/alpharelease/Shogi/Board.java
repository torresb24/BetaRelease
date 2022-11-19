package com.example.alpharelease.Shogi;

import android.graphics.Canvas;

import java.util.ArrayList;

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

public class Board {

    private ArrayList<Tile> tiles;
    private final int size = 9;
    private final int imagesize = 1030;
    private int tileSize;
    private final int boardLeftEdge = 479;
    private final int boardTopEdge = 24;
    private final int boardRightEdge = 1516;
    private final int boardBottomEdge = 1063;
    int offsetLeft;
    int offsetVer;
    private int left, top, right, bottom;
    private Tile temp;

    public Board() {
        tiles = new ArrayList<>();
        tileSize = imagesize / 9 - 3;

        makeBoard();
    }

    private void makeBoard() {
        tiles.clear();

        left = top = right = bottom = 0;
        offsetVer = 0;

        for (int i = 0; i < size; i++) {
            offsetLeft = 0;
            switch (i) {
                case 1: case 4: case 5: case 6:
                    offsetVer += 4;
                    break;
                case 2: case 3: case 7: case 8:
                    offsetVer += 5;
                    break;
            }

            top = boardTopEdge + (i * tileSize) + offsetVer;
            bottom = boardTopEdge + ((i + 1) * tileSize) + offsetVer;

            for (int j = 0; j < size; j++) {
                switch (j) {
                    case 1: case 4: case 5: case 6: case 7: case 8:
                        offsetLeft += 4;
                        break;
                    case 2: case 3:
                        offsetLeft += 5;
                        break;
                }

                left = boardLeftEdge + (j * tileSize) + offsetLeft;
                right = boardLeftEdge + ((j + 1) * tileSize) + offsetLeft;

                temp = new Tile();
                temp.setOccupied(false);
                temp.setRow(i);
                temp.setCol(j);
                temp.setCords(left, top, right, bottom);
                tiles.add(temp);
            }
        }

    } //End makeBoard

    public void placeOnBoard(Piece p, int row, int col) {
        for (Tile t : tiles) {
            if (t.getRow() == row && t.getCol() == col) {
                t.setOccupied(true);
                t.setPiece(p);
            }

            //TODO: ELABORATE ON THIS METHOD
                    //PERHAPS SPLIT INTO TWO METHODS OR SMTH IDK
        }
    } //End placeOnBoard

    public void drawBoard(Canvas c) {
        for (Tile t : tiles) {
            t.drawTile(c);
        }
    }

    public void drawMoves(Canvas c, ArrayList<Tile> tileArray) {
        for (Tile t : tileArray) {

        }
    }

    /**
     * checks to see if the point given is within the confines of the board
     *
     * @param xCord the horizontal component of the coordinate
     * @param yCord the vertical component of the coordinate
     */
    public boolean onBoard(float xCord, float yCord) {
        return ((boardLeftEdge <= xCord || xCord <= boardRightEdge)
                && (boardTopEdge <= yCord || yCord <= boardBottomEdge));
    }

    /**
     * checks to see which tile was touched
     *
     * @param xCord the horizontal component of the coordinate
     * @param yCord the vertical component of the coordinate
     *
     *
     * Due to how the tiles were created, there may be a single x or y value in some of the lines
     *      between some tiles that won't count towards a tile
     */
    public Tile getTouchedTile(float xCord, float yCord) {
        for (Tile t : tiles) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if ((t.getxCord() - 2 <= xCord || xCord <= t.getxCordEnd() + 2)
                    && (t.getyCord() - 2 <= yCord || yCord <= t.getyCordEnd() + 2)) {
                return t;
            }
        }

        return null; //In some rare cases the point may fall between the lines of tiles, thus null
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
