package com.example.alpharelease.Shogi;

import android.graphics.Canvas;

import com.example.alpharelease.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author Kathryn Weidman
 * @author Emma Kelly
 * @author Brent Torres
 * @author Matthew Tran
 *
 * @version 11/29/2022
 *
 * */

public class Board {

    private final ArrayList<Tile> tiles, possibleTiles;
    private final int size = 9;
    private final int imagesize = 1030;
    private final int tileSize;
    private final int boardLeftEdge = 479;
    private final int boardTopEdge = 24;
    private final int boardRightEdge = 1516;
    private final int boardBottomEdge = 1063;
    private int offsetLeft, offsetVer;
    private int left, top, right, bottom, tileNum;
    private Tile temp;
    private ArrayList<Tile> grave1,grave2;

    /**
     * Constructor for Board class
     */
    public Board() {
        tiles = new ArrayList<>();
        possibleTiles = new ArrayList<>();
        tileSize = imagesize / 9 - 3;
        makeBoard();
        initGrave();
    }

    /**
     * Initializes the tiles to create the initial board setup
     */
    private void makeBoard() {
        tiles.clear();
        possibleTiles.clear();

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
    /**
     * Initializes the tiles to create the initial grave setup
     */
    public void initGrave(){
        left = top = right = bottom = 0;
        tileNum = 0;
        offsetVer = 0;
        int graveTileSize = imagesize / 10;

        grave1 = new ArrayList<>();
        grave2 = new ArrayList<>();
        for(int i = 0 ; i < 18; i++){
            Tile newTile1 = new Tile();
            newTile1.setCoords(boardRightEdge+(i*graveTileSize),(tileSize*5)+((i%3)*graveTileSize),boardRightEdge+(i*graveTileSize)+graveTileSize,(tileSize*5)+((i*graveTileSize)%3) + graveTileSize);
            newTile1.setTileIndex(tileNum);
            grave1.add(newTile1);
            Tile newTile2 = new Tile();
            newTile2.setCoords(boardLeftEdge-(i*graveTileSize) - graveTileSize,(tileSize*5)-((i%3)*graveTileSize)-graveTileSize,boardLeftEdge-(i*graveTileSize),(tileSize*5)-((i%3)*graveTileSize));
            newTile2.setTileIndex(tileNum);
            grave2.add(newTile2);

            tileNum++;
        }

    }

    public void addToGrave(Piece p){
        if(p.pieceType.getPlayer() == 0) {
            for (Tile t : grave1) {
                if (t.getPiece() == null) {
                    t.setPiece(p);
                    break;
                }
            }
        } // if 0
        else if(p.pieceType.getPlayer() == 1) {
            for (Tile t : grave2) {
                if (t.getPiece() == null) {
                    t.setPiece(p);
                    break;
                }
            }
        } // if 1
    }
    /**
     * assigns pieces to tiles based on initial rows and columns
     *
     * @param pieces the arraylist of type Piece containing all pieces belonging to a specific player
     */
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

    /**
     * Drawing tiles onto the board
     *
     * @param c the main canvas
     *
     */
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
     *
     * @return false if the given coords are NOT within the bounds of the board, else true
     */
    public boolean onBoard(float xCoord, float yCoord) {
        return ((boardLeftEdge <= xCoord || xCoord <= boardRightEdge)
                && (boardTopEdge <= yCoord || yCoord <= boardBottomEdge));
    }

    /**
     * checks to see which tile was touched
     *
     * CAVEAT: Due to how the tiles were created, there may be a single x or y value in some of
     *      the lines between some tiles that won't count towards a tile. This is accounted for
     *      where the method is called
     *
     * @param xCoord the horizontal component of the coordinate
     * @param yCoord the vertical component of the coordinate
     *
     * @return the selected tile if found and null if not
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
     * @return the selected Tile if found and null if not
     */
    public Tile getTile(int col, int row) {
        for (Tile t : tiles) { //Check the tiles rows/col
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
     * @return the selected Tile if found and null if not
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
     * Returns an arraylist of all tiles making up the board
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Tile> getGrave1() {
        return grave1;
    }
    public ArrayList<Tile> getGrave2() {
        return grave2;
    }

    /**
     * Resets which tiles are able to be moved to
     */
    public void impossAllTiles() {
        for (Tile t : tiles) {
            t.setPossible(false);
        }
    }

    /**
     * Checks to see which tiles the selected piece can move to
     *
     * @param tile the tile that is currently selected
     *
     * @return an arraylist of all possible tiles
     */
    public ArrayList<Tile> checkMoves(Tile tile) {
        Piece.DIRECTION dir;
        int[] nums;
        Tile tempy;
        int rowMod = -2;
        nums = tile.getPiece().getMoveNum();
        dir = tile.getPiece().directionMovement;
        possibleTiles.clear();

        if (tile.getPiece().pieceType == Piece.GAME_PIECES.KNIGHT ||
                tile.getPiece().pieceType == Piece.GAME_PIECES.OPP_KNIGHT) { //Knights have different moves
            if (dir == Piece.DIRECTION.BACKWARD) {
                rowMod = -rowMod;
            }
            for (int j = -1; j < 2; j = j + 2) {
                tempy = getTile(tile.getCol() + j, tile.getRow() + rowMod);
                if (tempy == null || (tempy.isOccupied() && tempy.getPiece().directionMovement == dir)) {
                    tempy.setPossible(false);
                    //Out of bounds or an ally is on that tile
                    continue;
                }
                tempy.setPossible(true);
            }
            return getPossibleTiles();
        }

        for (int j = -1; j < 2; j++) { //0, 1, 2
            if (nums[j + 1] == 0) {
                continue;
            }
            for (int i = 1; i < nums[j + 1] + 1; i++) {//Check Top Directions
                tempy = getTile(tile.getCol() + (i * j), tile.getRow() - i);
                if (tempy == null) {
                    //Can't go any further in this direction, so move on
                    break;
                } else if (tempy.isOccupied()) {
                    if (tempy.getPiece().directionMovement == dir) {//Same team
                        tempy.setPossible(false);
                        // Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        tempy.setPossible(true); //You can jump your enemy
                        //Move onto the next possible direction
                        break;
                    }
                }
                tempy.setPossible(true);
                possibleTiles.add(tempy);
            }
        }

        for (int j = 0; j < 2; j++) { //3, 4
            int colMod = j;
            if (j == 0) {
               colMod = -1;
            }
            if (nums[j + 3] == 0) {
                continue;
            }
            for (int i = 1; i < nums[j + 3] + 1; i++) {//Check Middle Row
                tempy = getTile(tile.getCol() + (i * colMod), tile.getRow());
                if (tempy == null) {
                    //Can't go any further in this direction, so move on
                    break;
                } else if (tempy.isOccupied()) {
                    if (tempy.getPiece().directionMovement == dir) {//Same team
                        tempy.setPossible(false);
                        //Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        tempy.setPossible(true); //You can jump your enemy
                        //Move onto the next possible direction
                        break;
                    }
                }
                tempy.setPossible(true);
            }
        }

        for (int j = -1; j < 2; j++) { //5, 6, 7
            if (nums[j + 6] == 0) {
                continue;
            }
            for (int i = 1; i < nums[j + 6] + 1; i++) {//Check Bottom Row
                tempy = getTile(tile.getCol() + (i * j), tile.getRow() + i);
                if (tempy == null) {
                    //Can't go any further in this direction, so move on
                    break;
                } else if (tempy.isOccupied()) {
                    if (tempy.getPiece().directionMovement == dir) {//Same team
                        tempy.setPossible(false);
                        //Move onto the next possible direction
                        break;
                    } else { //Opposite team
                        tempy.setPossible(true); //You can jump your enemy
                        //Move onto the next possible direction
                        break;
                    }
                }
                tempy.setPossible(true);
            }
        }
        return getPossibleTiles();
    }//End checkMoves

    /**
     * Sends an arraylist of tiles a piece can move to based on which tiles
     *      are marked as possible during the checkMoves method
     *
     * @return ArrayList of type Tile
     */
    public ArrayList<Tile> getPossibleTiles() {
        possibleTiles.clear();

        for (Tile t : tiles) {
            if (t.isPossible()) {
                possibleTiles.add(t);
            }
        }
        return possibleTiles;
    }

    public boolean canPromote(Tile t){
        if(t.getPiece().directionMovement == Piece.DIRECTION.FORWARD) {
            if (t.getRow() < 3) {
                return true;
            }
        }
        else if (t.getPiece().directionMovement == Piece.DIRECTION.BACKWARD) {
            if (t.getRow() > 5) {
                return true;
            }
        }
        return false;
    } //canPromote


    public void promote(Tile t, ShogiGameState state, int turn) {
       Piece p = t.getPiece();
       if(turn == 0){
        switch(p.pieceType.getID()) {
            /** PAWN */
            case (R.drawable.pawn):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_pawn && p1.getRow() == -1 && p1.getCol() == -1) {
                        // set replacement
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
                /** LANCE */
            case (R.drawable.lance):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_lance && p1.getRow() == -1 && p1.getCol() == -1) {
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
                /** Rook */
            case (R.drawable.rook):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_rook && p1.getRow() == -1 && p1.getCol() == -1) {
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
                /** BISHOP */
            case (R.drawable.bishop):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_bishop && p1.getRow() == -1 && p1.getCol() == -1) {
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
                /** KNIGHT */
            case (R.drawable.knight):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_knight && p1.getRow() == -1 && p1.getCol() == -1) {
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
                /** SILVER */
            case (R.drawable.silv_gen):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_silv_gen && p1.getRow() == -1 && p1.getCol() == -1) {
                        promotehelper(p,p1,t);
                        break;
                    }
                }
                break;
        }
    } // if Turn == 0
        else if(turn == 1){
            switch(p.pieceType.getID()) {
                /** PAWN */
                case (R.drawable.pawn):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_pawn && p1.getRow() == -1 && p1.getCol() == -1) {
                            // set replacement
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    /** LANCE */
                case (R.drawable.lance):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_lance && p1.getRow() == -1 && p1.getCol() == -1) {
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    /** Rook */
                case (R.drawable.rook):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_rook && p1.getRow() == -1 && p1.getCol() == -1) {
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    /** BISHOP */
                case (R.drawable.bishop):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_bishop && p1.getRow() == -1 && p1.getCol() == -1) {
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    /** KNIGHT */
                case (R.drawable.knight):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_knight && p1.getRow() == -1 && p1.getCol() == -1) {
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    /** SILVER */
                case (R.drawable.silv_gen):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_silv_gen && p1.getRow() == -1 && p1.getCol() == -1) {
                            promotehelper(p,p1,t);
                            break;
                        }
                    }
                    break;
            }
        } // if Turn == 1
    } // promote

    private void promotehelper(Piece orig, Piece promo, Tile t){
        orig.setOnBoard(false);
        promo.setCol(orig.getCol());
        promo.setRow(orig.getRow());
        promo.setOnBoard(true);
        t.setPiece(promo);
    }// promote helper
}
