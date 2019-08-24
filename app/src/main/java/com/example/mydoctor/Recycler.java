package com.example.mydoctor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recycler extends Activity {

   private DatabaseReference reference;
   private RecyclerView recyclerView;
   private ArrayList<DoctorsData> list;
   private Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        recyclerView =  findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager( new LinearLayoutManager(Recycler.this));


        reference = FirebaseDatabase.getInstance().getReference().child("DoctorData");
        reference.keepSynced(true);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    DoctorsData doctorsData = dataSnapshot1.getValue(DoctorsData.class);
                    list.add(doctorsData);
                }
                adapter = new Adapter(Recycler.this,list);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Recycler.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

