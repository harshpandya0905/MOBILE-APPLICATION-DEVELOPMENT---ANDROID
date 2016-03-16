package com.example.harsh.arttherapy;

/**
 * Created by harsh on 2/24/2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;

public class BroadcastReciever extends BroadcastReceiver {



    private NotificationManager mNotificationManager;
    private int SIMPLE_NOTFICATION_ID;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_mangoo)
                        .setContentTitle("Art Therapy by Harsh ")
                        .setContentText("Its a Paint App")
                        .setAutoCancel(true)
                        .setContentInfo("Click here to launch the Art App ")
                        .setColor(25500);

        Vibrator vib= (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vib.vibrate(50);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);

        Intent resultIntent = new Intent(context, DrawActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(DrawActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setFullScreenIntent(resultPendingIntent, false);

        mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(0,mBuilder.build());


    }


}