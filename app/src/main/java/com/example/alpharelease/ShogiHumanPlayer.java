package com.example.alpharelease;

import android.graphics.Color;
import android.view.View;

import com.example.alpharelease.GameFramework.GameMainActivity;
import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.alpharelease.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.alpharelease.GameFramework.players.GameHumanPlayer;
import com.example.alpharelease.GameFramework.players.GamePlayer;
import com.example.alpharelease.GameFramework.utilities.Logger;

public class ShogiHumanPlayer extends GameHumanPlayer {

    private MainSurfaceView surfaceView;
    private int layoutId;

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
            // if the move was out of turn or otherwise illegal, flash the screen
            //surfaceView.flash(Color.RED, 50);
        }
        else if (!(info instanceof ShogiGameState))
            // if we do not have a TTTState, ignore
            return;
        else {
            //got an updated state, need to redraw (send the info to the surfaceView)
            surfaceView.setGameState((ShogiGameState)info);
            surfaceView.invalidate();
            Logger.log("ShogiHumanPlayer", "receiving");
        }
    }

    public ShogiHumanPlayer(String name, int activity_main) {
            super(name);
            this.layoutId = activity_main;
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(layoutId);
        myActivity = (GameMainActivity) activity;
        surfaceView = activity.findViewById(R.id.surfaceView);

        surfaceView.setLocalGame((LocalGame) game);
        surfaceView.setOnTouchListener(surfaceView);


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
        //myActivity.setTitle("Tic-Tac-Toe: "+allPlayerNames[0]+" vs. "+allPlayerNames[1]);
    }

    @Override
    public GameMainActivity getActivity() {
        return myActivity;
    }
}
