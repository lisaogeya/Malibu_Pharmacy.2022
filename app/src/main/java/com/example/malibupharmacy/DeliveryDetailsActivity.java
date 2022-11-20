package com.example.malibupharmacy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class DeliveryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_details);

        DBHelper dbHelper = new DBHelper(this);

        TextInputEditText location = (TextInputEditText) findViewById(R.id.textLocationEt);
        TextInputEditText street = (TextInputEditText) findViewById(R.id.textStreetEt);
        TextInputEditText additionalInfo = (TextInputEditText) findViewById(R.id.textDeliveryEt);
        Button saveButton = (Button) findViewById(R.id.btn_save);

        saveButton.setOnClickListener(v -> {
            if (location.getText().toString().equals("") || street.getText().toString().equals("") || additionalInfo.getText().toString().equals("")) {
                Toast.makeText(DeliveryDetailsActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean saved = dbHelper.saveDeliveryInfo(location.getText().toString(), street.getText().toString(), additionalInfo.getText().toString());

            if (saved) {
                Toast.makeText(DeliveryDetailsActivity.this, "Delivery details saved", Toast.LENGTH_SHORT).show();
                DeliveryDetailsActivity.this.onBackPressed();
            } else {
                Toast.makeText(DeliveryDetailsActivity.this, "Delivery details could not be saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
}