package com.example.malibupharmacy;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class MoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);

        EditText moreInfo = (EditText) findViewById(R.id.moreInfoEt);
        MaterialButton saveMoreInfoBtn = (MaterialButton) findViewById(R.id.saveMBtn);
        DBHelper db = new DBHelper(this);

        saveMoreInfoBtn.setOnClickListener(v -> {
            if (moreInfo.getText().toString().equals("")) {
                Toast.makeText(MoreInfoActivity.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean saved = db.saveAdditionalInformation(moreInfo.getText().toString());

            if (saved) {
                Toast.makeText(MoreInfoActivity.this, "Information Saved", Toast.LENGTH_SHORT).show();
                MoreInfoActivity.this.onBackPressed();
            } else Toast.makeText(MoreInfoActivity.this, "Error", Toast.LENGTH_SHORT).show();
        });
    }
}