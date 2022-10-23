package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomepageActivity extends AppCompatActivity {
private AppCompatImageButton imageProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

     AppCompatImageButton imageProfile = (AppCompatImageButton) findViewById(R.id.imageProfile);

     imageProfile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);
             startActivity(intent);
         }
     });
    }
}