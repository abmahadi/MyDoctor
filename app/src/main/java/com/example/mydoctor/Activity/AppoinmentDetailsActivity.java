package com.example.mydoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctor.Class.Appoinment;
import com.example.mydoctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppoinmentDetailsActivity extends AppCompatActivity {

    private TextView email,mobileNumber,date,time;
    private Button addBtn,deleteBtn;
    private String appoinmentId,drId;
    private FirebaseAuth mAuth;
    private String id;

    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_details);

        init();
        appoinmentId = getIntent().getStringExtra("id");
        drId = getIntent().getStringExtra("drId");
         id= mAuth.getUid();

        setText();


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("AppoinmentData").child(id).child(appoinmentId).setValue(null);
                reference.keepSynced(true);

                Toast.makeText(AppoinmentDetailsActivity.this, "Appoinment Delete", Toast.LENGTH_SHORT).show();
            }
        });
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String currentAppoinmentId=reference.push().getKey();



                boolean appoinmentStatus=true;








                Appoinment appoinmentData= new Appoinment(currentAppoinmentId,drId,email.getText().toString(),mobileNumber.getText().toString(),date.getText().toString(),time.getText().toString(),appoinmentStatus);
                reference.child("ApprovedAppoinmentData").child(id).child(date.getText().toString()).child(currentAppoinmentId).setValue(appoinmentData);
                reference.child("AppoinmentData").child(id).child(appoinmentId).setValue(null);
                reference.keepSynced(true);

                Toast.makeText(AppoinmentDetailsActivity.this, "Appoinment Approve", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setText() {

        email.setText(getIntent().getStringExtra("email"));
        mobileNumber.setText(getIntent().getStringExtra("mobileNumber"));
        date.setText(getIntent().getStringExtra("date"));
        time.setText(getIntent().getStringExtra("time"));
    }

    private void init() {

        email = findViewById(R.id.patientEmailTV_aad);
        mobileNumber = findViewById(R.id.mobilenumberPatient_aad);
        date = findViewById(R.id.dateTV_aad);
        time = findViewById(R.id.timeTV_aad);
        addBtn =findViewById(R.id.appoinmentAddBtn);
        deleteBtn =findViewById(R.id.appoinmentDeleteBtn);
        reference = FirebaseDatabase.getInstance().getReference();
        mAuth =FirebaseAuth.getInstance();


    }
}
