package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

public class HomepageActivity extends AppCompatActivity {
    private AppCompatImageButton imageProf;
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imagechats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        imageProf = findViewById(R.id.imageProf);
        imageHome = findViewById(R.id.imageHome);
        imagechats = findViewById(R.id.imagechats);

        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imagechats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomepageActivity.this, ChatsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}