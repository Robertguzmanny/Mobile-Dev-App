package com.example.foodieapp;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    private final Broadreciver BatteryInfo = new Broadreciver();
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        Button button = (Button)findViewById(R.id.button);

        registerReceiver(BatteryInfo, intentFilter);

        button.setOnClickListener(this);


    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences share = getSharedPreferences("P2", MODE_PRIVATE);
        String result = share.getString("HOM",null);

        if(result != null){
            ConstraintLayout layout = findViewById(R.id.mRecyclerView);
            Resources res = getResources();
            if(result.equals("dark")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.dark, null);
                layout.setBackground(drawable);
            }
            else if(result.equals("light")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.light, null);
                layout.setBackground(drawable);
            }
            else if(result.equals("mix")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.mix, null);
                layout.setBackground(drawable);
            }
            else{
                Drawable drawable = ResourcesCompat.getDrawable(res, R.color.white, null);
                layout.setBackground(drawable);
            }
        }
        else{
            Log.d("MAIN", "error in changing picture");
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(BatteryInfo);


    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

}
