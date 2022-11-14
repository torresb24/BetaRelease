package com.example.alpharelease;

import android.graphics.Color;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.example.alpharelease.R;

import java.util.ArrayList;

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

public class MainSurfaceView extends SurfaceView implements View.OnTouchListener {

    private int toggle;
    private int imagesize;
    private int buffersizeHoriz;
    private int buffersizeVert;
    private int xcord;
    private int ycord;
    private int tileSize;
    localGame lg;
    Paint paint;

    private Paint imgPaint;

    public MainSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs); // Call parent constructor
        // 2 Enable drawing
        setWillNotDraw(false);
        // 3 setup any required member variables
        imgPaint = new Paint();
        imgPaint.setColor(Color.BLACK);
        toggle = 0;
        imagesize = 0;
        buffersizeHoriz = 550;
        buffersizeVert = 50;
        xcord = -1;
        ycord = -1;
        lg = new localGame();
        tileSize = imagesize/9;
        paint  = new Paint();
        paint.setARGB(255, 255, 0, 0);
        //spots = new ArrayList<Spot>(); // Optional to repeat or not repeat the type <Spot>
    }

    // 4 tell the view what to draw/how to draw
    protected void onDraw(Canvas canvas) {

        // DO NOT, if at all possible, allocate anything in the draw method
        // METHOD (memory use optimization)
        // This method could run 100+ times per second (and potentially crash
        // -- a device if garbage collection is not fast enough

        //draw the main board
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.full_board);
        canvas.drawBitmap(image, buffersizeHoriz, buffersizeVert, imgPaint);

        //draw the graveyard


        //draw the initial setup for player 1
        for(Piece p: lg.gs.pieces1){
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            canvas.drawBitmap(image, ((tileSize) * p.getCol()) - ((tileSize) / 2), ((tileSize) * p.getRow()) - ((tileSize) / 2), imgPaint);
        }

        //draw the initial set up for player 2
        for(Piece p: lg.gs.pieces2){
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            canvas.drawBitmap(image, ((tileSize) * p.getCol()) - ((tileSize) / 2), ((tileSize) * p.getRow()) - ((tileSize) / 2), imgPaint);
        }

        //draw the possible moves
        for(int i = 0; i > lg.gs.cords.size(); i += 2){
            canvas.drawRect(tileSize * lg.gs.cords.get(i), tileSize * lg.gs.cords.get(i + 1), (tileSize * lg.gs.cords.get(i)) + tileSize, (tileSize * lg.gs.cords.get(i + 1)) + tileSize, paint);
        }
        // Moving the draw down here lets us draw on TOP of the image / circle above
        // For spots, can use for integer based loop or for each

        // button that causes the image or the circle appears
    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            float x = e.getX();
            float y = e.getY();
            if(x > buffersizeHoriz && x <= imagesize+buffersizeHoriz && y <= imagesize+buffersizeVert && y > buffersizeVert){
                // X cord
                if(x < imagesize/9){xcord = 0;}
                else if(x > imagesize/9 && x < (imagesize/9)*2){xcord = 1;}
                else if(x > (imagesize/9)*2 && x < (imagesize/9)*3){xcord = 2;}
                else if(x > (imagesize/9)*3 && x < (imagesize/9)*4){xcord = 3;}
                else if(x > (imagesize/9)*4 && x < (imagesize/9)*5){xcord = 4;}
                else if(x > (imagesize/9)*5 && x < (imagesize/9)*6){xcord = 5;}
                else if(x > (imagesize/9)*6 && x < (imagesize/9)*7){xcord = 6;}
                else if(x > (imagesize/9)*7 && x < (imagesize/9)*8){xcord = 7;}
                else if(x > (imagesize/9)*8 && x < (imagesize/9)*9){xcord = 8;}
                // Y cord
                if(y < imagesize/9){ycord = 0;}
                else if(y > imagesize/9 && y < (imagesize/9)*2){ycord = 1;}
                else if(y > (imagesize/9)*2 && y < (imagesize/9)*3){ycord = 2;}
                else if(y > (imagesize/9)*3 && y < (imagesize/9)*4){ycord = 3;}
                else if(y > (imagesize/9)*4 && y < (imagesize/9)*5){ycord = 4;}
                else if(y > (imagesize/9)*5 && y < (imagesize/9)*6){ycord = 5;}
                else if(y > (imagesize/9)*6 && y < (imagesize/9)*7){ycord = 6;}
                else if(y > (imagesize/9)*7 && y < (imagesize/9)*8){ycord = 7;}
                else if(y > (imagesize/9)*8 && y < (imagesize/9)*9){ycord = 8;}
                if (lever == 0) {
                    
                }
                return true;
            }
        }
        invalidate();
        return false;
    }
}

    /*public class MainSurfaceView extends SurfaceView {



    Paint imagePaint;

    public MainSurfaceView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        generateViewId();
        setWillNotDraw(false);
        imagePaint = new Paint();

    } // constructor

    protected void onDraw(Canvas canvas) {
        // Commented out due to no change
        //imagePaint.setColor(0xffffa3c4);

        float left = 10.0F;
        float top = 10.0F;
        float right = 10.0F;
        float bottom = 10.0F;

        //canvas.drawRect(left,top,right,bottom);

        *//*

        // [you] pawns
        Bitmap xpawn9 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn8 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn7 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn6 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn5 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn4 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn3 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn2 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap xpawn1 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        // [you] lances
        Bitmap xlance1 = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        Bitmap xlance2 = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        // [you] knights
        Bitmap xknight1 = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        Bitmap xknight2 = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        // [you] silver generals
        Bitmap xsilv_gen1 = BitmapFactory.decodeResource(getResources(), R.drawable.silv_gen);
        Bitmap xsilv_gen2 = BitmapFactory.decodeResource(getResources(), R.drawable.silv_gen);
        // [you] gold generals
        Bitmap xgold_gen1 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_gen);
        Bitmap xgold_gen2 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_gen);
        // [you] bishop
        Bitmap xbishop1 = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        // [you] rook
        Bitmap xrook1 = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        // [you] king
        Bitmap xking1 = BitmapFactory.decodeResource(getResources(), R.drawable.king);
        Bitmap bmResult = Bitmap.createBitmap(xking1.getWidth(), xking1.getHeight(), Bitmap.Config.ARGB_8888);

        // [them] pawns
        Bitmap ypawn9 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn8 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn7 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn6 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn5 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn4 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn3 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn2 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        Bitmap ypawn1 = BitmapFactory.decodeResource(getResources(), R.drawable.pawn);
        // [them] lances
        Bitmap ylance1 = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        Bitmap ylance2 = BitmapFactory.decodeResource(getResources(), R.drawable.lance);
        // [them] knights
        Bitmap yknight1 = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        Bitmap yknight2 = BitmapFactory.decodeResource(getResources(), R.drawable.knight);
        // [them] silver generals
        Bitmap ysilv_gen1 = BitmapFactory.decodeResource(getResources(), R.drawable.silv_gen);
        Bitmap ysilv_gen2 = BitmapFactory.decodeResource(getResources(), R.drawable.silv_gen);
        // [them] gold generals
        Bitmap ygold_gen1 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_gen);
        Bitmap ygold_gen2 = BitmapFactory.decodeResource(getResources(), R.drawable.gold_gen);
        // [them] bishop
        Bitmap ybishop1 = BitmapFactory.decodeResource(getResources(), R.drawable.bishop);
        // [them] rook
        Bitmap yrook1 = BitmapFactory.decodeResource(getResources(), R.drawable.rook);
        // [them] king
        Bitmap yking1 = BitmapFactory.decodeResource(getResources(), R.drawable.king);
*//*
    }

}*/