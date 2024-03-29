package com.example.mydoctor.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mydoctor.Adapter.AppoinmentAdapter;
import com.example.mydoctor.Class.Appoinment;
import com.example.mydoctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppoinmentListActivity extends AppCompatActivity {

    private RecyclerView appoinmentRV;
    private AppoinmentAdapter appoinmentAdapter;
    private ArrayList<Appoinment> appoinmentArrayList;
    private FirebaseAuth mAuth;
    String currentDrID;

    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_list);

        init();
         currentDrID = getIntent().getStringExtra("drId");

        configRecycler();
    }

    private void configRecycler() {

        appoinmentRV.setLayoutManager(new LinearLayoutManager(this));


        DatabaseReference apoinmentReferance = reference.child("AppoinmentData").child(currentDrID);
        apoinmentReferance.keepSynced(true);

        apoinmentReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Appoinment appoinment = dataSnapshot1.getValue(Appoinment.class);


                    String appoinmentDrID = appoinment.getDrId();

                    appoinmentArrayList.add(appoinment);
//                    if (currentDrID.equals(appoinmentDrID)) {
//
//                    }

                    appoinmentAdapter = new AppoinmentAdapter(AppoinmentListActivity.this, appoinmentArrayList);
                    appoinmentRV.setAdapter(appoinmentAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {

        appoinmentRV = findViewById(R.id.appoinmentListRV);
        appoinmentArrayList = new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

       reference = FirebaseDatabase.getInstance().getReference();

        reference.keepSynced(true);

    }
}
