package com.example.alpharelease.Shogi;

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

public class Graveyard {
    //Graveyard instance variables (for possible use)
    private float graveLength;
    private float graveWidth;
    private ArrayList<Piece> graveBoard = new ArrayList<Piece>();
    private ArrayList<Tile> graveTiles;

    //Graveyard Constructor
    public Graveyard() {
        //ensure that every initializing call to the graveyard is an empty grave
        graveTiles = new ArrayList<>();
    }

    //drop (remove from grave board and add to game board)
    /**
     * Drop and remove from the grave board and add to the game board
     */
    public void dropPiece() {
        //iterate through Array List by indexing pieces, use specified piece as key
        for(Piece piece: graveBoard){
            //check if specified piece is in graveyard
            if(graveBoard.contains(piece)){
                //possibly call movePiece() method to transfer piece on graveyard back into gameBoard
            }
            //else statement will be upgraded at a later moment for fluidity, for now, display text
            //fluidity here could mean two things:
            // (1) User can click and drag piece from graveyard to game board (requires changes in Board class and Graveyard class)
            // (2) Graveyard will be for now not shown but just a series of drop boxes that allows players to choose what pieces to drop and where
            else{
                //return text: "This piece is not in the graveyard"
            }
        }
    }

    /**
     * Add piece to graveyard method
     * (from capture remove from game board and put into grave board)
     */
    public void addPiece(Piece p){
        graveBoard.add(p);

        // (1) possibly call movePiece method to move the piece from the game board into the graveyard
        // (2) create separate function that is similar to capture piece but deals with transition between game board and grave yard (call that)

        //instantiate int variable val to use as comparator between size of board in order to iterate through board

        //Hardcoded bishop just for now
        //To make this fluid, might need to add getPiece function in Piece class
    }

}