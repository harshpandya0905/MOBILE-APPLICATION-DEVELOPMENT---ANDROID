package com.example.harsh.arttherapy;

/**
 * Created by harsh on 2/24/2016.
 */


// cause we need to create another intent service
import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;

public class Eraser extends IntentService {

    MediaPlayer mp;
    private boolean flag = false;
    public Eraser()
    {
        super("HelloIntentService");
    }

    protected void onHandleIntent(Intent intent) {

        mp = MediaPlayer.create(Eraser.this, R.raw.eraser);
        mp.start();
        while(mp.isPlaying())
        {
            System.out.println();
        }
        mp.reset();
        mp.release();

    }

}

