package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.infoMessage.GameState;
import com.example.alpharelease.R;

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

//onDraw

public class ShogiGameState extends GameState {
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
    private String banner;
    private Graveyard grave_1;
    private Graveyard grave_2;
    public ArrayList<Piece> pieces1;
    public ArrayList<Piece> pieces2;
    public ArrayList<Integer> cords;

    /**
     * Current state of the game constructor
     */
    public ShogiGameState() { //Cntr
        turn = first();
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
    public ShogiGameState(ShogiGameState orig) {
        this.turn = orig.turn;
        this.board = orig.board;
        this.grave_1 = orig.grave_1;
        this.grave_2 = orig.grave_2;
        this.pieces1 = new ArrayList<Piece>();
        this.pieces2 = new ArrayList<Piece>();
        // for loop through
        for (Piece p : orig.pieces1) {
            this.pieces1.add(p);
        }
        for(Piece p : orig.pieces2) {
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
        if (turn) {
            banner = "Player one's Turn";
        } else {
            banner = "Player two's turn";
        }
    }

    //Make methods for defined actions
    //make pieces
    private void assignPieces() {
        for (Piece.GAME_PIECES piece : Piece.GAME_PIECES.values()) {
            for (int i = 0; i < piece.getAmount(); i++) {
                switch (piece.getPlayer()) {
                    case 0:
                        pieces1.add(new Piece(piece, Piece.DIRECTION.FORWARD)); //player 1 (id = 0)
                        break;
                    case 1:
                        pieces2.add(new Piece(piece, Piece.DIRECTION.BACKWARD)); //player 2 (id = 1)
                        break;
                }
            } // for i
        } // for pieces
        placePieces(pieces1, 0);
        placePieces(pieces2, 1);
    }


    /**
     * Assigns rows and columns to each piece for initial setup for each player
     * Promotion pieces are unassigned due to them not existing on the board at that time
     */
    private void placePieces(ArrayList<Piece> piece, int id) { //board is 9x9 tiles
        //front row is 9 pawns
        //middle row is 1 space, bishop, 5 spaces, rook, 1 space (left to right from players pov)
        //back row is lance, knight, silver, gold, king, gold, silver, knight, lance
        int pawnNum = 0, lanceNum = 0, knightNum = 0, goldNum = 0, silvNum = 0;

        if (id == 0) { //forward facing pieces (player 1)
            for (Piece p : piece) {
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
            for (Piece p : piece) {
                switch (p.pieceType.getID()) { //What kind of piece is it
                    case R.drawable.opp_promo_bish:
                    case R.drawable.opp_promo_lance:
                    case R.drawable.opp_promo_knight:
                    case R.drawable.opp_promo_pawn:
                    case R.drawable.opp_promo_rook:
                    case R.drawable.opp_promo_silv:
                        //if it's a promoted piece skip it
                        break;
                    case R.drawable.opp_pawn:
                        p.setRow(2); //up down
                        p.setCol(pawnNum); //side to side
                        pawnNum++;
                        break;
                    case R.drawable.opp_bish: //switch cols for opponent bishop and rook bc perspective
                        p.setRow(1);
                        p.setCol(7);
                        break;
                    case R.drawable.opp_rook:
                        p.setRow(1);
                        p.setCol(1);
                        break;
                    case R.drawable.opp_lance:
                        p.setRow(0);
                        if (lanceNum == 0) {
                            p.setCol(0);
                        } else {
                            p.setCol(8);
                        }
                        lanceNum++;
                        break;
                    case R.drawable.opp_knight:
                        p.setRow(0);
                        if (knightNum == 0) {
                            p.setCol(1);
                        } else {
                            p.setCol(7);
                        }
                        knightNum++;
                        break;
                    case R.drawable.opp_silv_gen:
                        p.setRow(0);
                        if (silvNum == 0) {
                            p.setCol(2);
                        } else {
                            p.setCol(6);
                        }
                        silvNum++;
                        break;
                    case R.drawable.opp_gold_gen:
                        p.setRow(0);
                        if (goldNum == 0) {
                            p.setCol(3);
                        } else {
                            p.setCol(5);
                        }
                        goldNum++;
                        break;
                    case R.drawable.opp_king:
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
            return true; // should be false
        }
    }

    public boolean getTurn(){return turn;}
    public void setTurn(boolean _Turn){turn = _Turn;}

}
