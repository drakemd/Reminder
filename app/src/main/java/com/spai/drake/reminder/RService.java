package com.spai.drake.reminder;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Indraga on 4/19/2016.
 */
public class RService extends IntentService{

    public RService() {
        super("a");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String nama;
        nama = intent.getExtras().getString("nama");
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("Waktunya " + nama);
        mBuilder.setSmallIcon(R.drawable.htc__04);
        mBuilder.setContentText("Klik untuk melihat detail");
        Intent resultIntent = new Intent(this, InfoActivity.class);
        resultIntent.putExtra("nama", nama);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(InfoActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}
