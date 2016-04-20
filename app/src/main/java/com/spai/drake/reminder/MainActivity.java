package com.spai.drake.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent;
    private CheckBox cb1,cb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox) findViewById(R.id.cb1);
        cb2 = (CheckBox) findViewById(R.id.cb2);

        findViewById(R.id.stopAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });

        findViewById(R.id.stopAlarmAt10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAt10();
            }
        });
    }

    public void cancel(){
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(MainActivity.this, RAlarmReceiver.class);
        alarmIntent.putExtra("nama","Duhur");
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, alarmIntent, 0);
        manager.cancel(pendingIntent);
        alarmIntent.putExtra("nama", "Dhuha");
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        manager.cancel(pendingIntent);
    }

    public void startAt10() {
        cancel();
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        if(cb2.isChecked()){
            Intent alarmIntent = new Intent(MainActivity.this, RAlarmReceiver.class);
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 9);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            /* Repeating on every 20 minutes interval */
            /* Retrieve a PendingIntent that will perform a broadcast */
            alarmIntent.putExtra("nama", "Dhuha");
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);
        }
        /* Set the alarm to start at 10:30 AM */
        if(cb1.isChecked()){
            Intent alarmIntent = new Intent(MainActivity.this, RAlarmReceiver.class);
            /* Retrieve a PendingIntent that will perform a broadcast */
            alarmIntent.putExtra("nama","Duhur");
            pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, alarmIntent, 0);
            /* Set the alarm to start at 10:30 AM */
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 11);
            calendar.set(Calendar.MINUTE, 30);
            calendar.set(Calendar.SECOND, 0);
            /* Repeating on every 20 minutes interval */
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000*60*60*24, pendingIntent);
        }
    }
}