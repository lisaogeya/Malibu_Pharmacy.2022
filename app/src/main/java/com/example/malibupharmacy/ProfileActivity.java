package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private final String TAG = "PROFILE_ACTIVITY";
    private AppCompatImageButton imageProf;
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imagechats;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private HashMap<String, String> userInfo;

    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        AppCompatImageButton imageProf = (AppCompatImageButton) findViewById(R.id.imageProf);
        AppCompatImageButton imageHome = (AppCompatImageButton) findViewById(R.id.imageHome);
        AppCompatImageButton imagechats = (AppCompatImageButton) findViewById(R.id.imagechats);
        AppCompatImageView ocr = (AppCompatImageView) findViewById(R.id.ocr);
        AppCompatImageView medHistory = (AppCompatImageView) findViewById(R.id.medHistory);
        AppCompatImageView insurance = (AppCompatImageView) findViewById(R.id.insurance);
        AppCompatImageView delivery = (AppCompatImageView) findViewById(R.id.delivery);
        MaterialButton btnLogout = (MaterialButton) findViewById(R.id.btn_logout);
        TextView profileName = (TextView) findViewById(R.id.textUsername);
        AppCompatImageView shareButton = (AppCompatImageView) findViewById(R.id.img_share);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        db = new DBHelper(this);
        userInfo = new HashMap<>();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            btnLogout.setVisibility(View.VISIBLE);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signOut();
                    startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                }
            });

            DocumentReference ref = firestore.collection("users").document(currentUser.getEmail());

            ref.get().addOnCompleteListener(ProfileActivity.this, task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    Log.i(TAG, "onCreate: " + documentSnapshot.getData());

                    //unstructured data wrap in try catch
                    try {
                        Map data = documentSnapshot.getData();
                        userInfo = (HashMap<String, String>) data.get("aboutUser");

                        profileName.setText("Welcome " + userInfo.get("firstName"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        }

        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, HomepageActivity.class);
                startActivity(intent);
                finish();
            }
        });
        imagechats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, ChatsActivity.class);
                startActivity(intent);
                finish();
            }
        });
        ocr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, OCRActivity.class);
                startActivity(intent);
            }
        });
        medHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MedicalHistoryActivity.class);
                startActivity(intent);
            }
        });
        insurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, InsuranceDetailsActivity.class);
                startActivity(intent);
            }
        });
        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, DeliveryDetailsActivity.class);
                startActivity(intent);
            }
        });

        shareButton.setOnClickListener(v -> {
            HashMap<String, String> data = getShareData();
            if (data.isEmpty()) {
                Toast.makeText(this, "No data available for sharing", Toast.LENGTH_SHORT).show();
                return;
            }
            ShareDialog dialog = new ShareDialog();
            dialog.show(getSupportFragmentManager(), "ShareDialog");

        });
    }

    private HashMap<String, String> getShareData() {
        HashMap<String, String> data = new HashMap<>();
        String allergies = db.getAllergies();
        HashMap<String, String> tests = db.getTests();
        HashMap<String, String> deliveryInfo = db.getDeliveryInfo();
        HashMap<String, String> insuranceInfo = db.getInsuranceInfo();
        HashMap<String, String> userInformation = userInfo;
        String underlyingConditions = db.getUnderlyingConditions();
        String additionalInfo = db.getAdditionalInformation();

        data.put("Allergies", allergies);
        data.put("Tests", tests.toString());
        data.put("DeliveryInfo", deliveryInfo.toString());
        data.put("InsuranceInfo", insuranceInfo.toString());
        data.put("UnderlyingConditions", underlyingConditions);
        data.put("AdditionalInfo", additionalInfo);
        data.put("UserInformation", userInformation.toString());
        return data;
    }

    public void shareData(String email) {
        HashMap<String, String> data = getShareData();

        DocumentReference ref = firestore.collection("users").document(email);

        ref.get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                ref.update("notifications", FieldValue.arrayUnion(data)).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Data shared successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error sharing data", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {

                HashMap<String ,String > newData = new HashMap<>();
                newData.put("notifications", data.toString());
                firestore.collection("users").document(email).set(newData).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Data shared successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Error sharing data", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
