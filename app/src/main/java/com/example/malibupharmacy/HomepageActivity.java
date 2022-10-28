package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomepageActivity extends AppCompatActivity {
private AppCompatImageButton imageProf;
private AppCompatImageButton imageHome;
private AppCompatImageButton imageOCR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

     AppCompatImageButton imageProf = (AppCompatImageButton) findViewById(R.id.imageProf);
     AppCompatImageButton imageHome = (AppCompatImageButton) findViewById(R.id.imageHome);
     AppCompatImageButton imageOCR = (AppCompatImageButton)  findViewById(R.id.imageOCR);

     imageProf.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);
             startActivity(intent);
         }
     });
     imageHome.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(HomepageActivity.this, HomepageActivity.class);
             startActivity(intent);

         }
     });
     imageOCR.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(HomepageActivity.this, OCRActivity.class);
             startActivity(intent);
         }
     });
    }
}