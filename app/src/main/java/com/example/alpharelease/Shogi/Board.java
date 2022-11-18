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
    private int moveBoardHor = 479;
    private int moveBoardVer = 24;
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

            top = moveBoardVer + (i * tileSize) + offsetVer;
            bottom = moveBoardVer + ((i + 1) * tileSize) + offsetVer;

            for (int j = 0; j < size; j++) {
                switch (j) {
                    case 1: case 4: case 5: case 6: case 7: case 8:
                        offsetLeft += 4;
                        break;
                    case 2: case 3:
                        offsetLeft += 5;
                        break;
                }

                left = moveBoardHor + (j * tileSize) + offsetLeft;
                right = moveBoardHor + ((j + 1) * tileSize) + offsetLeft;
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
            //TODO: This will probably have/cause a few bugs when moving pieces around
            if (t.getRow() == p.getRow() && t.getCol() == p.getCol()) {
                t.setOccupied(false);
                t.setPiece(null);
            }
        }
    } //End placeOnBoard

    public void drawBoard(Canvas c) {
        for (Tile t : tiles) {
            t.drawTile(c);
        }
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }
}
