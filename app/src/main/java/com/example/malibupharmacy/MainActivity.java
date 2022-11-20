package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private MaterialButton customerbtn;
    private MaterialButton employeebtn;
    private MaterialButton adminbtn;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton customerbtn = (MaterialButton) findViewById(R.id.cutsomerbtn);
        MaterialButton employeebtn = (MaterialButton) findViewById(R.id.employeebtn);
        MaterialButton adminbtn = (MaterialButton) findViewById(R.id.adminbtn);

        firebaseAuth = FirebaseAuth.getInstance();

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

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null ){
            Intent intent = new Intent(MainActivity.this, HomepageActivity.class);
            startActivity(intent);
        }
    }
}
