package com.example.malibupharmacy;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import com.google.android.material.button.MaterialButton;

public class AllergiesActivity extends AppCompatActivity {
    EditText allergyEt;
    MaterialButton saveABtn,viewABtn;
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
                    else{
                    Boolean saveAllergy = DB.saveAllergies(id, description);
                    if (saveAllergy==true)
                        Toast.makeText(AllergiesActivity.this, "Allergy Saved", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(AllergiesActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }



            }
        });

        viewABtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getAdata();
                if (res.getCount()==0){
                    Toast.makeText(AllergiesActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Allergy:"+res.getString(0)+"\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(AllergiesActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

                    
            }
        });



    }
}