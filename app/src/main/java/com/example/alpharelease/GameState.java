package com.example.alpharelease;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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

public class GameState {
    /* [other class]/
     * Board (reference to copy constructor)
     * Pieces
     * */

    /**
     * Grave (2)
     * Button (new game)
     * TextView (____'s Turn / ____ Wins!)
     * Turn (keep track) X
     * <p>
     * Current selected piece
     * Selected space
     * Locations
     * <p>
     * Players
     * <p>
     * *** if stalemate ***
     * Previous turn state
     */
    private boolean turn; // [true = Player 1, false = other]
    private Board board;
    private final int p1 = 0;
    private final int p2 = 1;
    private Graveyard grave_1;
    private Graveyard grave_2;
    private ArrayList<Piece> pieces1;
    private ArrayList<Piece> pieces2;
    private ArrayList<Integer> cords;
    private String banner;
    private int turnCount = 0;

    /**
     * Current state of the game constructor
     */
    public GameState() { //Cntr
        turn = !first();
        board = new Board();
        grave_1 = new Graveyard();
        grave_2 = new Graveyard();
        pieces1 = new ArrayList<Piece>();
        pieces2 = new ArrayList<Piece>();
        cords = new ArrayList<>();
        assignPieces();
        changeTurn();
        //Declare turn the opposite of first so we can call the changeTurn method
        //to assign banner a value and correct the turn value bc I'm lazy
    }

    /**
     * Current state of the game deep copy constructor
     */
    public GameState(GameState orig) {
        this.turn = orig.turn;
        this.board = orig.board;
        this.grave_1 = orig.grave_1;
        this.grave_2 = orig.grave_2;
        this.pieces1 = new ArrayList<Piece>();
        this.pieces2 = new ArrayList<Piece>();
        // for loop through
        for(Piece p : orig.pieces1){
            this.pieces1.add(p);
        }
        for(Piece p : orig.pieces2){
            this.pieces2.add(p);
        }
        this.cords = new ArrayList<Integer>();
        for(Integer i : orig.cords){
            this.cords.add(i);
        }
        //this.pieces1.addAll(orig.pieces1);
        //this.pieces2.addAll(orig.pieces2);
    }

    /**
     * Determine next turn based on current turn
     */
    public void changeTurn() {
        turn = !turn;

        if (turn) {
            banner = "Player one's Turn";
        } else {
            banner = "Player two's turn";
        }
    }

    /**
     * Display all game state information in the TextView
     */
    @Override
    public String toString() {
        Random rand = new Random();
        String string = "Player 1 Pieces: \n";
        int iterations = 0;
        for (Piece p : pieces1) {
            if (!(p.getRow() == -1 || p.getCol() == -1)) { //Don't mention promotion pieces ugh
                string = string + p.pieceType + " at (" + p.getCol() + ", " + p.getRow() + ")\n";
                iterations++;
                if (iterations != pieces1.size()) {
                    string = string + "; ";
                } else {
                    string = string + ". ";
                }
            }
        }
        // newline
        string = string + "\n";
        string = string + "Player 2 Pieces: \n";
        iterations = 0;
        for (Piece p : pieces2) {
            if (!(p.getRow() == -1 || p.getCol() == -1)) { //Don't mention promotion pieces ugh
                string = string + p.pieceType + " at (" + p.getCol() + ", " + p.getRow() + ")\n";
                iterations++;
                if (iterations != pieces2.size()) {
                    string = string + "; ";
                } else {
                    string = string + ". ";
                }
            }
        }
        // newline
        string = string + "\n";
        if (!isChecked()) {
            string = string + "No one is in check. ";
        }
        // newline
        string = string + "\n";
        string = string + "Number of turns made: " + turnCount + ". ";
        // newline
        string = string + "\n";
        string = string + "Possible moves for King:";
        moveKing(turn);
        // make a random move bc not human player yet
        //   int randomMove = rand.nextInt(cords.size()-1);
        int theRand = rand.nextInt(cords.size()-1);
        int movex = cords.get(theRand);
        int movey = cords.get(theRand+1);
        for (int i = 0; i < cords.size(); i++) {
            if(i%2 == 0) {
                string = string + "[";
                string = string + cords.remove(0) + ", ";
            }
            else {
                string = string + cords.remove(0) + "], ";
            }
        }
        // newline
        string = string + "\n";
        string = string + "Player 1's King moves [ " + movex + " , " + movey + " ]";

        string = string + "\n";
        string = string + "Possible moves for Lance:";
        moveLance(turn);
        // make a random move bc not human player yet
        //   int randomMove = rand.nextInt(cords.size()-1);
        theRand = rand.nextInt(cords.size()-1);
        movex = cords.get(theRand);
        movey = cords.get(theRand+1);
        for (int i = 0; i < cords.size(); i++) {
            if(i%2 == 0) {
                string = string + "[";
                string = string + cords.remove(0) + ", ";
            }
            else {
                string = string + cords.remove(0) + "], ";
            }
        }
        // newline
        string = string + "\n";
        string = string + "Player 2's Lance moves [ " + movex + " , " + movey + " ]";

        string = string + "\n";
        string = string + "Possible moves for Pawn:";
        movePawn(turn);
        // make a random move bc not human player yet
        //   int randomMove = rand.nextInt(cords.size()-1);
        theRand = rand.nextInt(cords.size()-1);
        movex = cords.get(theRand);
        movey = cords.get(theRand+1);
        for (int i = 0; i < cords.size(); i++) {
            if(i%2 == 0) {
                string = string + "[";
                string = string + cords.remove(0) + ", ";
            }
            else {
                string = string + cords.remove(0) + "], ";
            }
        }
        // newline
        string = string + "\n";
        string = string + "Player 1's Pawn moves [ " + movex + " , " + movey + " ]";
        return string + banner + ".";
    }


    //Make methods for defined actions
    //make pieces
    private void assignPieces() {
        for (Piece.GAME_PIECES piece : Piece.GAME_PIECES.values()) {
            for (int i = 0; i < piece.getAmount(); i++) {
                pieces1.add( new Piece(piece, Piece.DIRECTION.FORWARD)); //player 1 (id = 0)
                pieces2.add( new Piece(piece, Piece.DIRECTION.BACKWARD)); //player 2 (id = 1)
            } // for i
        } // for pieces
        placePieces(pieces1, 0);
        placePieces(pieces2, 1);
    }


    /**
     * Assigns rows and columns to each piece for initial setup for each player
     * Promotion pieces are unassigned due to them not existing on the board at that time
     */
    private void placePieces(ArrayList<Piece> heehee, int id) { //board is 9x9 tiles
        //front row is 9 pawns
        //middle row is 1 space, bishop, 5 spaces, rook, 1 space (left to right from players pov)
        //back row is lance, knight, silver, gold, king, gold, silver, knight, lance
        int pawnNum = 0, lanceNum = 0, knightNum = 0, goldNum = 0, silvNum = 0;

        if (id == 0) { //forward facing pieces (player 1)
            for (Piece p : heehee) {
                switch (p.pieceType.getID()) { //What kind of piece is it
                    case R.drawable.promoted_bishop:
                    case R.drawable.promoted_lance:
                    case R.drawable.promoted_knight:
                    case R.drawable.promoted_pawn:
                    case R.drawable.promoted_rook:
                    case R.drawable.promoted_silv_gen:
                        //if it's a promoted piece skip it
                        break;
                    case R.drawable.pawn:
                        p.setRow(6); //up down
                        p.setCol(pawnNum); //side to side
                        pawnNum++;
                        break;
                    case R.drawable.bishop:
                        p.setRow(7);
                        p.setCol(1);
                        break;
                    case R.drawable.rook:
                        p.setRow(7);
                        p.setCol(7);
                        break;
                    case R.drawable.lance:
                        p.setRow(8);
                        if (lanceNum == 0) {
                            p.setCol(0);
                        } else {
                            p.setCol(8);
                        }
                        lanceNum++;
                        break;
                    case R.drawable.knight:
                        p.setRow(8);
                        if (knightNum == 0) {
                            p.setCol(1);
                        } else {
                            p.setCol(7);
                        }
                        knightNum++;
                        break;
                    case R.drawable.silv_gen:
                        p.setRow(8);
                        if (silvNum == 0) {
                            p.setCol(2);
                        } else {
                            p.setCol(6);
                        }
                        silvNum++;
                        break;
                    case R.drawable.gold_gen:
                        p.setRow(8);
                        if (goldNum == 0) {
                            p.setCol(3);
                        } else {
                            p.setCol(5);
                        }
                        goldNum++;
                        break;
                    case R.drawable.king:
                        p.setRow(8);
                        p.setCol(4);
                        break;
                }
            }
        } //end p1 setup

        if (id == 1) { //backward facing pieces (player 2)
            for (Piece p : heehee) {
                switch (p.pieceType.getID()) { //What kind of piece is it
                    case R.drawable.promoted_bishop:
                    case R.drawable.promoted_lance:
                    case R.drawable.promoted_knight:
                    case R.drawable.promoted_pawn:
                    case R.drawable.promoted_rook:
                    case R.drawable.promoted_silv_gen:
                        //if it's a promoted piece skip it
                        break;
                    case R.drawable.pawn:
                        p.setRow(2); //up down
                        p.setCol(pawnNum); //side to side
                        pawnNum++;
                        break;
                    case R.drawable.bishop: //switch cols for opponent bishop and rook bc perspective
                        p.setRow(1);
                        p.setCol(7);
                        break;
                    case R.drawable.rook:
                        p.setRow(1);
                        p.setCol(1);
                        break;
                    case R.drawable.lance:
                        p.setRow(0);
                        if (lanceNum == 0) {
                            p.setCol(0);
                        } else {
                            p.setCol(8);
                        }
                        lanceNum++;
                        break;
                    case R.drawable.knight:
                        p.setRow(0);
                        if (knightNum == 0) {
                            p.setCol(1);
                        } else {
                            p.setCol(7);
                        }
                        knightNum++;
                        break;
                    case R.drawable.silv_gen:
                        p.setRow(0);
                        if (silvNum == 0) {
                            p.setCol(2);
                        } else {
                            p.setCol(6);
                        }
                        silvNum++;
                        break;
                    case R.drawable.gold_gen:
                        p.setRow(0);
                        if (goldNum == 0) {
                            p.setCol(3);
                        } else {
                            p.setCol(5);
                        }
                        goldNum++;
                        break;
                    case R.drawable.king:
                        p.setRow(0);
                        p.setCol(4);
                        break;
                }
            }
        } //end p2 setup
    }


    // see who goes first
    public boolean first() {
        Random rand = new Random();
        int i = rand.nextInt(11);
        if (i < 6) {
            return true;
        } else {
            return false;
        }
    }

    // check and checkmate methods
    public boolean isChecked() {
        return false;
    }

    public boolean isCheckmated() {
        return false;
    }

    /**
     * Pre-set selected pieces and order
     *
     * @return selected piece from pieces array otherwise 0 (none)
     */
    public int selectPiece() {
        switch (turnCount) {
            case 1:
                turnCount++;
                return pieces1.indexOf(Piece.GAME_PIECES.PAWN);
            case 2:
                turnCount++;
                return pieces2.indexOf(Piece.GAME_PIECES.PAWN);
            case 3:
                return pieces1.indexOf(Piece.GAME_PIECES.ROOK);
        }
        return 0;
    }

    /**
     * Piece movement (hard coded now, flexible code later)
     */
    public void movePiece() {

        // identify the piece
        switch (turnCount) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;

        }
        changeTurn();
        turnCount++;
    }

    /**
     * Piece capture (hard coded now, flexible code later)
     * pieces: (none) (skipping for GameState, from meeting)
     */
    public int pieceCapture() {
        // ID of piece captured
        // If 0, then no pieces were captured
        // Sends ID to graveyard
        return 0;
        // identify the piece
    }

    public void moveLance(boolean theTurn) {
        //Complete a bounds check on the board based on the lance's specific movements
        // must clear "cords" first, it holds all the possible moves for the current selected piece
        cords.clear();
        ArrayList<Integer> toStore = new ArrayList<>();

        if (1==0) {
            int yCoord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.LANCE)).getCol();
            int xCoord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.LANCE)).getCol();

            for (int i = yCoord - 1; i > 0; --i) {
                toStore.add(xCoord);
                toStore.add(i);
                //cords.add(toStore);
                toStore.clear();
            }
        }
        /**xCoord does not work, filler code below (respectively for all move functions)*/
        // top going
        cords.add(0);
        cords.add(1);
    }

    public void movePawn(boolean theTurn) {
        cords.clear();
        ArrayList<Integer> toStore = new ArrayList<>();
        if (1==0) {
            int ycord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.PAWN)).getCol();
            int xcord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.PAWN)).getRow();
            if (xcord > 0) {
                toStore.add(xcord);
                toStore.add(ycord - 1);
                //   cords.add(toStore);
                toStore.clear();

            }// if turncount
        }
        cords.add(4);
        cords.add(5);

    }

    public void moveKing(boolean theTurn){
        cords.clear();
        ArrayList<Integer> toStore = new ArrayList<>();
        int ycord = -1;
        int xcord = -1;
        int lever = 0;
        if (1==0) {
            ycord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.KING)).getCol();
            xcord = pieces1.get(pieces1.indexOf(Piece.GAME_PIECES.KING)).getRow();
            lever = 1;
        }
        if (1==0) {
            ycord = pieces2.get(pieces2.indexOf(Piece.GAME_PIECES.KING)).getCol();
            xcord = pieces2.get(pieces2.indexOf(Piece.GAME_PIECES.KING)).getRow();
            lever = 1;
        }
        if (lever == 1) {
            if (xcord > 0 && 1==0) {
                toStore.add(xcord - 1);
                toStore.add(ycord);
                // cords.add(toStore);
                toStore.clear();
            }
            if (xcord > 0 && ycord < 9&& 1==0) {
                toStore.add(xcord - 1);
                toStore.add(ycord + 1);
                // cords.add(toStore);
                toStore.clear();
            }
            if (ycord < 9&& 1==0) {
                toStore.add(xcord);
                toStore.add(ycord + 1);
                // cords.add(toStore);
                toStore.clear();
            }
            if (xcord < 9 && ycord < 9&& 1==0) {
                toStore.add(xcord + 1);
                toStore.add(ycord + 1);
                // cords.add(toStore);
                toStore.clear();
            }
            if (xcord < 9&& 1==0) {
                toStore.add(xcord + 1);
                toStore.add(ycord);
                // cords.add(toStore);
                toStore.clear();
            }
            if (xcord < 9 && ycord > 0&& 1==0) {
                toStore.add(xcord + 1);
                toStore.add(ycord - 1);
                // cords.add(toStore);
                toStore.clear();
            }
            if (ycord > 0&& 1==0) {
                toStore.add(xcord);
                toStore.add(ycord - 1);
                //cords.add(toStore);
                toStore.clear();
            }
            if (xcord > 0 && ycord > 0&& 1==0) {
                toStore.add(xcord - 1);
                toStore.add(ycord - 1);
                //  cords.add(toStore);
                toStore.clear();
            }
        } // lever
        cords.add(3);
        cords.add(7);
        cords.add(4);
        cords.add(7);
        cords.add(5);
        cords.add(7);
    }// if turncount
}
