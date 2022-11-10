package com.example.alpharelease;

public class localGame {
    /*Includes methods for everything:
      selectPiece()
      move[Piece Name]()
      boundsCheck()
      obstacles()
      makeMove()
      knightMove()


      capturePiece()

      FOR BETA:
      promotePiece()
      dropPiece()

    */


    public void moveKing(boolean turn, int x, int y) {
           makeMove(turn, x, y, 1, 1, 1, 1, 1, 1, 1, 1);
    }


    public void moveGoldGen(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);
    }

    public void moveSilvGen(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 0, 0, 1, 0, 1);

    }

    public void movePromSilvGen(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);
    }

    public void moveBishop(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    public void movePromBishop(boolean turn, int x, int y){

    }

    public void moveRook(boolean turn, int x, int y){

    }

    public void movePromRook(boolean turn, int x, int y){

    }

    public void moveLance(boolean turn, int x, int y){

    }

    public void movePromLance(boolean turn, int x, int y){

    }

    public void moveKnight(boolean turn, int x, int y){

    }

    public void movePromKnight(boolean turn, int x, int y){

    }

    public void movePawn(boolean turn, int x, int y){

    }

    public void movePromPawn(boolean turn, int x, int y){

    }





}
