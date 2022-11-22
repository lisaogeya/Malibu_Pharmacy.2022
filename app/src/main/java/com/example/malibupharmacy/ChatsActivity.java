package com.example.malibupharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatsActivity extends AppCompatActivity {
    private final String TAG = "CHATS_ACTIVITY";
    private AppCompatImageButton imageHome;
    private AppCompatImageButton imageProf;
    private AppCompatImageButton imagechats;
    RecyclerView recyclerView;
    ArrayList<User> userArrayList;
    NotificationsAdapter notificationsAdapter;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        AppCompatImageButton imageHome = findViewById(R.id.imageHome);
        AppCompatImageButton imageProf = findViewById(R.id.imageProf);
        AppCompatImageButton imagechats = findViewById(R.id.imagechats);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firestore = FirebaseFirestore.getInstance();
        userArrayList = new ArrayList<User>();


        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, HomepageActivity.class);
                startActivity(intent);
            }
        });
        imageProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        imagechats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChatsActivity.this, ChatsActivity.class);
                startActivity(intent);
            }
        });


        //todo extract user info from firestore add populate recycler view
        firestore.collection("users").document("pharmattendant@gmail.com").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                DocumentSnapshot document = task.getResult();

                if (document.exists()) {

                    //this data is a hashmap
                    try {
                        Map data = document.getData();

                        //todo sort the data  into a list of entries and inject into recycler view adapter
                        ArrayList<HashMap<String, String>> notifications = (ArrayList<HashMap<String, String>>) data.get("notifications");

                        notificationsAdapter = new NotificationsAdapter(ChatsActivity.this,userArrayList);

                        //check log for data structure
                        Log.i(TAG, "onCreate: " + document.getData());
                    } catch (Exception e) {
                        Log.i(TAG, "onCreate: " + e.getMessage());
                    }
                } else {
                    System.out.println("Document does not exist");
                }
            } else {
                //the task failed to complete
            }
        });
    }
}