package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reset extends AppCompatActivity implements View.OnClickListener{
    private EditText user, p1, p2;
    private Button resetButton;
    private DataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        user = findViewById(R.id.username);
        p1 = findViewById(R.id.p1);
        p2 = findViewById(R.id.p2);
        resetButton = findViewById(R.id.rb);
        resetButton.setOnClickListener(this);
        DB = new DataBase(this);

    }

    @Override
    public void onClick(View v) {
        if(user.getText().toString().length() == 0 || p1.getText().toString().length() == 0 || p2.getText().toString().length() == 0){
            Toast.makeText(getBaseContext(), "Please enter user name and password", Toast.LENGTH_SHORT).show();
        }
        else if(!(p1.getText().toString().equals(p2.getText().toString()))){
            Toast.makeText(getBaseContext(), "Password does not match!", Toast.LENGTH_SHORT).show();
        }
        else{
            boolean result = DB.checkUser(user.getText().toString());
            if(result){
                boolean fl = DB.resetPassword(user.getText().toString(), p1.getText().toString());
                if(fl){
                    Toast.makeText(getBaseContext(), "Passwrod reset success", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getBaseContext(), "Passwrod reset fail", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getBaseContext(), "User name does not exist", Toast.LENGTH_SHORT).show();
            }

        }





    }
}