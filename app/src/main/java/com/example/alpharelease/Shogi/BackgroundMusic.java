package com.example.alpharelease.Shogi;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.example.alpharelease.R;

/**
 *
 * @author Kathryn Weidman
 *
 * This plays music in the game, but I couldn't get it to change which song it was playing
 *      or stop the music before we had to turn in tablets
 *
 * @version 12/13/22
 *
 * */

public class BackgroundMusic extends Service {
    MediaPlayer mediaPlayer;
    private int songID = R.raw.elise;
    private int clicked = 0;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, songID);
        mediaPlayer.setLooping(true); // Set looping
        mediaPlayer.setVolume(100, 100);
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        switch (clicked % 3) {
            case 0:
                songID = R.raw.elise;
                break;
            case 1:
                songID = R.raw.godot;
                break;
            case 2:
                songID = R.raw.jake;
                break;
        }

        mediaPlayer.start();
        Toast.makeText(getApplicationContext(), "Playing a Song in BG",    Toast.LENGTH_SHORT).show();
        return startId;
    }

    public void onStart(Intent intent, int startId) {
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    @Override
    public void onLowMemory() {
    }
}
