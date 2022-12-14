package com.example.foodieapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private EditText uName, email, password;
    private Button button;
    private TextView link;
    private DataBase dbr;
    private DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        uName = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        button = (Button) findViewById(R.id.btnRegister);
        button.setOnClickListener(this);
        dataBase = new DataBase(this);

        link = findViewById(R.id.ah);
        link.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int butonId = v.getId();


        if(butonId == R.id.btnRegister){
            if(uName.getText().toString().length() == 0 || password.getText().toString().length() == 0){
                Toast.makeText(getBaseContext(), "Enter Name and Password", Toast.LENGTH_SHORT).show();
            }
            else{
                boolean existUser = dataBase.checkUser(uName.getText().toString());

                if(existUser){
                    Toast.makeText(getBaseContext(), "Name already exist, try new name.", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean flag = dataBase.insertData(uName.getText().toString(), email.getText().toString(),password.getText().toString());
                    if(flag){
                        Toast.makeText(getBaseContext(), "Sign up completed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, LoginActivity.class));
                    }
                    else{
                        Toast.makeText(getBaseContext(), "Failed to add data", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            }

        else{ // this one for forgot password link

            startActivity(new Intent(this, LoginActivity.class));
        }




    }// end of onclick
}