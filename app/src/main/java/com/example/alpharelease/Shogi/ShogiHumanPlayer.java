package com.example.alpharelease.Shogi;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.alpharelease.GameFramework.GameMainActivity;
import com.example.alpharelease.GameFramework.LocalGame;
import com.example.alpharelease.GameFramework.infoMessage.GameInfo;
import com.example.alpharelease.GameFramework.infoMessage.GameOverInfo;
import com.example.alpharelease.GameFramework.infoMessage.IllegalMoveInfo;
import com.example.alpharelease.GameFramework.infoMessage.NotYourTurnInfo;
import com.example.alpharelease.GameFramework.players.GameHumanPlayer;
import com.example.alpharelease.GameFramework.utilities.Logger;
import com.example.alpharelease.R;
import com.example.alpharelease.Shogi.MainSurfaceView;
import com.example.alpharelease.Shogi.ShogiGameState;

public class ShogiHumanPlayer extends GameHumanPlayer implements View.OnClickListener {

    private MainSurfaceView surfaceView;
    private ShogiGameState copyState;

    private Button promo;
    private Button newGame;

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
            ShogiGameState state = (ShogiGameState) info;
            copyState = (ShogiGameState) info;
            surfaceView.setGameState((ShogiGameState)info);
            surfaceView.invalidate();
            Logger.log("ShogiHumanPlayer", "receiving");
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
        surfaceView.setOnTouchListener(surfaceView);

        promo = activity.findViewById(R.id.promoButton);
        newGame = activity.findViewById(R.id.newGameButton);

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
        //myActivity.setTitle("Tic-Tac-Toe: "+allPlayerNames[0]+" vs. "+allPlayerNames[1]);
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
}
