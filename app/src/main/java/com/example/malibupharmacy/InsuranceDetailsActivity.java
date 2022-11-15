package com.example.malibupharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class InsuranceDetailsActivity extends AppCompatActivity {
    TextView textTitle;
    EditText textInputEditText5,textInputEditText,textInputEditText3,textInputEditText6,textInputEditText4,textInputEditText2;
    MaterialButton saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        MaterialButton scanPrint = (MaterialButton) findViewById(R.id.scanPrint);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textInputEditText5 = (EditText) findViewById(R.id.textInputEditText5);
        textInputEditText = (EditText) findViewById(R.id.textInputEditText);
        textInputEditText3 = (EditText) findViewById(R.id.textInputEditText3);
        textInputEditText6 = (EditText) findViewById(R.id.textInputEditText6);
        textInputEditText4 = (EditText) findViewById(R.id.textInputEditText4);
        textInputEditText2 = (EditText) findViewById(R.id.textInputEditText2);
        saveBtn = (MaterialButton) findViewById(R.id.saveBtn);


        scanPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsuranceDetailsActivity.this, BiometricActivity.class);
                startActivity(intent);


            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = textTitle.getText().toString();
                String content = textInputEditText5.getText().toString();

                if (!filename.equals("") && !content.equals("")) {
                    saveTextAsFile(filename, content);
                }


            }
        });
    }
    private void saveTextAsFile(String filename, String content){
        String fileName = filename + ".txt";

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), fileName);


        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(content.getBytes());
            fos.close();
            Toast.makeText(this, "SAVED", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                    finish();
                }

        }

    }
}