package com.example.foodieapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class LoginActivity extends AppCompatActivity {
    private Button button;
    private Intent home;
    private DataBase dataBase;
    TextView fp;
    EditText inputEmailL, inputPasswordL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //setContentView(R.layout.homepage);


        dataBase = new DataBase(this);


        fp = findViewById(R.id.ForgotPassword);

        TextView btnRegister = findViewById(R.id.textViewSignUp);
        Button guest = findViewById(R.id.guest);
        inputEmailL = findViewById(R.id.inputEmailL);
        inputPasswordL = findViewById(R.id.inputPasswordL);
        button = findViewById(R.id.btnlogin);
        home = new Intent(this, SlideActivity.class);

        ///////////////////////////////
        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("USER", "guest");
                editor.apply();
                startActivity(home);

            }
        });
        ///////////////////////////////
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUp.class));


            }
        });

        ///////////////////////////////
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openHomePage();
//                dataBase.resetPassword("", "abcde");

            }
        });
        //////////////////////////////////
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, reset.class));

            }
        });
    } // end oncreate

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences share = getSharedPreferences("P4", MODE_PRIVATE);
        String result = share.getString("MSG",null);

        if(result != null){

            Toast toast = Toast.makeText(getBaseContext(), result, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0 );
            toast.show();

        }
        else{
            Log.d("MAIN", "error in setting msg");
        }
    }



    public void openHomePage(){
        String user = inputEmailL.getText().toString();
        String password = inputPasswordL.getText().toString();
//        Intent intent = new Intent(this, HomePage.class);
        SharedPreferences sharedPreferences = getSharedPreferences("LOGIN", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
///////////////////////////////
        boolean flag = dataBase.checkUsername_Password(user, password);

        if (flag){
            editor.putString("USER", "admin");
            editor.apply();
            startActivity(home);

        }
        else if(user.equals("") || password.equals("")){

            Toast.makeText(getBaseContext(), "Enter user and password", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getBaseContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
        }
    }


}//end class