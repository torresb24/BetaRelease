package com.example.alpharelease.Shogi;

import android.graphics.Canvas;
import android.util.Log;
import com.example.alpharelease.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kathryn Weidman
 * @author Emma Kelly
 * @author Brent Torres
 * @author Matthew Tran
 *
 * @version 12/13/2022
 *
 * */

public class Board implements Serializable {

    private final ArrayList<Tile> tiles, possibleTiles;
    private ArrayList<Tile> g1Array, g0Array;
    private final int size = 9;
    private final int imagesize = 1030;
    private final int tileSize;
    private final int graveSide = 426;
    private final int boardLeftEdge = 479;
    private final int boardTopEdge = 24;
    private final int boardRightEdge = 1516;
    private final int boardBottomEdge = 1063;

    // top grave
    private final int tgLeftEdge = 12;
    private final int tgTopEdge = 12;
    private final int tgRightEdge = tgTopEdge + graveSide;
    private final int tgBottomEdge = tgLeftEdge + graveSide;

    // bottom grave
    private final int bgLeftEdge = boardRightEdge + 40;
    private final int bgBottomEdge = boardBottomEdge + 6;
    private final int bgRightEdge = bgLeftEdge + graveSide - 2;
    private final int bgTopEdge = bgBottomEdge - graveSide + 1;
    private int offsetLeft, offsetVer;
    private int left, top, right, bottom, tileNum;
    private boolean promoted;
    private Tile temp;

    /**
     * Constructor for Board class
     */
    public Board() {
        tiles = new ArrayList<>();
        g1Array = new ArrayList<>();
        g0Array = new ArrayList<>();
        possibleTiles = new ArrayList<>();
        tileSize = imagesize / 9 - 3;

        makeBoard();
        makeGraves();
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
                case 1:case 4:case 5:case 6:
                    offsetVer += 4;
                    break;
                case 2:case 3:case 7:case 8:
                    offsetVer += 5;
                    break;
            }

            top = boardTopEdge + (i * tileSize) + offsetVer;
            bottom = boardTopEdge + ((i + 1) * tileSize) + offsetVer;

            for (int j = 0; j < size; j++) {
                switch (j) {
                    case 1:case 4:case 5:case 6:case 7: case 8:
                        offsetLeft += 4;
                        break;
                    case 2:case 3:
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

    private void makeGraves() {
        g0Array.clear();
        g1Array.clear();

        left = top = right = bottom = 0;
        tileNum = 100;
        offsetVer = 0;

        // Top Grave (5x4)
        for (int i = 0; i < 5; i++) {
            // vertical
            top = tgTopEdge + (i * (graveSide/5));
            bottom = tgTopEdge + ((i + 1) * (graveSide/5));

            for (int j = 0; j < 4; j++) {
                // horizontal
                left = tgLeftEdge + (j * (graveSide/4));
                right = tgLeftEdge + ((j + 1) * (graveSide/4));

                temp = new Tile();
                temp.setOccupied(false);
                temp.setRow(i);
                temp.setCol(j);
                temp.setCoords(left, top, right, bottom);
                temp.setTileIndex(tileNum);
                g1Array.add(temp);
                tileNum++;

            } // for j ! col
        } // for i ! row

        tileNum = 200;
        // Bottom Grave (5x4)
        for (int i = 0; i < 5; i++) {

            bottom = bgBottomEdge - (i * (graveSide/5));
            top = bgBottomEdge - ((i + 1) * (graveSide/5));

            //bottom = bgBottomEdge;
            //top = bgTopEdge;

            for (int j = 0; j < 4; j++) {
                // horizontal
                //left = bgLeftEdge;
                //right = bgRightEdge;
                left = bgLeftEdge + (j * (graveSide/4));
                right = bgLeftEdge + ((j + 1) * (graveSide/4));

                temp = new Tile();
                temp.setOccupied(false);
                temp.setRow(i);
                temp.setCol(j);
                temp.setCoords(left, top, right, bottom);
                temp.setTileIndex(tileNum);
                g0Array.add(temp);
                tileNum++;
            } // for j ! col
        } // for i ! row
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
     */
    public void drawBoard(Canvas c) {
        for (Tile t : tiles) {
            t.drawTiles(c);
        }
    }

    /**
     * Checks to see if the point given is within the confines of the board
     *
     * @param xCoord the horizontal component of the coordinate
     * @param yCoord the vertical component of the coordinate
     * @return false if the given coords are NOT within the bounds of the board, else true
     */
    public boolean onBoard(float xCoord, float yCoord) {
        return ((boardLeftEdge <= xCoord || xCoord <= boardRightEdge)
                && (boardTopEdge <= yCoord || yCoord <= boardBottomEdge));
    }
    public boolean onGraves(float xCoord, float yCoord) {
        boolean topGrave = ((tgLeftEdge <= xCoord || xCoord <= tgRightEdge)
                && (tgTopEdge <= yCoord || yCoord <= tgBottomEdge));
        boolean botGrave =  ((bgLeftEdge <= xCoord || xCoord <= bgRightEdge)
                && (bgTopEdge <= yCoord || yCoord <= bgBottomEdge));
        return (topGrave || botGrave);
    }

    /**
     * Checks to see which tile was touched
     * <p>
     * CAVEAT: Due to how the tiles were created, there may be a single x or y value in some of
     * the lines between some tiles that won't count towards a tile. This is accounted for
     * where the method is called
     *
     * @param xCoord the horizontal component of the coordinate
     * @param yCoord the vertical component of the coordinate
     * @return the selected tile if found and null if not
     */
    public Tile getTileByCord(float xCoord, float yCoord) {
        for (Tile t : tiles) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if ((t.getxCoord() - 2 <= xCoord && xCoord <= t.getxCoordEnd() + 2)
                    && (t.getyCoord() - 2 <= yCoord && yCoord <= t.getyCoordEnd() + 2)) {
                return t;
            }
        }
        for (Tile t : g0Array) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if ((t.getxCoord() - 2 <= xCoord && xCoord <= t.getxCoordEnd() + 2)
                    && (t.getyCoord() - 2 <= yCoord && yCoord <= t.getyCoordEnd() + 2)) {
                return t;
            }
        }
        for (Tile t : g1Array) { //Check the tiles coordinates (with slight leeway bc of the lines btwn)
            if ((t.getxCoord() - 2 <= xCoord && xCoord <= t.getxCoordEnd() + 2)
                    && (t.getyCoord() - 2 <= yCoord && yCoord <= t.getyCoordEnd() + 2)) {
                return t;
            }
        }
        return null; //In some rare cases the point may fall between the lines of tiles, thus null
    }

    /**
     * Checks to see which tile the given row/col refers to
     *
     * @param col the column of the tile that is currently selected
     * @param row the row of the tile that is currently selected
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
     * Checks to see which tile the given index refers to
     *
     * @param index the index of the tile that is currently selected
     * @return t the selected Tile if found and null if not
     */
    public Tile getTile(int index) {
        for (Tile t : tiles) { //Check the tiles indexes
            if (t.getTileIndex() == index) {
                return t;
            }
        }
        for (Tile t : g0Array) { //Check the tiles indexes
            if (t.getTileIndex() == index) {
                return t;
            }
        }
        for (Tile t : g1Array) { //Check the tiles indexes
            if (t.getTileIndex() == index) {
                return t;
            }
        }
        return null; //If the tile doesn't exist it's null
    }

    /**
     * Returns an arraylist of all tiles making up the board
     *
     * @return ArrayList of Tile object tiles
     */
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public ArrayList<Tile> getGrave0() {
        return g0Array;
    }

    public ArrayList<Tile> getGrave1() {
        return g1Array;
    }

    // When killed, place in first empty tile, when dropped, tile is emptied [not occupied]
    public void addToGrave(Piece p, int turn) {
        if (turn == 0) {
            for (Tile t : g0Array) {
                if (!t.isOccupied()) {
                    t.setPiece(p);
                    return;
                }
            } // for t
        } // if turn == 0
        else {
            for (Tile t : g1Array) {
                if (!t.isOccupied()) {
                    t.setPiece(p);
                    return;
                }
            } // for t
        } // else
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

        //For dropping from grave
        if (!tile.getPiece().isAlive()) {
            // if pawn, check all tiles, if tile has pawn alr, notPossible is col
            ArrayList<Integer> holdPawnCols = new ArrayList<>();
            if (tile.getPiece().pieceType.getID() == R.drawable.pawn) {
                for (Tile t : tiles) {
                    if (t.isOccupied()) {
                        // if pawn and  same team.
                        if (t.getPiece().pieceType.getID() == R.drawable.pawn &&
                                t.getPiece().getThePlayer() == tile.getPiece().getThePlayer()) {
                            holdPawnCols.add(t.getCol());
                        }
                    } // if occupied
                } // for t
            }
            // [End] if pawn, check all tiles, if tile has pawn alr, notPossible is col
            // find empty spaces
            for (Tile t: tiles) {
                if (!t.isOccupied()) {
                    if (!holdPawnCols.contains(t.getCol())) {
                        t.setPossible(true);
                    }
                } // if not occupied
            } // for t
            // [End] find empty spaces
            return getPossibleTiles();
        }
        /**END For dropping from grave*/

        // if it's a knight,
        if (tile.getPiece().pieceType == Piece.GAME_PIECES.KNIGHT ||
                tile.getPiece().pieceType == Piece.GAME_PIECES.OPP_KNIGHT) { //Knights have different moves
            // if it's the "computer"
            if (dir == Piece.DIRECTION.BACKWARD) {
                // reverse location for flipped board of "computer"
                rowMod = -rowMod;
            }
            for (int j = -1; j < 2; j = j + 2) {
                //Log.i("knightMoveHere", "Tempy: " + (tile.getCol() + j) + " and " + (tile.getRow() + rowMod));
                tempy = getTile(tile.getCol() + j, tile.getRow() + rowMod);
                if (tempy == null || (tempy.isOccupied() && tempy.getPiece().directionMovement == dir)) {
                    if (tempy != null) {
                        tempy.setPossible(false);
                    }
                    //Out of bounds or an ally is on that tile
                    continue;
                }

                if ((tile.getCol() + j) > 8 || (tile.getRow() + rowMod) > 8
                        || (tile.getCol() + j) < 0 || (tile.getRow() + rowMod) < 0) {
                    tempy.setPossible(false);
                } else {
                    tempy.setPossible(true);
                }

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
     * @return ArrayList of elements of type Tile object
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

    /**
     * Checks to see whether a piece can be promoted or not
     *
     * @param t the tile holding the piece being checked
     *
     * @return true if the piece is not a king or golden general and is
     *              within the last three rows opposite to them
     *         false otherwise
     * */
    public boolean canPromote(Tile t) {
        switch (t.getPiece().pieceType) {
            case KING: case OPP_KING: case GOLD_GENERAL: case OPP_GOLD_GEN:
            return false;
        }
        if (t.getPiece().directionMovement == Piece.DIRECTION.FORWARD) {
            return t.getRow() < 3 && !t.getPiece().getPromoted();
        }
        else if (t.getPiece().directionMovement == Piece.DIRECTION.BACKWARD) {
            return t.getRow() > 5 && !t.getPiece().getPromoted();
        }
        return false;
    } //canPromote


    /**
     * Promotes the given piece
     *
     * @param t the tile holding the piece that's being promoted
     * @param state current gamestate
     * @param turn the player's turn
     * */
    public void promote(Tile t, ShogiGameState state, int turn) {
       Piece p = t.getPiece();
       if (turn == 0) {
        switch (p.pieceType.getID()) {
            // Pawn promotion
            case (R.drawable.pawn):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_pawn && p1.getRow() == -1 &&
                            p1.getCol() == -1) {
                        // set replacement
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
                // Lance promotion
            case (R.drawable.lance):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_lance && p1.getRow() == -1 &&
                            p1.getCol() == -1) {
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
                // Rook promotion
            case (R.drawable.rook):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_rook && p1.getRow() == -1 &&
                            p1.getCol() == -1) {
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
                // Bishop promotion
            case (R.drawable.bishop):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_bishop && p1.getRow() == -1 &&
                            p1.getCol() == -1) {
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
                // Knight promotion
            case (R.drawable.knight):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_knight && p1.getRow() == -1 && p1.getCol() == -1) {
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
                // Silver General promotion
            case (R.drawable.silv_gen):
                for (Piece p1 : state.pieces1) {
                    if (p1.pieceType.getID() == R.drawable.promoted_silv_gen && p1.getRow() == -1 && p1.getCol() == -1) {
                        promoteHelper(p,p1,t);
                        break;
                    }
                }
                break;
        }
    } // if Turn == 0
        else if (turn == 1) {
            switch (p.pieceType.getID()) {
                // Pawn promotion
                case (R.drawable.pawn):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_pawn && p1.getRow() == -1 && p1.getCol() == -1) {
                            // set replacement
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    // Lance promotion
                case (R.drawable.lance):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_lance && p1.getRow() == -1 && p1.getCol() == -1) {
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    // Rook promotion
                case (R.drawable.rook):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_rook && p1.getRow() == -1 && p1.getCol() == -1) {
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    // Bishop promotion
                case (R.drawable.bishop):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_bishop && p1.getRow() == -1 && p1.getCol() == -1) {
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    // Knight promotion
                case (R.drawable.knight):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_knight && p1.getRow() == -1 && p1.getCol() == -1) {
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
                    // Silver promotion
                case (R.drawable.silv_gen):
                    for (Piece p1 : state.pieces2) {
                        if (p1.pieceType.getID() == R.drawable.promoted_silv_gen && p1.getRow() == -1 && p1.getCol() == -1) {
                            promoteHelper(p,p1,t);
                            break;
                        }
                    }
                    break;
            }
        } // if Turn == 1
    } // promote

    /**
     * Check the opposite team's pieces
     *
     * @param orig the piece prior to promotion (i.e. KNIGHT)
     * @param promo the piece post promotion (i.e. PROMO_KNIGHT)
     * @param t the tile holding the piece being checked
     * */
    private void promoteHelper(Piece orig, Piece promo, Tile t) {

        if ((orig.getPromoted())) {
            Log.i("promotionCheck", "This is already promoted.");

        } else {
            Log.i("promotionCheck", "This is not yet promoted.");
            orig.setOnBoard(false);
            promo.setCol(orig.getCol());
            promo.setRow(orig.getRow());
            promo.setPromoted(true);
            orig.setPromoted(false);
            orig.setRow(-1);
            orig.setCol(-1);
            promo.setOnBoard(true);
            t.setPiece(promo);
        }

    }// promote helper

}
