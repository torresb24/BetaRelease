package com.example.alpharelease;

import static org.junit.Assert.*;

import org.junit.Test;
import android.graphics.Point;

import com.example.alpharelease.Shogi.ShogiGameState;

public class ShogiUnnitTest {
    @Test
    public void TestInitPiecces() throws Exception{
        ShogiGameState testState = new ShogiGameState();
        testState
    }
    @Test
    public void TestCopyConstructor() throws Exception {
        ShogiGameState testState = new ShogiGameState();
        testState.changeTurn(10);
        ShogiGameState testStateCopy = new ShogiGameState(testState);
        assertEquals(10,testStateCopy.getWhoseTurn());
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
