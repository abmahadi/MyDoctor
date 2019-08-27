package com.example.mydoctor.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.mydoctor.Class.Doctor;
import com.example.mydoctor.Adapter.DoctorAdapter;
import com.example.mydoctor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DoctorListRecyclerViewActivity extends Activity {

   private DatabaseReference reference;
   private RecyclerView recyclerView;
   private ArrayList<Doctor> list;
   private DoctorAdapter doctorAdapter;
   private String doctorSpecilazation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView =  findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager( new LinearLayoutManager(DoctorListRecyclerViewActivity.this));

        doctorSpecilazation=getIntent().getStringExtra("specilization");


        reference = FirebaseDatabase.getInstance().getReference().child("DoctorData").child(doctorSpecilazation);
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Doctor doctor = dataSnapshot1.getValue(Doctor.class);
                    list.add(doctor);
                }
                doctorAdapter = new DoctorAdapter(DoctorListRecyclerViewActivity.this,list);
                recyclerView.setAdapter(doctorAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DoctorListRecyclerViewActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

