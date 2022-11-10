package com.example.alpharelease;

import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.alpharelease.R;

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
public class MainSurfaceView extends SurfaceView{
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

        // initial board setup --

        Bitmap board = BitmapFactory.decodeResource(getResources(), R.drawable.shogi_board);
        Bitmap graveyard_you = BitmapFactory.decodeResource(getResources(), R.drawable.shogi_grave);
        Bitmap graveyard_them = BitmapFactory.decodeResource(getResources(), R.drawable.shogi_grave);
        // Commented out due to duplicate overlap
        canvas.drawBitmap(board, 550, -10, imagePaint);

        // Commented out due to duplicate overlaps
        //canvas.drawBitmap(graveyard_them, 3, 3, imagePaint);
        //canvas.drawBitmap(graveyard_you, 1547, 636, imagePaint);
        /**
         color.setARGB(255, 128, 128, 128);
         canvas.drawRect(startingX + 2 * i * squareSize, startingY + 2 * j * squareSize, startingX + (2 * i + 1) * squareSize, startingY + (2 * j +1) * squareSize, color);
         canvas.drawRect(startingX + (2*i+1) * squareSize, startingY + (2*j+1) *squareSize, startingX + (2*i+2)*squareSize, startingY + (2*j+2) *squareSize, color);

         color.setARGB(255, 0, 0, 0);
         canvas.drawRect(startingX + (2 * i + 1) * squareSize, startingY + 2 * j * squareSize, startingX + (2 * i + 2) * squareSize, startingY + (2 * j +1) * squareSize, color);
         canvas.drawRect(startingX + (2*i) *squareSize, startingY + (2*j+1)*squareSize, startingX + (2*i +1)*squareSize, startingY + (2*j+1) * squareSize, color);
         */

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

    }

}
