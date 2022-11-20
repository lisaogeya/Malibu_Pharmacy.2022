package com.example.malibupharmacy;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

public class AllergiesActivity extends AppCompatActivity {
    EditText allergyEt;
    MaterialButton saveABtn, viewABtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allergies);

        allergyEt = (EditText) findViewById(R.id.allergyEt);

        MaterialButton saveABtn = (MaterialButton) findViewById(R.id.saveABtn);
        MaterialButton viewABtn = (MaterialButton) findViewById(R.id.viewABtn);
        DBHelper DB = new DBHelper(this);
        String phoneno = "907";
        saveABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = allergyEt.getText().toString();
                /*String user_id = "wer";
                String num2 = num.toString();
                String id = user_id + "_" + num2;*/
                Integer id = 67;

                if (description.equals(""))
                    Toast.makeText(AllergiesActivity.this, "Allergy has not been entered", Toast.LENGTH_SHORT).show();
                else {
                    Boolean saveAllergy = DB.saveAllergies(description);
                    if (saveAllergy) {
                        Toast.makeText(AllergiesActivity.this, "Allergy Saved", Toast.LENGTH_SHORT).show();
                        allergyEt.setText("");
                    }
                    else
                        Toast.makeText(AllergiesActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }


            }
        });

        viewABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String allergies = DB.getAllergies();

                if (allergies.isEmpty()) {
                    Toast.makeText(AllergiesActivity.this, "No allergies recorded yet", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(AllergiesActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(allergies);
                builder.show();
            }
        });


    }
}