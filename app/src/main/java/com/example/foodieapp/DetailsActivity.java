package com.example.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView title = findViewById(R.id.Title);
        TextView topic = findViewById(R.id.topic);
        TextView hv1 = findViewById(R.id.ho1);
        TextView hv2 = findViewById(R.id.ho2);
        TextView cv1 = findViewById(R.id.conV1);
        TextView cv2 = findViewById(R.id.conV2);
        TextView c1 = findViewById(R.id.CN1);
        TextView c2 = findViewById(R.id.CN2);
        TextView cl1 = findViewById(R.id.CoL1);
        TextView cl2 = findViewById(R.id.CoL2);
        TextView s1 = findViewById(R.id.ps1);
        TextView s2 = findViewById(R.id.ps2);
        ImageView img = findViewById(R.id.Img);
        //////////////////////////////////////////////////

        Intent intent = getIntent();
        String Title = intent.getStringExtra("TITLE");
        String Topic =  intent.getStringExtra("TOPIC");
        String h1 = intent.getStringExtra("HV1");
        String h2 = intent.getStringExtra("HV2");
        String cvv1 = intent.getStringExtra("CV1");
        String cvv2 = intent.getStringExtra("CV2");
        String concept1 = intent.getStringExtra("C1");
        String concept2 = intent.getStringExtra("C2");
        String codelab1 = intent.getStringExtra("CL1");
        String codelab2 = intent.getStringExtra("CL2");
        String ps1 = intent.getStringExtra("S1");
        String ps2 = intent.getStringExtra("S2");
        String Image = intent.getStringExtra("IMAGE");
        ///////////////////////////////////////////////////////

        title.setText(Title);
        topic.setText(Topic);
        hv1.setText(h1);
        hv2.setText(h2);
        cv1.setText(cvv1);
        cv2.setText(cvv2);
        c1.setText(concept1);
        c2.setText(concept2);
        cl1.setText(codelab1);
        cl2.setText(codelab2);
        s1.setText(ps1);
        s2.setText(ps2);
        Picasso.get().load(Image).into(img);
    }
}