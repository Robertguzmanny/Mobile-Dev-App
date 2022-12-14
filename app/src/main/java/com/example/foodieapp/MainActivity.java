package com.example.foodieapp;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";
    Broadreciver Internet_connection;
    RecyclerView mRecyclerView;
    ArrayList<Items> mealItemArrayList;
    ItemAdaptor adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.mealitemRV);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        load();
        adapter = new ItemAdaptor(MainActivity.this, mealItemArrayList);
        mRecyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fab);

        SharedPreferences sh = getSharedPreferences("LOGIN", MODE_PRIVATE);
        String user = sh.getString("USER", null);

        if(user.equals("admin")){       // Allow admin to add chapters
            fab.show();
        }
        else{
            fab.hide();
        }




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        Internet_connection = new Broadreciver();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(Internet_connection, intentFilter);

    }// end of oncreate()

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "--> onStart");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "--> onResume");

        SharedPreferences share = getSharedPreferences("P1", MODE_PRIVATE);
        String result = share.getString("CHAPTER",null);

        if(result != null){
            RelativeLayout layout = findViewById(R.id.a);
            Resources res = getResources();
            if(result.equals("shady")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.shady, null);
                layout.setBackground(drawable);
            }
            else if(result.equals("nature")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.nature, null);
                layout.setBackground(drawable);
            }
            else if(result.equals("mild")){
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.mild, null);
                layout.setBackground(drawable);
            }
            else{
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.green, null);
                layout.setBackground(drawable);
            }
        }
        else{
            Log.d("MAIN", "error in changing picture");
        }


}

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "--> onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "--> onStop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "--> onDestroy");
        if(Internet_connection != null){
            unregisterReceiver(Internet_connection);}
//        unregisterReceiver(dynReciever);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "--> onRestart");
    }



    private void load() {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MEALS_OBJ", null);
        Type type = new TypeToken<ArrayList<Items>>() {}.getType();
        mealItemArrayList = gson.fromJson(json, type);
        if (mealItemArrayList == null) {
            mealItemArrayList = new ArrayList<>();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.share:
                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType(mimeType)
                        .setChooserTitle("Pick and app!")
                        .setText("") ///////
                        .startChooser();

                return true;

            case R.id.info:
                AlertDialog.Builder build = new AlertDialog.Builder(MainActivity.this, R.style.alert);
                build .setTitle("Group Final Project: ");
                String message = "Members: \n\n" +
                                 "01. Mohamed Irsath Abdul Azeez \n" +
                                 "02. Robert Guzman\n" +
                                 "03. Miguel Liranzo\n" +
                                 "04. Johnny Maldonado";
                build .setMessage(message);
                build .setCancelable(false);
                build .setPositiveButton("Cancel", (DialogInterface.OnClickListener) (dialog, which)->{
                    dialog.cancel();
                });
                AlertDialog infoDialog = build.create();
                infoDialog.show();
                return true;

            case R.id.quiz:

                Intent inte = new Intent(this, quiz.class);
                startActivity(inte);
                return true;

            case R.id.setting:

                Intent inten = new Intent(this, setting.class);
                startActivity(inten);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}