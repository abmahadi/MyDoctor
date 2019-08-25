package com.example.mydoctor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppoinmentListActivity extends AppCompatActivity {

    private RecyclerView appoinmentRV;
    private AppoimentAdapter appoimentAdapter;
    private ArrayList<Appoinment> appoinmentArrayList;
    private FirebaseAuth mAuth;

    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_list);

        init();
        configRecycler();
    }

    private void configRecycler() {

        appoinmentRV.setLayoutManager(new LinearLayoutManager(this));

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Appoinment appoinment =dataSnapshot1.getValue(Appoinment.class);

                    String currentDrID= mAuth.getUid();
                    String appoinmentDrID =appoinment.getDrId();

                    if(currentDrID == appoinmentDrID)
                    {
                        appoinmentArrayList.add(appoinment);
                    }

                    appoimentAdapter = new AppoimentAdapter(AppoinmentListActivity.this,appoinmentArrayList);
                    appoinmentRV.setAdapter(appoimentAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {

        appoinmentRV = findViewById(R.id.appoinmentListRV);
        appoinmentArrayList= new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference().child("AppoinmentData");
        reference.keepSynced(true);
        mAuth =FirebaseAuth.getInstance();
    }
}
