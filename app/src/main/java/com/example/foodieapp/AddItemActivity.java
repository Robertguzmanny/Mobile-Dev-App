package com.example.foodieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.UUID;

public class AddItemActivity extends AppCompatActivity {

    MainActivity mainActivity;
    ArrayList<Items> mealItemArrayList;
    private EditText addtitle, adddescription;
    private String ar1[] = new String[2],ar2[] = new String[2],ar3[] = new String[2],ar4[] = new String[2],ar5[] = new String[2];
    private TextView immsg;
    private EditText hv1, hv2, cv1, cv2, c1, c2, cl1, cl2, s1 , s2;
    String url;
    private Uri ImageUri;
    FirebaseFirestore picture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        loadData();
        addtitle = findViewById(R.id.addtitle);
        adddescription = findViewById(R.id.adddescription);
        hv1 = findViewById(R.id.hv1);
        hv2 = findViewById(R.id.hv2);
        cv1 = findViewById(R.id.cv1);
        cv2 = findViewById(R.id.cv2);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        cl1 = findViewById(R.id.cl1);
        cl2 = findViewById(R.id.cl2);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);

        immsg = findViewById(R.id.upload);
        Button choosebtn = findViewById(R.id.choosebtn);
        Button addbtn = findViewById(R.id.addbtn);

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(AddItemActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddItemActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            100);
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(
                        Intent.createChooser(
                                intent,
                                "Select Image from here..."),
                        1);
            }
        });

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String title = addtitle.getText().toString();
                final String description = adddescription.getText().toString();

                ar1[0] = hv1.getText().toString();
                ar1[1] = hv2.getText().toString();
                ar2[0] = cv1.getText().toString();
                ar2[1] = cv2.getText().toString();
                ar3[0] = c1.getText().toString();
                ar3[1] = c2.getText().toString();
                ar4[0] = cl1.getText().toString();
                ar4[1] = cl2.getText().toString();
                ar5[0] = s1.getText().toString();
                ar5[1] = s2.getText().toString();




                final String image = ImageUri.getPath();

                if (title.isEmpty()) {
                    addtitle.setText("Please add title");

                }
                if (description.isEmpty()) {
                    adddescription.setText("Please add description");

                }
//                if (ingredients.isEmpty()) {
//                    addingredients.setText("please add ingredients");
//
//                }
//                if (calories.isEmpty()) {
//                    addcalories.setText("please add calories");
//
//                }
//                if (recipe.isEmpty()) {
//                    addrecipe.setText("please add recipe");
//
//                }

                if (ImageUri != null) {
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images/" + UUID.randomUUID().toString());
                    storageReference.putFile(ImageUri).continueWithTask(new Continuation() {
                        @Override
                        public Object then(@NonNull Task task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return storageReference.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            if (task.isSuccessful()) {
                                Uri uri = task.getResult();
                                url = uri.toString();
                                Items mealItem = new Items(url, title, description, ar1, ar2, ar3, ar4, ar5);
                                mealItemArrayList.add(mealItem);
                                saveData();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {

                            }
                        }
                    });
                }
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED PREFERENCE", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("MEALS_OBJ", null);
        Type type = new TypeToken<ArrayList<Items>>() {}.getType();
        mealItemArrayList = gson.fromJson(json, type);
        if (mealItemArrayList == null) {
            mealItemArrayList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED PREFERENCE", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mealItemArrayList);
        editor.putString("MEALS_OBJ", json);
        editor.apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK
                && data != null && data.getData() != null) {

            immsg.setText("Image uploaded");
            ImageUri = data.getData();
        }
    }

}
