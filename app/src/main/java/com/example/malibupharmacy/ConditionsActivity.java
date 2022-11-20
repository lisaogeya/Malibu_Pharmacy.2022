package com.example.malibupharmacy;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class ConditionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);

        EditText conditions = (EditText) findViewById(R.id.conditionsEt);
        MaterialButton saveButton = (MaterialButton) findViewById(R.id.saveCBtn);
        DBHelper db = new DBHelper(ConditionsActivity.this);


        saveButton.setOnClickListener(v -> {
            if (conditions.getText().toString().equals("")) {
                Toast.makeText(ConditionsActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean saved = db.saveUnderlyingCondition(conditions.getText().toString());

            if (saved) {
                Toast.makeText(ConditionsActivity.this, "Conditions saved", Toast.LENGTH_SHORT).show();
                ConditionsActivity.this.onBackPressed();
            } else {
                Toast.makeText(ConditionsActivity.this, "Error saving the condition.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}