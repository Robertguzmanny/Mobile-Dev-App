package com.example.foodieapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.widget.Toast;

public class Broadreciver extends BroadcastReceiver {

    Broadreciver(){}
    @Override
    public void onReceive(Context context, Intent intent) {

        if(ConnectivityManager.CONNECTIVITY_ACTION.equals((intent.getAction()))){
            boolean flag = intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );

            if(flag == true){
                Toast.makeText(context, "No Internet", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
            }
        }//
        if(Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())){
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float)scale;
            String result = Float.toString(batteryPct);
            Toast.makeText(context, "Current Battery Health: " + result + "%", Toast.LENGTH_LONG).show();
        }

    }
}
