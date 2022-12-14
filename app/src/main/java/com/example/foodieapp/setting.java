package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.os.Bundle;

public class setting extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private boolean shady = false, nature = false, mild = false;
    private boolean dark = false, light = false, mix = false;
    private boolean blue = false, grey = false;
    private boolean resetFlag = false;
    SharedPreferences sharedPreferences1;
    private EditText welmsg ;
    private Button save_button ;
    private String msg = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RadioGroup r1 = findViewById(R.id.RGroup1);
        RadioGroup r2 = findViewById(R.id.RGroup2);
        RadioGroup r3 = findViewById(R.id.RGroup3);
        Button reset = findViewById(R.id.reset);

        r1.setOnCheckedChangeListener(this);
        r2.setOnCheckedChangeListener(this);
        r3.setOnCheckedChangeListener(this);

        welmsg = findViewById(R.id.msg);
        save_button = findViewById(R.id.save);
        save_button.setOnClickListener(this);
        reset.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        int id_check = radioGroup.getId();
        String x = Integer.toString(id_check);
        String y = Integer.toString(R.id.RGroup1);

        if(id_check == R.id.RGroup1){

            RadioButton radio1 = (RadioButton) radioGroup.findViewById(i);
            if (radio1 != null) {
                int check = radio1.getId();
                switch (check) {

                    case R.id.shady:
                        shady = true;
                        Log.d("S", "soccer");
                        nature = false;
                        mild = false;
                        break;

                    case R.id.nature:
                        nature = true;
                        Log.d("S", "baseball");
                        shady = false;
                        mild = false;
                        break;

                    case R.id.mild:
                        mild = true;
                        Log.d("S", "football");
                        shady = false;
                        nature = false;
                        break;
                }
            }
            else{
                Log.d("s","error");
            }
        }

        else if(id_check == R.id.RGroup2){

            RadioButton radio1 = (RadioButton) radioGroup.findViewById(i);
            if (radio1 != null) {
                int check = radio1.getId();
                switch (check) {

                    case R.id.dark:
                        dark = true;
                        Log.d("S", "medal");
                        light= false;
                        mild = false;
                        resetFlag = false;
                        break;

                    case R.id.light:
                        light = true;
                        Log.d("S", "cup");
                        dark = false;
                        mild = false;
                        resetFlag = false;
                        break;

                    case R.id.mild:
                        mild = true;
                        Log.d("S", "thumbs up");
                        dark = false;
                        light = false;
                        resetFlag = false;
                        break;
                }
            }
            else{
                Log.d("s","error");
            }
        }
        else{
            RadioButton radio1 = (RadioButton) radioGroup.findViewById(i);
            if (radio1 != null) {
                int check = radio1.getId();
                switch (check) {

                    case R.id.blue:

                        blue = true;
                        grey = false;
                        resetFlag = false;

                        break;

                    case R.id.grey:
                        blue = false;
                        grey = true;
                        resetFlag = false;
                        break;
                }
            }
            else{
                Log.d("s","error");
            }
        }



    }// end of radio group


    @Override
    public void onClick(View view) {

        int id = view.getId();

        if(id == R.id.reset){

            resetFlag = true;
            shady = false; nature = false; mild = false;
            dark = false; light = false; mix = false;
            blue = false; grey = false;


           welmsg.setText("Welcome to Android Studio");
        }
        else{

            if(shady){
                Log.d("TEST", "s=true");
                sharedPreferences1 = getSharedPreferences("P1", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("CHAPTER", "shady");
                editor.apply();
            }
            else if(nature){
                sharedPreferences1 = getSharedPreferences("P1", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("CHAPTER", "nature");
                editor.apply();
            }
            else if(mild){
                sharedPreferences1 = getSharedPreferences("P1", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("CHAPTER", "mild");
                editor.apply();
            }
            else if(resetFlag){
                sharedPreferences1 = getSharedPreferences("P1", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("CHAPTER", "reset");
                editor.apply();
            }
            ////////////////////////////////

            if(dark){
                Log.d("TEST", "s=true");
                sharedPreferences1 = getSharedPreferences("P2", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("HOM", "dark");
                editor.apply();
            }
            else if(light){
                sharedPreferences1 = getSharedPreferences("P2", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("HOM", "light");
                editor.apply();
            }
            else if(mix){
                sharedPreferences1 = getSharedPreferences("P2", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("HOM", "mix");
                editor.apply();
            }
            else if(resetFlag){
                sharedPreferences1 = getSharedPreferences("P2", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("HOM", "reset");
                editor.apply();
            }
            /////////////////////////////////////

            if(blue){
                sharedPreferences1 = getSharedPreferences("P3", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("QUIZ", "blue");
                editor.apply();
            }
            else if(grey){
                sharedPreferences1 = getSharedPreferences("P3", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("QUIZ", "grey");
                editor.apply();
            }
            else if(resetFlag){
                sharedPreferences1 = getSharedPreferences("P3", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("QUIZ", "reset");
                editor.apply();
            }
            ///////////////////////////////////

            if(welmsg.getText().toString().length() > 0){

                sharedPreferences1 = getSharedPreferences("P4", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("MSG", welmsg.getText().toString());
                editor.apply();
            }
            else{
                sharedPreferences1 = getSharedPreferences("P4", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences1.edit();
                editor.putString("MSG", "Welcome to Android Studio");
                editor.apply();
            }






        }



    }
}