package com.example.alpharelease;

import static org.junit.Assert.*;

import org.junit.Test;
import android.graphics.Point;

import com.example.alpharelease.Shogi.Board;
import com.example.alpharelease.Shogi.Piece;
import com.example.alpharelease.Shogi.ShogiGameState;

import java.util.ArrayList;

public class ShogiUnnitTest {
    @Test
    public void TestInitPieces() throws Exception{
        ShogiGameState testState = new ShogiGameState();
        int KingRow = -1;
        int KingCol = -1;
        for(int i = 0; i < testState.getPieceArray(0).size(); i++){
            if(testState.getPieceArray(0).get(i).pieceType.getID() == R.drawable.king){
                KingCol = testState.getPieceArray(0).get(i).getCol();
                KingRow = testState.getPieceArray(0).get(i).getRow();
                break;
            }
        }// for
        assertEquals(4,KingCol);
        assertEquals(0,KingRow);
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

    //Testing Implementations for Group Member Brent Torres
    @Test
    public void testMakeBoard() throws Exception{
        Board testObject = new Board();



    }

    @Test
    public void testAssignTile() throws Exception{
        //test that initial tile for human player king is row 0 col 4
        Board testObject = new Board();

        //initialize tile coordinates to false coordinates
        int kingTileRow = -1;
        int kingTileCol = -1;

        //Get tile coords



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

    @Test //Not yet implemented
    public void testInCheckmate() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.setInCheckmate(true);
        boolean t1 = testState.isInCheckmate();
        testState.setInCheckmate(false);
        boolean t2 = testState.isInCheckmate();
        assertEquals(true,t1);
        assertEquals(false,t2);
    }

}
