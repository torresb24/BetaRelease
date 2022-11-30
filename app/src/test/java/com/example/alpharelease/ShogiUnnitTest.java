package com.example.alpharelease;

import static org.junit.Assert.*;

import org.junit.Test;
import android.graphics.Point;

import com.example.alpharelease.Shogi.ShogiGameState;

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
    }

    //Testing Implementations for Group Member Brent Torres
    @Test
    public void testMakeBoard() throws Exception{
        ShogiGameState testObject = new ShogiGameState();

    }

    @Test
    public void testAssignTile() throws Exception{
        ShogiGameState testObject = new ShogiGameState();

    }
    
}
