package com.example.harsh.arttherapy;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class DrawActivity extends AppCompatActivity implements SensorEventListener {


    // Canvas will be updated only when phone is shaken 3 times //
    private SensorManager mSensorManager;
    private static final int FORCE_SENSOR_THRESHOLD = 50;
    private static final int TIME_SENSOR_THRESHOLD = 350;
    private static final int SUBSEQUENT_SHAKE_TIMEOUT = 750;
    private static final int SHAKE_INTERVAL_DURATION = 2000;
    private static final int SHAKE_ITERATOR = 3;


    private float LastSensorX = -1.0f, LastSensorY = -1.0f, LastSensorZ = -1.0f;
    private long LastSensorTime;
    private int ShakeIterator = 0;
    private long LastSensorShake;
    private long LastSensorForce;
    private float Accelerator;

    private TouchEventView customCanvas;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  TouchEventView tv = new TouchEventView(this);
        setContentView(R.layout.activity_draw);
       // addContentView(tv.parentLinearLayout, tv.params);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customCanvas = (TouchEventView)findViewById(R.id.custom);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
        LastSensorForce = System.currentTimeMillis();
        LastSensorTime = LastSensorForce;
        LastSensorShake = LastSensorForce;




    }

    public void clearCanvas(View v)
    {

        customCanvas.clearcanvas();

    }

    public void onResume()
    {
        super.onResume();
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }




    protected void onPause()
    {
        mSensorManager.unregisterListener(this);
        super.onPause();
       // finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_draw, menu);
        return true;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        long now = System.currentTimeMillis();

        if ((now - LastSensorForce) > SUBSEQUENT_SHAKE_TIMEOUT) {
            ShakeIterator = 0;
        }

        if ((now - LastSensorTime) > TIME_SENSOR_THRESHOLD) {
            long diff = now - LastSensorTime;

            Accelerator = Math.abs(event.values[0] + event.values[1] + event.values[2] - LastSensorX - LastSensorY - LastSensorZ) / diff * 10000;
            if (Accelerator > FORCE_SENSOR_THRESHOLD) {

                if ((++ShakeIterator >= SHAKE_ITERATOR) && ((now - LastSensorShake) > SHAKE_INTERVAL_DURATION)) {
                    Vibrator v = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
                    v.vibrate(100);
                    clearCanvas(customCanvas);

                    Intent intent = new Intent(this,Eraser.class);
                    startService(intent);


                    LastSensorShake = now;
                    ShakeIterator = 0;
                }
                LastSensorForce = now;
            }
            else
            {


            }

            LastSensorTime = now;
            LastSensorX = event.values[0];
            LastSensorY = event.values[1];
            LastSensorZ = event.values[2];
        }

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
