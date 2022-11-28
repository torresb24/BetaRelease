package com.example.alpharelease;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.alpharelease.Shogi.ShogiLocalGame;
import com.example.alpharelease.Shogi.Tile;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    public void createTile_isCorrect() {
        Tile t1 = new Tile();

        t1.setCol(1);
        t1.setRow(2);

        assertEquals(1, t1.getCol());
        assertEquals(2, t1.getRow());
    }

    public void setCords_isCorrect() {
        Tile t1 = new Tile();

        t1.setCords(1,2,3,4);

        assertEquals(1,t1.getxCord());
        assertEquals(2,t1.getyCord());
        assertEquals(3,t1.getxCordEnd());
        assertEquals(4,t1.getyCordEnd());
    }

    public void gameCopy_isCorrect() {
        ShogiLocalGame g1 = new ShogiLocalGame();
        ShogiLocalGame g2 = new ShogiLocalGame();

        g2.moveKnight(false,1,1);

        assertNotEquals(g1, g2);
    }

    /*public void setCords(int x, int y, int xEnd, int yEnd) {
        this.xCord = x;
        this.yCord = y;
        this.xCordEnd = xEnd;
        this.yCordEnd = yEnd;
    }*/
}