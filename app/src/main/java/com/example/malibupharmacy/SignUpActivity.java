package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {
    private final String TAG = "SIGN_UP";
    EditText textFNameEt, textLNameEt, contactEt, emailEt, passwordEt, re_passwordEt;
    MaterialButton registerBtn;
    MaterialButton loginBtn;
    DBHelper DB;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;


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
        DBHelper DB = new DBHelper(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = textFNameEt.getText().toString();
                String lname = textLNameEt.getText().toString();
                String email = emailEt.getText().toString();
                String phoneno = contactEt.getText().toString();
                String pass = passwordEt.getText().toString();
                String password2 = re_passwordEt.getText().toString();

                if (phoneno.equals("") || pass.equals("") || fname.equals("") || lname.equals("") || email.equals("") || password2.equals(""))
                    Toast.makeText(SignUpActivity.this, "Kindly fill all fields.", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(password2)) {

                        createFirebaseUserWithEmailAndPassword(email, pass, fname, lname, phoneno);
//                        Boolean checkuserdata = DB.checkuserdata(fname);
//                        if (!checkuserdata) {
//                            Integer num = DB.getnumberofclients(phoneno);
//                            Integer id = ++num;
//                            Boolean insert = DB.saveUser(id, fname, lname, email, phoneno, pass);
//                            if (insert == true) {
//                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(SignUpActivity.this, HomepageActivity.class);
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(SignUpActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(SignUpActivity.this, "User already exists.Sign In", Toast.LENGTH_SHORT).show();
//                        }
                    } else {
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

    //create a user on firebase and return the email if it the request was successful else return null
    private void createFirebaseUserWithEmailAndPassword(String email, String password, String firstName, String lastName, String phone) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {
                        Map<String, Object> aboutUser = new HashMap<>();
                        aboutUser.put("firstName", firstName);
                        aboutUser.put("lastName", lastName);
                        aboutUser.put("phone", phone);
                        aboutUser.put("role", "customer");

                        Map<String, Object> aboutUserFinal = new HashMap<>();
                        aboutUserFinal.put("aboutUser", aboutUser);

                        firestore.collection("users").document(email).set(aboutUserFinal)
                                .addOnCompleteListener(SignUpActivity.this, task1 -> {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SignUpActivity.this, "Account created with email " + email + ". You can now login.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    } else {
                                        if (task.getException() instanceof IOException) {
                                            Toast.makeText(SignUpActivity.this, "Please connect to the internet to continue.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "There was an error creating the account. Please try again later.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    } else {
                        if (task.getException() instanceof IOException) {
                            Toast.makeText(SignUpActivity.this, "Please connect to the internet to continue.", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUpActivity.this, "A user with this email already exists. Please sign in instead.", Toast.LENGTH_SHORT).show();
                        } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                            Toast.makeText(SignUpActivity.this, "Password is too weak. Please change it and try again.", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, "There was an error creating the account. Please try again later.", Toast.LENGTH_SHORT).show();
                        }

                        Log.i(TAG, "createFirebaseUserWithEmailAndPassword: " + task.getException());
                        task.getException().printStackTrace();
                    }
                });
    }
}