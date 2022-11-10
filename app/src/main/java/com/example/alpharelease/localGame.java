package com.example.alpharelease;

import java.util.ArrayList;

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
    private ArrayList<Piece> pieces1;
    private ArrayList<Piece> pieces2;
    private ArrayList<Integer> cords;

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
// TODO this function currently only makesMove for P1
    public void makeMove(boolean turn, int x, int y,int TL, int T, int TR, int L, int R, int BL, int B, int BR){
        int xcord = x;
        int ycord = y;
        // MOVE TOP LEFT
        for(int i = 1; i <= TL; i++){
            xcord = x-i;
            ycord = y-i;
            if(xcord < 0 || ycord < 0){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    break;
                }
            }
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
            // if it gets here, then nothing in space and can move
        }  // ======================== //
        // MOVE TOP
        for(int i = 1; i <= T; i++){
            xcord = x-i;
            if(xcord < 0){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE TOP RIGHT
        for(int i = 1; i <= TR; i++){
            xcord = x-i;
            ycord = y+i;
            if(xcord < 0 || ycord > 8){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE RIGHT
        for(int i = 1; i <= R; i++){
            ycord = y+i;
            if(ycord > 8){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE BOTTOM RIGHT
        for(int i = 1; i <= BR; i++){
            xcord = x+i;
            ycord = y+i;
            if(xcord > 8 || ycord > 8){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE BO†TOM
        for(int i = 1; i <= B; i++){
            xcord = x+i;
            if(xcord > 8){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE BOTTOM LEFT
        for(int i = 1; i <= BL; i++){
            xcord = x+i;
            ycord = y-i;
            if(xcord > 8 || ycord < 0){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
        // MOVE LEFT
        for(int i = 1; i <= L; i++){
            ycord = y-i;
            if(ycord < 0){
                break;
            }
            int notin = 0;
            for(Piece p : pieces1){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 1;
                    continue;
                } // if
            } // for
            for(Piece p: pieces2){
                if(p.getRow() == xcord && p.getCol() == ycord){
                    notin = 2;
                    break;
                }
            }
            if(notin == 0){
                cords.add(xcord);
                cords.add(ycord);
            }
            if(notin == 1){
                break;
            }
            if(notin == 2){
                cords.add(xcord);
                cords.add(ycord);
                break;
            }
        } // ======================== //
    }

    public void knightMove(int x){
        return;
    }

}
