package com.example.alpharelease;

public class localGame {
    /*Includes methods for everything:
      selectPiece()
      move[Piece Name]()
      boundsCheck()
      obstacles()
      makeMove()
      makeMove()
      knightMove()


      capturePiece()

      FOR BETA:
      promotePiece()
      dropPiece()
    */
    Board board;
    public localGame(){
        board = new Board();
    }
/**
 * top left      +   TL
 * top           +   T
 * top right     +   TR
 * left          +   L
 * right         +   R
 * bottom left   +   BL
 * bottom        +   B
 * bottom right  +   BR
 * */
    public void makeMove(int x, int y,int TL, int T, int TR, int L, int R, int BL, int B, int BR){
        int xcord = x;
        int ycord = y;
        for(int i = 1; i <= TL; i++){
            xcord = x+i;
            ycord = y+i;
            if(xcord > 8 || ycord > 8){
                break;
            }
            int notin = 0;
            for(Piece p : Arraylist){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            // if it gets here, then nothing in space and can move
        }
    }

    public void knightMove(int x){
        return;
    }

}
