package com.spai.drake.reminder;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        tv1 = (TextView) findViewById(R.id.tv1);
        Intent i = getIntent();
        Log.d("tagg",i.getExtras().getString("nama"));
        if(i.getExtras().getString("nama").equals("Duhur")){
            tv1.setText("Shalat duhur adalah shalat wajib 4 rakaat");
        }else if(i.getExtras().getString("nama").equals("Dhuha")){
            tv1.setText("Shalat dhuha adalah shalat sunat 2 sampai 8 rakaat");
        }
    }
}
