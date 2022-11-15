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

import com.example.alpharelease.GameFramework.LocalGame;
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
    LocalGame lg;
    Paint paint;
    private int currID;
    private int lever;
    private int Piecex,Piecey;

    private Paint imgPaint;
    private ShogiLocalGame game;
    private ShogiGameState state;

    public MainSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs); // Call parent constructor
        // 2 Enable drawing
        setWillNotDraw(false);
        // 3 setup any required member variables
        imgPaint = new Paint();
        imgPaint.setColor(Color.BLACK);
        toggle = 0;
        imagesize = 696;
        buffersizeHoriz = 314;
        buffersizeVert = 14;
        xcord = -1;
        ycord = -1;
        tileSize = imagesize/9;
        paint  = new Paint();
        paint.setARGB(255, 255, 0, 0);
        currID = 0;
        lever = 0;
        Piecex = Piecey = -1;
        state = new ShogiGameState();
        game = new ShogiLocalGame(state);


        //spots = new ArrayList<Spot>(); // Optional to repeat or not repeat the type <Spot>
    }

    // 4 tell the view what to draw/how to draw
    protected void onDraw(Canvas canvas) {
        //TODO: Use state to draw the correct things
        // DO NOT, if at all possible, allocate anything in the draw method
        // METHOD (memory use optimization)
        // This method could run 100+ times per second (and potentially crash
        // -- a device if garbage collection is not fast enough

        //TODO: Move these images into the constructor so that we only have to create them once
        //draw the main board
        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.full_board);
        canvas.drawBitmap(image, buffersizeHoriz, buffersizeVert, imgPaint);

        //draw the graveyard


        //draw the initial setup for player 1
        for(Piece p: state.pieces1){
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            canvas.drawBitmap(image, ((tileSize) * p.getCol()) - ((tileSize) / 2), ((tileSize) * p.getRow()) - ((tileSize) / 2), imgPaint);
        }

        //draw the initial set up for player 2
        for(Piece p: state.pieces2){
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            canvas.drawBitmap(image, ((tileSize) * p.getCol()) - ((tileSize) / 2), ((tileSize) * p.getRow()) - ((tileSize) / 2), imgPaint);
        }

        //draw the possible moves
        for(int i = 0; i > state.cords.size(); i += 2){
            canvas.drawRect(tileSize * state.cords.get(i), tileSize * state.cords.get(i + 1), (tileSize * state.cords.get(i)) + tileSize, (tileSize * state.cords.get(i + 1)) + tileSize, paint);
        }
        // Moving the draw down here lets us draw on TOP of the image / circle above
        // For spots, can use for integer based loop or for each

        // button that causes the image or the circle appears
    }

    @Override
    public boolean onTouch(View view, MotionEvent e){
        //TODO: This is where to send moves to the game using game.sendAction(new ShogiAction)
        if (e.getActionMasked() == MotionEvent.ACTION_DOWN){
            float x = e.getX();
            float y = e.getY();
            System.out.println(x);
            System.out.println(y);

            if(x > buffersizeHoriz && x <= imagesize+buffersizeHoriz && y <= imagesize+buffersizeVert && y > buffersizeVert){
                // X cord

                //TODO: FOR LOOP THIS (OPTIMIZATION)
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
                lever = 0;
                if (lever == 0) { // first click""
                    System.out.println(state.getTurn());
                    if(state.getTurn()){
                        System.out.println("Inner");
                        for(Piece p : state.pieces1){
                            if(p.getCol() == xcord && p.getRow() ==ycord){
                                currID = p.pieceType.getID();
                                Piecex = p.getCol();
                                Piecey = p.getRow();
                                game.callCorrectMovement(currID,state.getTurn(),p.getCol(),p.getRow());
                                lever = 1;
                                System.out.println("!!! CORDDS !!!");
                                System.out.println(state.cords);
                                invalidate();
                                break;
                            } // if piece matches selected cords
                        } // for Piece p
                    }
                } // if lever == 0
                if(lever == 1){
                    for(int i = 0; i < state.cords.size(); i+= 2){
                        if(state.cords.get(i) == xcord && state.cords.get(i+1) == ycord){
                            if(state.getTurn()){
                                for(Piece p : state.pieces1){
                                    if(p.pieceType.getID() == currID && p.getCol() == Piecex && p.getRow() == Piecey){
                                        p.setCol(xcord);
                                        p.setRow(ycord);
                                        // flip turn aswell
                                        state.changeTurn();
                                        lever = 0;
                                        invalidate();
                                    }
                                }
                            }
                        }
                    } // for cords
                } // if lever == 1
                return true;
            }
        }
        invalidate();
        return false;
    }

    public void setLocalGame(LocalGame g) {
        lg = g;
    }

    public void setGameState(ShogiGameState g) {
        state = g;
    }
}