package com.example.malibupharmacy;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class TestsActivity extends AppCompatActivity {

    EditText testDateEt, testLocationEt, testTitleEt;
    MaterialButton saveTestBtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tests);

        testDateEt = (EditText) findViewById(R.id.testDateEt);
        testLocationEt = (EditText) findViewById(R.id.testNameEt);
        testTitleEt = (EditText) findViewById(R.id.testLocationEt);

        MaterialButton saveTestBtn = (MaterialButton) findViewById(R.id.saveTest);
        DBHelper DB = new DBHelper(this);

        saveTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String testDate = testDateEt.getText().toString();
                String testTitle = testLocationEt.getText().toString();
                String testLocation = testLocationEt.getText().toString();

                if (testDate.equals("") || testLocation.equals("") || testTitle.equals(""))
                    Toast.makeText(TestsActivity.this, "Kindly Fill All Fields.", Toast.LENGTH_SHORT).show();
                else {
                    Boolean saveAllergy = DB.saveTest(testTitle, testDate, testLocation);
                    if (saveAllergy) {
                        Toast.makeText(TestsActivity.this, "Test Saved", Toast.LENGTH_SHORT).show();
                        TestsActivity.this.onBackPressed();
                    } else Toast.makeText(TestsActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}