package com.example.alpharelease.Shogi;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.alpharelease.GameFramework.GameMainActivity;
import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.infoMessage.GameOverInfo;
import com.example.alpharelease.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.alpharelease.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.alpharelease.GameFramework.players.GameHumanPlayer;
import com.example.alpharelease.GameFramework.utilities.Logger;
import com.example.alpharelease.R;
import com.example.alpharelease.Shogi.Actions.MovePieceAction;
import com.example.alpharelease.Shogi.Actions.SelectPieceAction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ShogiHumanPlayer extends GameHumanPlayer implements View.OnClickListener, View.OnTouchListener {

    private MainSurfaceView surfaceView;
    private ShogiGameState state;
    private Board board;

    private Button promo;
    private Button newGame;
    private EditText whichPlayer;
    private EditText whichPiece;
    private EditText results;

    boolean pieceIsSelected = false;
    private Tile chosenTile = null, fromThisTile = null, goToTile = null;
    private ArrayList<Tile> possibleTiles = new ArrayList<>();

    /**
     * Callback method, called when player gets a message
     *
     * @param info
     * 		the message
     */
    @Override
    public void receiveInfo(GameInfo info) {

        if (surfaceView == null) return;

        if (info instanceof IllegalMoveInfo || info instanceof NotYourTurnInfo) {
            // if the move was out of turn or otherwise illegal
            Log.d("HUMAN_INFO_NOT_GS", "It's not your turn, idiot");
        }
        else if (!(info instanceof ShogiGameState))
            // if we do not have a ShogiGameState, ignore
            return;
        else {
            //got an updated state, need to redraw (send the info to the surfaceView)
            state = (ShogiGameState) info;
            surfaceView.setGameState((ShogiGameState)info);
            Logger.log("ShogiHumanPlayer", "receiving");
            surfaceView.invalidate();
        }
    }

    public ShogiHumanPlayer(String name) {
            super(name);
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(R.layout.activity_main);
        myActivity = (GameMainActivity) activity;
        surfaceView = activity.findViewById(R.id.surfaceView);

        surfaceView.setLocalGame((LocalGame) game);
        surfaceView.setOnTouchListener(this);

        promo = activity.findViewById(R.id.promoButton);
        newGame = activity.findViewById(R.id.newGameButton);
        whichPlayer = activity.findViewById(R.id.turnWhoTextView);
        whichPiece = activity.findViewById(R.id.pieceTextView);
        results = activity.findViewById(R.id.endStatus);

        promo.setOnClickListener(this);
        newGame.setOnClickListener(this);
    }

    /**
     * returns the GUI's top view
     *
     * @return
     * 		the GUI's top view
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * perform any initialization that needs to be done after the player
     * knows what their game-position and opponents' names are.
     */
    protected void initAfterReady() {
        myActivity.setTitle("Battle of Wits!: " + allPlayerNames[0] + " vs. " + allPlayerNames[1]);
    }

    @Override
    public GameMainActivity getActivity() {
        return myActivity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newGameButton:
                Log.i("HUMAN_SURRENDER", "HAHAHAHAHA loser");
                this.sendInfo(new GameOverInfo("You kinda suck at this game. "));
                break;
        }
    }


    @Override
    public boolean onTouch(View view, MotionEvent e) {

        state = surfaceView.getGameState();

        if (state.getWhoseTurn() == this.playerNum) { //Players turn

            if (e.getActionMasked() == MotionEvent.ACTION_DOWN) {
                float x = e.getX();
                float y = e.getY();

                board = state.getBoard();

                if (!board.onBoard(x, y)) { //If they didn't touch the board pretend it didn't happen
                    return false;

                } else { //All good to go, boss!
                    chosenTile = board.getTileByCord(x, y);
                    while (chosenTile == null) {
                        x--;
                        y--;
                        chosenTile = board.getTileByCord(x, y);
                    } //While it's null, try adjusting the coordinates slightly due to the tiny gaps in the board

                    if (!pieceIsSelected) { //Nothing is selected
                        if (!chosenTile.isOccupied() || this.playerNum != chosenTile.getPiece().pieceType.getPlayer()) {
                            //Chosen piece either isn't yours or is empty
                            return false;
                        }
                        fromThisTile = chosenTile;
                        game.sendAction(new SelectPieceAction(this, fromThisTile.getTileIndex()));
                        pieceIsSelected = true;
                        board.checkMoves(fromThisTile);

                        whichPiece.setText("" + fromThisTile.getPiece().pieceType);

                        Log.d("HUMAN_SELECTED_PIECE", "Wheeee you chose " + fromThisTile.getTileIndex());

                        this.sendInfo(state);
                        surfaceView.invalidate();
                        return true; //You selected a new piece! Congrats!

                    } else if (chosenTile.isOccupied() && this.playerNum == chosenTile.getPiece().pieceType.getPlayer()) {
                        //You already selected a piece, but chose a different one
                        fromThisTile.getPiece().setSelected(false); //Set old piece to unselected
                        board.impossAllTiles(); //Get rid of previous possibilities

                        fromThisTile = chosenTile;
                        game.sendAction(new SelectPieceAction(this, fromThisTile.getTileIndex()));
                        pieceIsSelected = true;
                        board.checkMoves(fromThisTile);

                        whichPiece.setText("" + fromThisTile.getPiece().pieceType);

                        Log.d("HUMAN_SELECTED_PIECE", "Wheeee you chose (again) " + goToTile.getTileIndex());

                        this.sendInfo(state);

                        surfaceView.invalidate();

                        return true; //You selected a new piece! Congrats!

                    } else { //Unoccupied or is occupied by an enemy. Sally forth!

                        goToTile = chosenTile;
                        possibleTiles.addAll(board.getPossibleTiles());

                        if (!possibleTiles.contains(goToTile)) { //If it's not possible
                            return false;
                        }

                        game.sendAction(new MovePieceAction(this, goToTile.getTileIndex()));
                        pieceIsSelected = false;
                        possibleTiles.clear();

                        if (state.getWhoseTurn() == 0) {
                            whichPlayer.setText(allPlayerNames[1] + "'s Turn");
                        } else {
                            whichPlayer.setText(allPlayerNames[0] + "'s Turn");
                        }

                        Log.d("HUMAN_MOVED_PLACE", "Wheeee you placed it at " + goToTile.getTileIndex());
                        this.sendInfo(state);
                        surfaceView.invalidate();
                        return true;
                    }
                }
            }
        }
        surfaceView.invalidate();
        return false;
    }
}
