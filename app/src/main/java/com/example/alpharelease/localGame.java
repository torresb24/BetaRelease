package com.example.alpharelease;

import java.util.ArrayList;

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
    GameState gs;
    public localGame(){
        gs = new GameState();
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
// TODO this function currently only makesMove for P1
    public void makeMove(boolean turn, int x, int y,int TL, int T, int TR, int L, int R, int BL, int B, int BR){
        int xcord = x;
        int ycord = y;
        /**IF PLAYER 1*/
        if(turn) {
            // MOVE TOP LEFT
            for (int i = 1; i <= TL; i++) {
                xcord = x - i;
                ycord = y - i;
                if (xcord < 0 || ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        break;
                    }
                }
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
                // if it gets here, then nothing in space and can move
            }  // ======================== //
            // MOVE TOP
            for (int i = 1; i <= T; i++) {
                xcord = x - i;
                if (xcord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE TOP RIGHT
            for (int i = 1; i <= TR; i++) {
                xcord = x - i;
                ycord = y + i;
                if (xcord < 0 || ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE RIGHT
            for (int i = 1; i <= R; i++) {
                ycord = y + i;
                if (ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BOTTOM RIGHT
            for (int i = 1; i <= BR; i++) {
                xcord = x + i;
                ycord = y + i;
                if (xcord > 8 || ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BO†TOM
            for (int i = 1; i <= B; i++) {
                xcord = x + i;
                if (xcord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BOTTOM LEFT
            for (int i = 1; i <= BL; i++) {
                xcord = x + i;
                ycord = y - i;
                if (xcord > 8 || ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE LEFT
            for (int i = 1; i <= L; i++) {
                ycord = y - i;
                if (ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
        }
        /**IF PLAYER 2*/
        if(!turn) {
            // MOVE TOP LEFT
            for (int i = 1; i <= TL; i++) {
                xcord = x - i;
                ycord = y - i;
                if (xcord < 0 || ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        break;
                    }
                }
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
                // if it gets here, then nothing in space and can move
            }  // ======================== //
            // MOVE TOP
            for (int i = 1; i <= T; i++) {
                xcord = x - i;
                if (xcord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE TOP RIGHT
            for (int i = 1; i <= TR; i++) {
                xcord = x - i;
                ycord = y + i;
                if (xcord < 0 || ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE RIGHT
            for (int i = 1; i <= R; i++) {
                ycord = y + i;
                if (ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BOTTOM RIGHT
            for (int i = 1; i <= BR; i++) {
                xcord = x + i;
                ycord = y + i;
                if (xcord > 8 || ycord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BO†TOM
            for (int i = 1; i <= B; i++) {
                xcord = x + i;
                if (xcord > 8) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE BOTTOM LEFT
            for (int i = 1; i <= BL; i++) {
                xcord = x + i;
                ycord = y - i;
                if (xcord > 8 || ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
            // MOVE LEFT
            for (int i = 1; i <= L; i++) {
                ycord = y - i;
                if (ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                }
                if (notin == 1) {
                    break;
                }
                if (notin == 2) {
                    gs.cords.add(xcord);
                    gs.cords.add(ycord);
                    break;
                }
            } // ======================== //
        }
    }


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
        makeMove(turn, x, y, 7, 0, 7, 0, 0, 7, 0, 7);
    }

    public void movePromBishop(boolean turn, int x, int y){
        makeMove(turn, x, y, 7, 1, 7, 1, 1, 7, 1, 7);

    }

    public void moveRook(boolean turn, int x, int y){
        makeMove(turn, x, y, 0, 8, 0, 8, 8, 0, 8, 0);

    }

    public void movePromRook(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 8, 1, 8, 8, 1, 8, 1);

    }

    public void moveLance(boolean turn, int x, int y){
        makeMove(turn, x, y, 0, 8, 0, 0, 0, 0, 0, 0);

    }

    public void movePromLance(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);

    }

    public void movePromKnight(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);

    }

    public void movePawn(boolean turn, int x, int y){
        makeMove(turn, x, y, 0, 1, 0, 0, 0, 0, 0, 0);

    }

    public void movePromPawn(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);

    }




    public void moveKnight(int x){
        return;
    }


}
