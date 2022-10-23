package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
private MaterialButton customerbtn;
private MaterialButton employeebtn;
private MaterialButton adminbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton customerbtn = (MaterialButton) findViewById(R.id.cutsomerbtn);
        MaterialButton employeebtn = (MaterialButton) findViewById(R.id.employeebtn);
        MaterialButton adminbtn = (MaterialButton) findViewById(R.id.adminbtn);

        customerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
        employeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EmployeeActivity.class);
                startActivity(intent);

            }
        });
        adminbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SysActivity.class);
                startActivity(intent);

            }
        });
    }




    }
