package com.spai.drake.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Indraga on 4/19/2016.
 */
public class RAlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        String nama;
        nama = intent.getExtras().getString("nama");
        Log.d("RUN", "RUN " + nama);
        Intent i = new Intent(context, RService.class);
        i.putExtra("nama", nama);
        context.startService(i);
    }

}
