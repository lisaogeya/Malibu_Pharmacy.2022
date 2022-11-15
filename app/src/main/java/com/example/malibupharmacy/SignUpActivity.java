package com.example.malibupharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class SignUpActivity extends AppCompatActivity {
    EditText textFNameEt, textLNameEt, contactEt, emailEt, passwordEt, re_passwordEt;
    MaterialButton registerBtn;
    MaterialButton loginBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        textFNameEt = (EditText) findViewById(R.id.textFNameEt);
        textLNameEt = (EditText) findViewById(R.id.textLNameEt);
        contactEt = (EditText) findViewById(R.id.contactEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        passwordEt = (EditText) findViewById(R.id.passwordEt);
        re_passwordEt = (EditText) findViewById(R.id.re_passwordEt);
        MaterialButton registerBtn = (MaterialButton) findViewById(R.id.registerBtn);
        MaterialButton loginBtn = (MaterialButton) findViewById(R.id.loginBtn);
        DBHelper DB= new DBHelper(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = textFNameEt.getText().toString();
                String lname = textLNameEt.getText().toString();
                String email = emailEt.getText().toString();
                String phoneno = contactEt.getText().toString();
                String pass = passwordEt.getText().toString();
                String password2 = re_passwordEt.getText().toString();

                if (phoneno.equals("")|| pass.equals("")||fname.equals("")||lname.equals("")||email.equals("")||password2.equals(""))
                    Toast.makeText(SignUpActivity.this, "Kindly fill all fields.", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(password2)){
                        Boolean checkuserdata = DB.checkuserdata(fname);
                        if (checkuserdata==false){
                            Integer num = DB.getnumberofclients(phoneno);
                            Integer id = ++num;
                            Boolean insert = DB.saveUser(id, fname, lname, email, phoneno, pass);
                            if (insert==true){
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignUpActivity.this, HomepageActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SignUpActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignUpActivity.this, "User already exists.Sign In", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SignUpActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}