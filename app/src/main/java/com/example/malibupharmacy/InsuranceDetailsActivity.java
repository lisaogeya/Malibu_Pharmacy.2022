package com.example.malibupharmacy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class InsuranceDetailsActivity extends AppCompatActivity {
    private TextView textTitle;
    private EditText edtPrincipalName, edtPolicyNo, edtMemberNo, edtEmployer, edtDependant, edtInsurance;
    private MaterialButton saveBtn;
    private MaterialButton editButton;
    private DBHelper dbHelper;
    private TextView textInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance_details);

        dbHelper = new DBHelper(this);
        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }

        MaterialButton scanPrint = (MaterialButton) findViewById(R.id.scanPrint);
        edtPrincipalName = (EditText) findViewById(R.id.edt_principal_name);
        edtPolicyNo = (EditText) findViewById(R.id.edt_policy_no);
        edtMemberNo = (EditText) findViewById(R.id.edt_member_no);
        edtEmployer = (EditText) findViewById(R.id.edt_employer);
        edtDependant = (EditText) findViewById(R.id.edt_dependant);
        edtInsurance = (EditText) findViewById(R.id.edt_insurance);
        saveBtn = (MaterialButton) findViewById(R.id.saveBtn);
        editButton = (MaterialButton) findViewById(R.id.editBtn);
        textInfo = (TextView) findViewById(R.id.textinfo);

        scanPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InsuranceDetailsActivity.this, BiometricActivity.class);
                startActivity(intent);


            }
        });
        saveBtn.setOnClickListener(v -> {

            if (edtPrincipalName.getText().toString().equals("") || edtPolicyNo.getText().toString().equals("") || edtMemberNo.getText().toString().equals("") || edtEmployer.getText().toString().equals("") || edtDependant.getText().toString().equals("") || edtInsurance.getText().toString().equals("")) {

                Toast.makeText(InsuranceDetailsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean saved = dbHelper.saveInsuranceDetails(edtPrincipalName.getText().toString(), edtPolicyNo.getText().toString(), edtMemberNo.getText().toString(), edtEmployer.getText().toString(), edtDependant.getText().toString(), edtInsurance.getText().toString());
            if (saved) {
                Toast.makeText(InsuranceDetailsActivity.this, "Insurance details saved", Toast.LENGTH_SHORT).show();
                InsuranceDetailsActivity.this.onBackPressed();
            } else {
                Toast.makeText(InsuranceDetailsActivity.this, "Failed saving insurance details", Toast.LENGTH_SHORT).show();
            }

            // you can update this with the new info  above
            //todo  saveTextAsFile("Insurance Details", content);
        });

        editButton.setOnClickListener(v -> {
            HashMap<String, String> insuranceData = dbHelper.getInsuranceInfo();


            if (insuranceData.isEmpty()) {
                Toast.makeText(InsuranceDetailsActivity.this, "No insurance data to edit ", Toast.LENGTH_SHORT).show();
                return;
            }

            edtPrincipalName.setText((CharSequence) insuranceData.get("principal_name"));
            edtPolicyNo.setText((CharSequence) insuranceData.get("policy_no"));
            edtMemberNo.setText((CharSequence) insuranceData.get("member_no"));
            edtEmployer.setText((CharSequence) insuranceData.get("employer_no"));
            edtDependant.setText((CharSequence) insuranceData.get("dependant"));
            edtInsurance.setText((CharSequence) insuranceData.get("insurance"));
        });
    }

    public void checkPolicyDetails(View v){
        EditText edtPolicyNo = (EditText) findViewById(R.id.edt_policy_no);
        EditText edtInsurance = (EditText) findViewById(R.id.edt_insurance);
        String policyNo = edtPolicyNo.getText().toString();
        String insurance= edtInsurance.getText().toString();
        String type = "checkinsurance";
        BackgroundHelper backgroundHelper= new BackgroundHelper(this);
        backgroundHelper.execute(type, insurance, policyNo);
    }
    //you can update this function to save the rest of the details
    private void saveTextAsFile(String filename, String content) {
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
        } catch (IOException e) {
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