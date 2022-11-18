package com.example.alpharelease.Shogi;

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

public class Tile {
    private int row;
    private int col;
    private float xCord; // top left
    private float yCord; // top left
    public Tile() {
        row = 0;
        col = 0;
        xCord = 0;
        yCord = 0;
    }

    public Tile(int row, int col, float xCord, float yCord) {
        this.row = row;
        this.col = col;
        this.xCord = xCord;
        this.yCord = yCord;
    }

//    public int getCol() {
//        return col;
//    }
//
//    public int getRow() {
//        return row;
//    }
//
//    public void setCol(int col) {
//        this.col = col;
//    }
//
//    public void setRow(int row) {
//        this.row = row;
//    }

    //    public float getXcord(){return xcord;}
//    public float getYcord(){return ycord;}
}
