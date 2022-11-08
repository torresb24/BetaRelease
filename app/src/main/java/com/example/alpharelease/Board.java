package com.example.alpharelease;

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
    // copy constructor
    ArrayList<Tile> tiles;
    int size;
    int tileSize;
    public Board(){
        tiles = new ArrayList<Tile>();
        size = 9;
        tileSize = 100;

        mkBoard();
    }

    private void mkBoard(){
        tiles.clear();

        for(int row = 0; row < size; row++){
            for(int col = 0; col < size; col++){
                Tile addTile = new Tile(row, col, tileSize*col,tileSize*row);
                tiles.add(addTile);
            }//for col
        }// for row
    }

    public int getTileSize(){return tileSize;}

}
