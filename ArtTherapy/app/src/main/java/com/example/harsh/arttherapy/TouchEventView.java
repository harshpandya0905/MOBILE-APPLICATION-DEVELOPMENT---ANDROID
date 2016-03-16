package com.example.harsh.arttherapy;

/**
 * Created by harsh on 2/23/2016.
 */

import android.app.ActionBar;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.SizeF;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class TouchEventView extends View
{


    public Paint paint = new Paint();
    public Path path = new Path();
    public Paint circlePaint = new Paint();
    public Path circlePath = new Path();


    // Yeh kya bhakchodi kr raha hai no idea

    public TouchEventView(Context ctx) {
        super(ctx);
        init(null,0);
    }

    public TouchEventView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        init(attrs,0);
    }

    public TouchEventView(Context ctx, AttributeSet attrs, int defStyleAttr) {
        super(ctx, attrs, defStyleAttr);
        init(attrs,defStyleAttr);
    }

    public void init(AttributeSet attributeSet, int defStyleAttr)

    {

        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10f);


        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.BLACK);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeJoin(Paint.Join.ROUND);
        circlePaint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawPath(path,paint);
        canvas.drawPath(circlePath,circlePaint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float xPos = event.getX();
        float yPos = event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(xPos,yPos);
                return true;


            case MotionEvent.ACTION_MOVE:
                path.lineTo(xPos, yPos);
                circlePath.reset();

                circlePath.addCircle(xPos,yPos,20,Path.Direction.CW);
                break;

               // return true;

            case MotionEvent.ACTION_UP:

                circlePath.reset();
                break; // when finger are taken away from screen


            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void clearcanvas()

    {

        path.reset();
        invalidate();

    }


}
