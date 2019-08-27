package com.example.mydoctor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.mydoctor.Adapter.AppoinmentAdapter;
import com.example.mydoctor.Class.Appoinment;
import com.example.mydoctor.RecyclerView.AppoinmentListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InDateAppoinmentActivity extends AppCompatActivity {

    private RecyclerView inDateRV;
    private AppoinmentAdapter appoinmentAdapter;
    private ArrayList<Appoinment> appoinments;
    private DatabaseReference reference;
    private String date;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_date_appoinment);

        init();
        date = getIntent().getStringExtra("date");

        configRecycler();
    }

    private void configRecycler() {

        inDateRV.setLayoutManager(new LinearLayoutManager(this));


        DatabaseReference apoinmentReferance = reference.child("ApprovedAppoinmentData").child(mAuth.getUid()).child(date);
        apoinmentReferance.keepSynced(true);

        apoinmentReferance.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Appoinment appoinment = dataSnapshot1.getValue(Appoinment.class);




                    appoinments.add(appoinment);
//                    if (currentDrID.equals(appoinmentDrID)) {
//
//                    }

                    appoinmentAdapter = new AppoinmentAdapter(InDateAppoinmentActivity.this, appoinments);
                    inDateRV.setAdapter(appoinmentAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void init() {

        inDateRV = findViewById(R.id.inDateAppoinmentRV);
        appoinments = new ArrayList<>();
       mAuth = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference();

        reference.keepSynced(true);

    }
}
