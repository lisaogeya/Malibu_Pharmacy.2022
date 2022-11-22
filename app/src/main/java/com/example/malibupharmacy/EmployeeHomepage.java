package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeeHomepage extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    MaterialButton btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_homepage);
        MaterialButton btnLogout= (MaterialButton) findViewById(R.id.btnlogout);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            btnLogout.setVisibility(View.VISIBLE);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(EmployeeHomepage.this, MainActivity.class));
                }
            });
        }
    }
}