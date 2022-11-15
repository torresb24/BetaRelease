package com.example.alpharelease;

import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.actionMessage.GameAction;
import com.example.alpharelease.GameFramework.players.GamePlayer;

public class ShogiLocalGame extends LocalGame {

    public ShogiLocalGame() {
        super();
        super.state = new ShogiGameState();
    }

    public ShogiLocalGame(ShogiGameState estate) {
        super();
        super.state = new ShogiGameState(estate);
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        p.sendInfo(new ShogiGameState((ShogiGameState) state));
    }

    @Override
    protected boolean canMove(int playerIdx) {
        // TODO:
        return false;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        //TODO: Using action, makeMove, and return true if move was made
        ShogiGameState theState = (ShogiGameState) state;
        // ShogiAction
        return false;
    }

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
    //public ShogiGameState gs;
    //public localGame(){
    //    gs = new ShogiGameState();
    //}
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
    public void makeMove(boolean turn, int x, int y,int TL, int T, int TR, int L, int R, int BL, int B, int BR){
        int xcord = x;
        int ycord = y;
        ((ShogiGameState) state).cords.clear();
        
        /**IF PLAYER 1*/
        if(turn) {
            System.out.println("MOVING PAWN3");
            // MOVE TOP LEFT
            for (int i = 1; i <= TL; i++) {
                xcord = x - i;
                ycord = y - i;
                if (xcord < 0 || ycord < 0) {
                    break;
                }
                int notin = 0;
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        break;
                    }
                }
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                    break;
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        break;
                    }
                }
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
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
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 1;
                        continue;
                    } // if
                } // for
                if (notin == 1) {
                    break;
                }
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == xcord && p.getCol() == ycord) {
                        notin = 2;
                        break;
                    }
                }
                if (notin == 0) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                }
                if (notin == 2) {
                    ((ShogiGameState) state).cords.add(xcord);
                    ((ShogiGameState) state).cords.add(ycord);
                    break;
                }
            } // ======================== //
        }
        System.out.println(((ShogiGameState) state).cords);
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
                moveSilvGen(turn, x, y);
                break;
            case (R.drawable.bishop):
                moveBishop(turn, x, y);
                break;
            case (R.drawable.promoted_bishop):
                movePromBishop(turn, x, y);
                break;
            case (R.drawable.rook):
                moveRook(turn, x, y);
                break;
            case (R.drawable.lance):
                moveLance(turn, x, y);
                break;
            case (R.drawable.pawn):
                movePawn(turn, x, y);
                break;
            case (R.drawable.knight):
                moveKnight(turn, x, y);
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

        ((ShogiGameState) state).cords.clear();
        

        /**IF PLAYER 1*/
        if(turn) {
            if (!(x - 1 < 0 || y - 2 < 0)) { //
                int lever = 0;
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == x - 1 && p.getCol() == y - 2) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    ((ShogiGameState) state).cords.add(x);
                    ((ShogiGameState) state).cords.add(y);
                }
            }

            if (!(x + 1 > 8 || y - 2 < 0)) { //
                int lever = 0;
                for (Piece p : ((ShogiGameState) state).pieces1) {
                    if (p.getRow() == x + 1 && p.getCol() == y - 2 ) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    ((ShogiGameState) state).cords.add(x);
                    ((ShogiGameState) state).cords.add(y);
                }
            }
        }

        /**IF PLAYER 2*/
        if(!turn) {
            if (!(x - 1 < 0 || y + 2 < 0)) { //
                int lever = 0;
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == x - 1 && p.getCol() == y + 2) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    ((ShogiGameState) state).cords.add(x);
                    ((ShogiGameState) state).cords.add(y);
                }
            }

            if (!(x + 1 > 8 || y + 2 < 0)) { //
                int lever = 0;
                for (Piece p : ((ShogiGameState) state).pieces2) {
                    if (p.getRow() == x + 1 && p.getCol() == y + 2 ) {
                        lever = 1;
                        break;
                    }
                }

                if (lever == 0) {
                    ((ShogiGameState) state).cords.add(x);
                    ((ShogiGameState) state).cords.add(y);
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


        for(Piece p: ((ShogiGameState) state).pieces1){
            if(p.pieceType.getID() == R.drawable.king){
                xCoord = p.getCol();
                yCoord = p.getRow();
                break;
            }
        }

        for(Piece p: ((ShogiGameState) state).pieces1){
            if(p.pieceType.getID() == R.drawable.gold_gen){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_gold_gen){

            }
            if(p.pieceType.getID() == R.drawable.silv_gen){
                moveSilvGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_silv_gen){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.bishop){
                moveBishop(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_bishop){
                movePromBishop(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.rook){
                moveRook(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_rook){
                movePromRook(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.lance){
                moveLance(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_lance){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.knight){
                //TODO: moveKnight
            }
            if(p.pieceType.getID() == R.drawable.promoted_knight){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.pawn){
                movePawn(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }

            }
            if(p.pieceType.getID() == R.drawable.promoted_pawn){
                moveGoldGen(turn, p.getCol(), p.getRow());
                for(int i = 0; i < ((ShogiGameState) state).cords.size(); i += 2){
                    if(((ShogiGameState) state).cords.get(i) == xCoord && ((ShogiGameState) state).cords.get(i + 1) == yCoord){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean checkMate(boolean turn){
        if (turn) {
            if (((ShogiGameState) state).pieces1.size() == 1) {
                return true;
            }
        }

        if (!turn) {
            if (((ShogiGameState) state).pieces2.size() == 1) {
                return true;
            }
        }
        return false;
    }
}
