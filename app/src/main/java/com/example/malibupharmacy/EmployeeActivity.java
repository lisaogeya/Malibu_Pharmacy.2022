package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

import com.google.android.material.button.MaterialButton;

public class EmployeeActivity extends AppCompatActivity {
    private final String TAG = "LOGIN_ACTIVITY";

    EditText employee_id, Epassword;
    MaterialButton loginemployeebtn;
    DBHelper DB;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);
        MaterialButton loginemployeebtn= (MaterialButton) findViewById(R.id.loginemployeebtn);
        employee_id = (EditText) findViewById(R.id.employeeid);
        Epassword = (EditText) findViewById(R.id.Epassword);
        DB = new DBHelper(this);

        firebaseAuth = FirebaseAuth.getInstance();

        loginemployeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = employee_id.getText().toString();
                String pass = Epassword.getText().toString();

                if (email.equals("") || pass.equals(""))
                    Toast.makeText(EmployeeActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                else {

                    loginWithFirebase(email, pass);
                }
            }
        });


            }
    private void loginWithFirebase(String email, String Epassword) {
        firebaseAuth.signInWithEmailAndPassword(email.trim(), Epassword)
                .addOnCompleteListener(EmployeeActivity.this, task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(EmployeeActivity.this, EmployeeHomepage.class);
                        startActivity(intent);
                    }else {

                        Log.i(TAG, "loginWithFirebase: " + task.getException());

                        if(task.getException() instanceof IOException){
                            Toast.makeText(EmployeeActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(EmployeeActivity.this, "Confirm email and password are correct.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
        }



