package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MedicalHistoryActivity extends AppCompatActivity {
    TextView Allergies,conditions,tests,moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalhistory);

        TextView Allergies= findViewById(R.id.Allergies);
        TextView conditions= findViewById(R.id.conditions);
        TextView tests = findViewById(R.id.tests);
        TextView moreInfo = findViewById(R.id.moreInfo);

        Allergies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalHistoryActivity.this, AllergiesActivity.class);
                startActivity(intent);
            }
        });
        conditions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalHistoryActivity.this, ConditionsActivity.class);
                startActivity(intent);
            }
        });
        tests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalHistoryActivity.this, TestsActivity.class);
                startActivity(intent);
            }
        });
        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalHistoryActivity.this, MoreInfoActivity.class);
                startActivity(intent);
            }
        });

    }
}