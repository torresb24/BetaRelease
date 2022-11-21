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
    private int offsetLeft, offsetVer;
    private int left, top, right, bottom, tileNum;
    private Tile temp;

    public Board() {
        tiles = new ArrayList<>();
        tileSize = imagesize / 9 - 3;

        makeBoard();
    }

    private void makeBoard() {
        tiles.clear();

        left = top = right = bottom = 0;
        tileNum = 0;
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
                temp.setCoords(left, top, right, bottom);
                temp.setTileIndex(tileNum);
                tiles.add(temp);
                tileNum++;
            }
        }

    } //End makeBoard

    public void assignTile(ArrayList<Piece> pieces) {
        for (Piece p : pieces) {
            for (Tile t : tiles) {
                if (p.getRow() != t.getRow() || p.getCol() != t.getCol()) {
                    continue;
                }
                if (p.getRow() == t.getRow() && p.getCol() == t.getCol()) {
                    t.setPiece(p);
                    break;
                }
            }
        }
    }

    public void drawBoard(Canvas c) {
        for (Tile t : tiles) {
            t.drawTiles(c);
        }
    }

    /**
     * checks to see if the point given is within the confines of the board
     *
     * @param xCoord the horizontal component of the coordinate
     * @param yCoord the vertical component of the coordinate
     */
    public boolean onBoard(float xCoord, float yCoord) {
        return ((boardLeftEdge <= xCoord || xCoord <= boardRightEdge)
                && (boardTopEdge <= yCoord || yCoord <= boardBottomEdge));
    }

    /**
     * checks to see which tile was touched
     *
     * @param xCoord the horizontal component of the coordinate
     * @param yCoord the vertical component of the coordinate
     *
     *
     * Due to how the tiles were created, there may be a single x or y value in some of the lines
     *      between some tiles that won't count towards a tile
     */
    public Tile getTileByCord(float xCoord, float yCoord) {
        for (Tile t : tiles) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if ((t.getxCoord() - 2 <= xCoord && xCoord <= t.getxCoordEnd() + 2)
                    && (t.getyCoord() - 2 <= yCoord && yCoord <= t.getyCoordEnd() + 2)) {
                return t;
            }
        }
        return null; //In some rare cases the point may fall between the lines of tiles, thus null
    }

    /**
     * checks to see which tile the given row/col refers to
     *
     * @param col the column of the tile that is currently selected
     * @param row the row of the tile that is currently selected
     *
     * returns the selected Tile if found and null if not
     */
    public Tile getTile(int col, int row) {
        for (Tile t : tiles) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if (t.getCol() == col && t.getRow() == row) {
                return t;
            }
        }
        return null; //If the tile doesn't exist it's null
    }

    /**
     * checks to see which tile the given index refers to
     *
     * @param index the index of the tile that is currently selected
     *
     * returns the selected Tile if found and null if not
     */
    public Tile getTile(int index) {
        for (Tile t : tiles) { //Check the tiles indexes
            if (t.getTileIndex() == index) {
                return t;
            }
        }
        return null; //If the tile doesn't exist it's null
    }

    /**
     * returns an arraylist of all tiles making up the board
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    /**
     * resets which tiles are able to be moved to
     */
    public void impossAllTiles() {
        for (Tile t : tiles) {
            t.setPossible(false);
        }
    }

    /**
     * checks to see which tile the selected piece can move to
     *
     * @param tile the tile that is currently selected
     *
     * returns an arraylist of all possible tiles
     */
    public ArrayList checkMoves(Tile tile) {
        Piece.DIRECTION dir;
        ArrayList<Tile> possibleTiles = new ArrayList<>();
        int[] nums;
        nums = tile.getPiece().getMoveNum();
        dir = tile.getPiece().directionMovement;
        int rowMod = 2;

        if (tile.getPiece().pieceType == Piece.GAME_PIECES.KNIGHT ||
                tile.getPiece().pieceType == Piece.GAME_PIECES.OPP_KNIGHT) { //Knights have different moves
            if (dir == Piece.DIRECTION.BACKWARD) {
                rowMod = -rowMod;
            }
            for (int j = -1; j < 2; j = j + 2) {
                temp = getTile(tile.getCol() + j, tile.getRow() + rowMod);
                if (temp == null || (temp.isOccupied() && temp.getPiece().directionMovement == dir)) {
                    //Out of bounds or an ally is on that tile
                    break;
                }
                temp.setPossible(true);
            }
        }

        for (int j = -1; j < 2; j++) { //0, 1, 2
            for (int i = 0; i <= nums[j + 1]; i++) {//Check Top Row
                temp = getTile(tile.getCol() + (i * j), tile.getRow() - i);
                if (temp == null) {
                    j++; //Can't go any further in this direction, so move on
                    break;
                } else if (temp.isOccupied()) {
                    if (temp.getPiece().directionMovement == dir) {//Same team
                        j++; //Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        temp.setPossible(true); //You can jump your enemy
                        j++; //Move onto the next possible direction
                        break;
                    }
                }
                temp.setPossible(true);
            }
        }

        for (int j = 0; j < 2; j++) { //3, 4
            int colMod = j;
            if (j == 0) {
               colMod = -1;
            }
            for (int i = 0; i <= nums[j + 3]; i++) {//Check Middle Row
                temp = getTile(tile.getCol() + colMod, tile.getRow());
                if (temp == null) {
                    j++; //Can't go any further in this direction, so move on
                    break;
                } else if (temp.isOccupied()) {
                    if (temp.getPiece().directionMovement == dir) {//Same team
                        j++; //Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        temp.setPossible(true); //You can jump your enemy
                        j++; //Move onto the next possible direction
                        break;
                    }
                }
                temp.setPossible(true);
            }
        }

        for (int j = -1; j < 2; j++) { //5, 6, 7
            for (int i = 0; i <= nums[j + 6]; i++) {//Check Bottom Row
                temp = getTile(tile.getCol() + (i * j), tile.getRow() + i);
                if (temp == null) {
                    j++; //Can't go any further in this direction, so move on
                    break;
                } else if (temp.isOccupied()) {
                    if (temp.getPiece().directionMovement == dir) {//Same team
                        j++; //Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        temp.setPossible(true); //You can jump your enemy
                        j++; //Move onto the next possible direction
                        break;
                    }
                }
                temp.setPossible(true);
            }
        }

        for (Tile t : tiles) {
            if (t.isPossible()) {
                possibleTiles.add(t);
            }
        }
        return possibleTiles;
    }//End checkMoves

}
