package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {

    private AppCompatImageButton imageProf;
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imageOCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatImageButton imageProf = (AppCompatImageButton) findViewById(R.id.imageProf);
        AppCompatImageButton imageHome = (AppCompatImageButton) findViewById(R.id.imageHome);
        AppCompatImageButton imageOCR = (AppCompatImageButton)  findViewById(R.id.imageOCR);
        AppCompatImageView   chats= (AppCompatImageView) findViewById(R.id.chats);
        AppCompatImageView   delivery=(AppCompatImageView) findViewById(R.id.delivery);
        AppCompatImageView   insurance=(AppCompatImageView) findViewById(R.id.insurance);
        AppCompatImageView   person=(AppCompatImageView) findViewById(R.id.person);
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
        imageOCR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, OCRActivity.class);
                startActivity(intent);
            }
        });
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatsActivity.class);
                startActivity(intent);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DeliveryActivity.class);
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
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}