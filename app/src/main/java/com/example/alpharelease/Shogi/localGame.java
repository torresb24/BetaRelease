package com.example.alpharelease.Shogi;

import com.example.alpharelease.Shogi.ShogiGameState;

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
    public ShogiGameState gs;
    public localGame(){
        gs = new ShogiGameState();
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
/*
    public void makeMove(boolean turn, int x, int y,int TL, int T, int TR, int L, int R, int BL, int B, int BR){
        int xcord = x;
        int ycord = y;
        gs.cords.clear();
        /**IF PLAYER 1
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
        /**IF PLAYER 2
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

    public void callCorrectMovement(int ID, boolean turn, int x, int y){
        switch(ID) {
            case (R.drawable.king):
                moveKing(turn, x, y);
                break;
            case (R.drawable.gold_gen): case(R.drawable.promoted_silv_gen): case (R.drawable.promoted_pawn): case (R.drawable.promoted_lance): case (R.drawable.promoted_knight):
                moveGoldGen(turn, x, y);
                break;
            case (R.drawable.silv_gen):
                moveKing(turn, x, y);
                break;
            case (R.drawable.bishop):
                moveKing(turn, x, y);
                break;
            case (R.drawable.promoted_bishop):
                moveKing(turn, x, y);
                break;
            case (R.drawable.rook):
                moveKing(turn, x, y);
                break;
            case (R.drawable.promoted_rook):
                moveKing(turn, x, y);
                break;
            case (R.drawable.lance):
                moveKing(turn, x, y);
                break;
            case (R.drawable.pawn):
                moveKing(turn, x, y);
                break;
            case (R.drawable.knight):
                moveKing(turn, x, y);
                break;
        }
    }
    // TODO move up to case switch only
    public void moveKing(boolean turn, int x, int y) {
           makeMove(turn, x, y, 1, 1, 1, 1, 1, 1, 1, 1);
    }

    public void moveGoldGen(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 1, 1, 0, 1, 0);
    }

    public void moveSilvGen(boolean turn, int x, int y){
        makeMove(turn, x, y, 1, 1, 1, 0, 0, 1, 0, 1);
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

    public void movePawn(boolean turn, int x, int y){
        makeMove(turn, x, y, 0, 1, 0, 0, 0, 0, 0, 0);
    }

    public void moveKnight(boolean turn, int x, int y){
        int xcord = x;
        int ycord = y;

        gs.cords.clear();

        /**IF PLAYER 1
        if(turn) {
            if (!(x - 1 < 0 || y - 2 < 0)) { //
                int lever = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == x - 1 && p.getCol() == y - 2) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    gs.cords.add(x);
                    gs.cords.add(y);
                }
            }

            if (!(x + 1 > 8 || y - 2 < 0)) { //
                int lever = 0;
                for (Piece p : gs.pieces1) {
                    if (p.getRow() == x + 1 && p.getCol() == y - 2 ) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    gs.cords.add(x);
                    gs.cords.add(y);
                }
            }
        }

        IF PLAYER 2
        if(!turn) {
            if (!(x - 1 < 0 || y + 2 < 0)) { //
                int lever = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == x - 1 && p.getCol() == y + 2) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    gs.cords.add(x);
                    gs.cords.add(y);
                }
            }

            if (!(x + 1 > 8 || y + 2 < 0)) { //
                int lever = 0;
                for (Piece p : gs.pieces2) {
                    if (p.getRow() == x + 1 && p.getCol() == y + 2 ) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    gs.cords.add(x);
                    gs.cords.add(y);
                }
            }
        }
            return;
    }
    public boolean inCheck(boolean turn){
        //loop through the pieces, find king
        //store king's x and y into coords
        //if coords is in danger, call move[Piece] methods

        //instantiate x and y Coords
        int xCoord = -1;
        int yCoord = -1;


        for(Piece p: gs.pieces1){
            if(p.pieceType.getID() == R.drawable.king){
                xCoord = p.getCol();
                yCoord = p.getRow();
                break;
            }
        }

        for(Piece p: gs.pieces1){
            if(p.pieceType.getID() == R.drawable.gold_gen){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_gold_gen){

            }
            if(p.pieceType.getID() == R.drawable.silv_gen){
                moveSilvGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_silv_gen){
                movePromSilvGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.bishop){
                moveBishop(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_bishop){
                movePromBishop(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.rook){
                moveRook(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_rook){
                movePromRook(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.lance){
                moveLance(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_lance){
                movePromLance(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.knight){
                //TODO: moveKnight
            }
            if(p.pieceType.getID() == R.drawable.promoted_knight){
                movePromKnight(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.pawn){
                movePawn(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_pawn){
                movePromPawn(turn, p.getCol(), p.getRow());
                for(int i = 0; i < gs.cords.size(); i += 2){
                    if(gs.cords.get(i) == xCoord && gs.cords.get(i + 1) == yCoord){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkMate(boolean turn){
        if (turn) {
            if (gs.pieces1.size() == 1) {
                return true;
            }
        }

        if (!turn) {
            if (gs.pieces2.size() == 1) {
                return true;
            }
        }
        return false;
    }*/
}
