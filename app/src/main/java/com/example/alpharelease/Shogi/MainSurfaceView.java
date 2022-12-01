package com.example.alpharelease.Shogi;

import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.utilities.FlashSurfaceView;
import com.example.alpharelease.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Kathryn Weidman
 * @author Emma Kelly
 * @author Brent Torres
 * @author Matthew Tran
 *
 * @version 11/09/2022
 *
 * */

public class MainSurfaceView extends SurfaceView {

    /**
     External Citation
     Date: 23 November 2022
     Problem: Not exactly a problem, but the code to draw the piece bitmaps was rather clumsy
     Resource: Nathaniel Hopper
     Solution: Use a matrix to rotate the pieces rather than having so many drawable resources
     */

    private final Paint imgPaint;
    private final Paint paint;


    private ShogiGameState state;
    private final ShogiLocalGame game;
    private LocalGame lg;
    private Board board;

    private ArrayList<Tile> tiles;

    public MainSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs); // Call parent constructor
        // 2 Enable drawing
        setWillNotDraw(false);
        // 3 setup any required member variables

        state = new ShogiGameState();
        game = new ShogiLocalGame(state);

        paint = new Paint();
        paint.setARGB(255/2, 100, 155, 160);
        imgPaint = new Paint();
        imgPaint.setColor(Color.BLACK);
    }

    // 4 tell the view what to draw/how to draw
    protected void onDraw(Canvas canvas) {

        Bitmap image;
        Piece piece;

        board = state.getBoard();
        tiles = board.getTiles();

        for (Tile t : tiles) {
            piece = t.getPiece();
            if (piece == null) {
                continue;
            }

            image = BitmapFactory.decodeResource(getResources(), piece.pieceType.getID());

            // TODO: change this to rotate depending on who you are (rotate player 1's if you're player 0,
            //  rotate player 0's if you're p1)
            if (piece.pieceType.getPlayer() == 1) {
                Matrix matrix = new Matrix();
                matrix.postRotate(180);
                image = Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
            }
            canvas.drawBitmap(image, t.getxCoord(), t.getyCoord(), imgPaint);
        }
        board.drawBoard(canvas);
    }

    public void setLocalGame(LocalGame g) {
        lg = g;
    }

    public void setGameState(ShogiGameState g) {
        state = g;
    }

    public ShogiGameState getGameState() {
        return state;
    }
}