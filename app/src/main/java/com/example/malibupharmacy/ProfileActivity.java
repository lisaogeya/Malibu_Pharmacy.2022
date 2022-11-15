package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    private AppCompatImageButton imageProf;
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imagechats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatImageButton imageProf = (AppCompatImageButton) findViewById(R.id.imageProf);
        AppCompatImageButton imageHome = (AppCompatImageButton) findViewById(R.id.imageHome);
        AppCompatImageButton imagechats = (AppCompatImageButton)  findViewById(R.id.imagechats);
        AppCompatImageView   ocr= (AppCompatImageView) findViewById(R.id.ocr);
        AppCompatImageView   medHistory=(AppCompatImageView) findViewById(R.id.medHistory);
        AppCompatImageView   insurance=(AppCompatImageView) findViewById(R.id.insurance);
        AppCompatImageView   delivery=(AppCompatImageView) findViewById(R.id.delivery);
        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomepageActivity.class);
                startActivity(intent);

            }
        });
        imagechats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatsActivity.class);
                startActivity(intent);
            }
        });
        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, OCRActivity.class);
                startActivity(intent);
            }
        });
        medHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MedicalHistoryActivity.class);
                startActivity(intent);
            }
        });
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, InsuranceDetailsActivity.class);
                startActivity(intent);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DeliveryDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}