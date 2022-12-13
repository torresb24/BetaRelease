package com.example.alpharelease;

import static org.junit.Assert.*;

import org.junit.Test;
import android.graphics.Point;
import android.util.Log;

import com.example.alpharelease.Shogi.Board;
import com.example.alpharelease.Shogi.Piece;
import com.example.alpharelease.Shogi.ShogiGameState;

import java.util.ArrayList;
import java.util.Random;

public class ShogiUnnitTest {
    @Test
    public void TestInitPieces() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        int KingRow = -1;
        int KingCol = -1;
        for(int i = 0; i < testState.getPieceArray(0).size(); i++) {
            if(testState.getPieceArray(0).get(i).pieceType.getID() == R.drawable.king) {
                KingCol = testState.getPieceArray(0).get(i).getCol();
                KingRow = testState.getPieceArray(0).get(i).getRow();
                break;
            }
        }// for
        assertEquals(4,KingCol);
        assertEquals(8,KingRow);

        // test edge case
        int LanceRow = -1;
        int LanceCol = -1;
        for(int i = 0; i < testState.getPieceArray(0).size(); i++) {
            if(testState.getPieceArray(0).get(i).pieceType.getID() == R.drawable.lance) {
                LanceCol = testState.getPieceArray(0).get(i).getCol();
                LanceRow = testState.getPieceArray(0).get(i).getRow();
                break;
            }
        }// for
        assertEquals(0,LanceCol);
        assertEquals(8,LanceRow);
    } /** Matt Tran */

    @Test
    public void TestCopyConstructor() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.changeTurn(10);
        ShogiGameState testStateCopy = new ShogiGameState(testState);
        assertEquals(10,testStateCopy.getWhoseTurn());
    } /** Matt Tran */

    @Test
    public void TestSetSelecting() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.setSelecting(true);
        boolean t1 = testState.isSelecting();
        testState.setSelecting(false);
        boolean t2 = testState.isSelecting();
        assertEquals(true,t1);
        assertEquals(false,t2);
    } /** Matt Tran */

    /**Testing Implementations for Brent Torres*/

    @Test
    public void testAssignPieces() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        Random rand = new Random();
        int randIndex = rand.nextInt(testState.pieces1.size());
        assertEquals(Piece.DIRECTION.FORWARD, testState.pieces1.get(randIndex).directionMovement);

        randIndex = rand.nextInt(testState.pieces1.size());
        assertEquals(Piece.DIRECTION.BACKWARD, testState.pieces2.get(randIndex).directionMovement);
    }

    @Test
    public void testMakeBoard() throws Exception {
        Board testObject = new Board();

        //test size
        assertEquals(9*9,testObject.getTiles().size());
        // test out of bounds
        assertEquals(null,testObject.getTile(-1,8));
        assertEquals(null,testObject.getTile(8,-1));

        //front & back
        assertEquals(0,testObject.getTile(0).getCol());
        assertEquals(0,testObject.getTile(0).getRow());
        assertEquals(8,testObject.getTile(80).getCol());
        assertEquals(8,testObject.getTile(80).getRow());
    }

    @Test
    public void testAssignTile() throws Exception {
        //test that initial tile for human player king is row 0 col 4
        Board testObject = new Board();

        //Get tile coords
        int tile0Row = testObject.getTile(0).getRow();
        int tile0Col = testObject.getTile(0).getCol();

        int tile1Row = testObject.getTile(7).getRow();
        int tile1Col = testObject.getTile(7).getCol();

        int tile2Row = testObject.getTile(80).getRow();
        int tile2Col = testObject.getTile(80).getCol();

            //ensure that King is on tile col 4 row 0 through assert equals
        assertEquals(0, tile0Row);
        assertEquals(0, tile0Col);

        assertEquals(0, tile1Row);
        assertEquals(7, tile1Col);

        assertEquals(8, tile2Row);
        assertEquals(8, tile2Col);
    }

    // Tests by Emma Kelly
    @Test
    public void testTurnPiecesDiffer() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        ShogiGameState testState2 = new ShogiGameState();

        ArrayList<Piece> t1 = testState.getPieceArray(0);
        ArrayList<Piece> t2 = testState.getPieceArray(1);
        ArrayList<Piece> t3 = testState.getPieceArray(0);

        assertNotEquals(t1, t2);
        assertEquals(t1, t3);
    }

    @Test //Not yet implemented
    public void testInCheck() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.setInCheck(true);
        boolean t1 = testState.isInCheck();
        testState.setInCheck(false);
        boolean t2 = testState.isInCheck();
        assertEquals(true,t1);
        assertEquals(false,t2);
    }

    @Test
    public void testInCheckmate() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.setInCheckmate(true);
        boolean t1 = testState.isInCheckmate();
        testState.setInCheckmate(false);
        boolean t2 = testState.isInCheckmate();
        assertEquals(true,t1);
        assertEquals(false,t2);
    }

    // Kathryn's Tests
    @Test
    public void testCheckMoves() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        Board testBoard = testState.getBoard();
        //King
        assertEquals(3,testBoard.checkMoves(testBoard.getTile(4,8)).size());
        assertEquals(7,testBoard.checkMoves(testBoard.getTile(4,8)).get(0).getRow());
        //Edge Lance
        assertEquals(4,testBoard.checkMoves(testBoard.getTile(0,0)).size());
        assertEquals(1,testBoard.checkMoves(testBoard.getTile(0,8)).get(0).getRow());
    }

    @Test
    public void testFirstToGo() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        int first = testState.first();
        // 1 or 0 only
        assertTrue(first == 1 || first == 0);
    }

    @Test
    public void testPromote() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        Board testBoard = testState.getBoard();

        // promote pawn
        testBoard.promote(testBoard.getTile(0,6),testState,0);
        assertEquals(R.drawable.promoted_pawn, testBoard.getTile(0,6).getPiece().pieceType.getID());
        // enemy pawn
        testBoard.promote(testBoard.getTile(0,2),testState,1);
        assertEquals(R.drawable.promoted_pawn, testBoard.getTile(0,2).getPiece().pieceType.getID());

        // promote lance
        testBoard.promote(testBoard.getTile(0,8),testState,0);
        assertEquals(R.drawable.promoted_lance, testBoard.getTile(0,8).getPiece().pieceType.getID());

        // promote king [Can't promote]
        testBoard.promote(testBoard.getTile(4,8),testState,0);
        assertEquals(R.drawable.king, testBoard.getTile(4,8).getPiece().pieceType.getID());
    }
}