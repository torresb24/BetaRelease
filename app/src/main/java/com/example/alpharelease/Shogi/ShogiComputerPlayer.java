package com.example.alpharelease.Shogi;

import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.players.GameComputerPlayer;

public class ShogiComputerPlayer extends GameComputerPlayer {

    private ShogiGameState state;

    public ShogiComputerPlayer(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        //TODO: Send move actions to the game with game.sendAction(new Shogi-Something-Action)

        state = (ShogiGameState) info;

    }





    /*
    * else if (!state.getTurn()) {
                        int randIndex = -1;
                        pieceCol = -1;
                        pieceRow = -1;
                        Random rand = new Random();
                        while (state.cords.isEmpty()) {
                            randIndex = rand.nextInt(state.pieces2.size());
                            pieceCol = state.pieces2.get(randIndex).getCol();
                            pieceRow = state.pieces2.get(randIndex).getRow();
                            currID = state.pieces2.get(randIndex).pieceType.getID();

                            state.cords = game.callCorrectMovement(currID,state.getTurn(), pieceCol, pieceRow);
                            if (currID == R.drawable.promoted_bishop || currID == R.drawable.promoted_knight ||
                                    currID == R.drawable.promoted_lance ||
                                    currID == R.drawable.promoted_pawn ||
                                    currID == R.drawable.promoted_rook ||
                                    currID == R.drawable.promoted_silv_gen) {
                                state.cords.clear();
                            }
                        }
                        randIndex = 1;
                        while(randIndex%2 != 0){
                            randIndex = rand.nextInt(state.cords.size());
                        }
                        for(Piece p: state.pieces2){
                            if(pieceCol == p.getCol() && pieceRow == p.getRow() && p.pieceType.getID() == currID) {
                                p.setCol(state.cords.get(randIndex));
                                p.setRow(state.cords.get(randIndex+1));
                            }
                        }
                        holdCords.clear();
                        for (int j = 0; j < state.pieces1.size()-1; j++) {
                            if(state.pieces1.get(j).getCol() == state.cords.get(randIndex) &&
                             (state.pieces2.get(j).getRow() == state.cords.get(randIndex+1))) {
                                holdCords.add(j);
                            }
                        }
                        Collections.sort(holdCords);
                        Collections.reverse(holdCords);
                        for(Integer l : holdCords){
                            state.pieces1.remove(l);
                        }
                        state.cords.clear();
                        if(game.checkMate(state.getTurn())){
        //GAME END
                            System.exit(0);
}
                        state.setTurn(!state.getTurn());
                                invalidate();
                                }
    *
    *
    * */
}
