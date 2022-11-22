package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "LOGIN_ACTIVITY";
    TextView HeadToSignUp;
    EditText username, password;
    DBHelper DB;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        TextView HeadToSignUp = (TextView) findViewById(R.id.HeadToSignUp);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        DB = new DBHelper(this);

        firebaseAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString();
                String pass = password.getText().toString();

                if (email.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                else {

                    loginWithFirebase(email, pass);
                }
            }
        });
        HeadToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginWithFirebase(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email.trim(), password)
                .addOnCompleteListener(LoginActivity.this, task -> {
                    if(task.isSuccessful()){
                        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                        startActivity(intent);
                    }else {

                        Log.i(TAG, "loginWithFirebase: " + task.getException());

                        if(task.getException() instanceof IOException){
                            Toast.makeText(LoginActivity.this, "Please connect to the internet", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Toast.makeText(LoginActivity.this, "Confirm email and password are correct.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}