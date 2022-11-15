package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

TextView HeadToSignUp;
EditText username, password;
DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        TextView HeadToSignUp = (TextView) findViewById(R.id.HeadToSignUp);
        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        DB = new DBHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneno = username.getText().toString();
                String pass = password.getText().toString();
                
                if (phoneno.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.confirmusercredentials(phoneno,pass);
                    if (checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }
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
}