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

public class MainSurfaceView extends SurfaceView implements View.OnTouchListener {

    private int toggle;
    private int imagesize;
    private int boardLeft, boardTop, boardRight, boardBottom;

    private int boardCol;
    private int boardRow;
    private int tileSize;
    LocalGame lg;
    Paint paint;

    private int currID;
    private int lever;
    private int pieceCol, pieceRow;
    private int noMove;

    private Paint imgPaint;
    private Paint P2paint;

    private ShogiLocalGame game;
    private ShogiGameState state;
    private Board board;

    Matrix transform;
    private ArrayList<Integer> holdCords;

    private ArrayList<Tile> tiles;

    public MainSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs); // Call parent constructor
        // 2 Enable drawing
        setWillNotDraw(false);
        // 3 setup any required member variables

        state = new ShogiGameState();
        game = new ShogiLocalGame(state);

        imgPaint = new Paint();
        imgPaint.setColor(Color.BLACK);
        toggle = 0;
        imagesize = 1030;
        //buffersizeHoriz = 563;
        //buffersizeVert = 14;

        boardLeft = 479;
        boardTop = 24;
        boardRight = 1516;
        boardBottom = 1063;

        boardCol = boardRow = -100;
        tileSize = imagesize / 9 - 3;
        tiles = new ArrayList<>();


        board = state.getBoard();

        paint = new Paint();
        P2paint = new Paint();
        paint.setARGB(255/2, 255, 145, 164);
        P2paint.setARGB(255/2, 199, 0, 200);

        currID = 0;
        lever = 0;
        pieceCol = pieceRow = -1;
        noMove = 0;
        holdCords = new ArrayList<Integer>();

        transform = new Matrix();
        transform.preRotate(180);
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

        Bitmap image;

        //draw the initial setup for player 1
        for (Piece p: state.pieces1) {
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            canvas.drawBitmap(image, boardLeft + ((tileSize) * p.getCol()), ((tileSize) * p.getRow()), imgPaint);
        }

        //draw the initial set up for player 2
        for (Piece p: state.pieces2) {
            image = BitmapFactory.decodeResource(getResources(), p.pieceType.getID());
            transform.setTranslate(boardLeft + ((tileSize) * p.getCol()),((tileSize) * p.getRow()));
            canvas.drawBitmap(image, transform, imgPaint);
        }

        board.drawBoard(canvas);

        //draw the possible moves
        /*for (int i = 0; i < state.cords.size(); i += 2) {
            canvas.drawRect(tileSize * state.cords.get(i) + buffersizeHoriz/25,
                    tileSize * state.cords.get(i + 1),
                    (tileSize * state.cords.get(i)) + tileSize + buffersizeHoriz/25,
                    (tileSize * state.cords.get(i + 1)) + tileSize, paint);
        }*/
        // Moving the draw down here lets us draw on TOP of the image / circle above
        // For spots, can use for integer based loop or for each

        // button that causes the image or the circle appears
    }

    @Override
    public boolean onTouch(View view, MotionEvent e) {
        //TODO: This is where to send moves to the game using game.sendAction(new ShogiAction)

        if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
            float x = e.getX();
            float y = e.getY();

            if (board.onBoard(x, y)) { //If they touched the board
                Tile chosen = new Tile();
                chosen = board.getTouchedTile(x, y);

                noMove = 2;
                if (lever == 0) { // first click""
                 //   System.out.println(state.getTurn());
                    if (state.getTurn()) {
                        for (Piece p : state.pieces1) {
                            if (p.getCol() == boardCol && p.getRow() == boardRow) {
                                currID = p.pieceType.getID();
                                pieceCol = p.getCol();
                                pieceRow = p.getRow();
                                state.cords.clear();

                                state.cords = game.callCorrectMovement(currID,state.getTurn(), pieceCol, pieceRow);
                                lever = 1;
                                break;
                            } // if piece matches selected cords
                        } // for Piece p
                        // CHECK IF ITS OWN PIECE //
                        /**
                        for(Piece p: state.pieces1){
                            for(int k = 0; k < state.cords.size(); k+=2){
                                if(p.getRow() == state.cords.get(k) && p.getCol() == state.cords.get(k+1)){
                                    state.cords.set(k+1,-10);
                                    state.cords.set(k,-10);
                                }
                            }
                        }
                        for(int k = 0; k < state.cords.size(); k++){
                            if(state.cords.get(k) >= 0 ){
                                holdCords.add(state.cords.get(k));
                            }
                        }
                        state.cords.clear();
                        state.cords = holdCords;*/
                        // CHECK IF ITS OWN PIECE //
                        invalidate();
                    }
                    // Dumb AI Playing
                    else if (!state.getTurn()) {
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
                                    currID == R.drawable.promoted_silv_gen
                            ) {
                                state.cords.clear();
                            }
                        }
                        randIndex = 1;
                        while(randIndex%2 != 0){
                            randIndex = rand.nextInt(state.cords.size());
                        }
                        for(Piece p: state.pieces2){
                            if(pieceCol == p.getCol() && pieceRow == p.getRow() && p.pieceType.getID() == currID){
                                p.setCol(state.cords.get(randIndex));
                                p.setRow(state.cords.get(randIndex+1));
                            }
                        }
                        holdCords.clear();
                        for (int j = 0; j < state.pieces1.size()-1; j++) {
                            if(state.pieces1.get(j).getCol() == state.cords.get(randIndex) && (state.pieces2.get(j).getRow() == state.cords.get(randIndex+1))){
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
                            /**GAME END*/
                            System.exit(0);
                        }
                        state.setTurn(!state.getTurn());
                        invalidate();
                    }
                } // if lever == 0
                else if(lever == 1){
                    for(int i = 0; i < state.cords.size(); i+= 2){
                        if(state.cords.get(i) == boardCol && state.cords.get(i+1) == boardRow){
                            noMove = 1;
                            if(state.getTurn()){
                                for(Piece p : state.pieces1){
                                    if(p.pieceType.getID() == currID && p.getCol() == pieceCol && p.getRow() == pieceRow){
                                        p.setCol(boardCol);
                                        p.setRow(boardRow);
                                        // flip turn aswell
                                       // state.changeTurn();
                                        holdCords.clear();
                                        for(int j = 0; j < state.pieces2.size(); j++){
                                            if(state.pieces2.get(j).getCol() == boardCol && (state.pieces2.get(j).getRow() == boardRow)){
                                                state.pieces2.remove(j);
                                                //holdCords.add(j);
                                            }
                                        }
                                       /** Collections.sort(holdCords);
                                        Collections.reverse(holdCords);
                                        for(Integer l : holdCords){
                                            state.pieces2.remove(l);
                                        }*/

                                        lever = 0;
                                        state.cords.clear();
                                        if(game.checkMate(state.getTurn())){
                                            /**GAME END*/
                                            System.exit(0);
                                        }
                                        state.setTurn(!state.getTurn());
                                        invalidate();
                                    }
                                }
                            }
                            break;
                        }
                    } // for cords
                    if(noMove == 2){
                        state.cords.clear();
                        lever = 0;
                        invalidate();
                    }
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