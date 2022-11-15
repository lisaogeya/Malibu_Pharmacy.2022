package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ChatsActivity extends AppCompatActivity {
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imageProf;
    private AppCompatImageButton imagechats;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        AppCompatImageButton imageHome = findViewById(R.id.imageHome);
        AppCompatImageButton imageProf = findViewById(R.id.imageProf);
        AppCompatImageButton imagechats = findViewById(R.id.imagechats);

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        imagechats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ChatsActivity.class);
                startActivity(intent);
            }
        });
    }
}