package com.example.mydoctor.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mydoctor.R;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String doctorRegistrationNo;
    private String firstName;
    private String lastName;
    private String specialization;
    private String degree;
    private String city;
    private String area;
    private String street;
    private String startTime;
    private String endTime;
    private String specialNotice;

    private TextView drID,drName,drSpecilazitaion,drDegree,drCityAreaStreet,drTime,drSpecialNotice;

    private Button checkID,appoinment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        init();

        if(getIntent().getExtras() !=null)
        {
            doctorRegistrationNo = getIntent().getStringExtra("id");
            firstName = getIntent().getStringExtra("firstName");
            lastName = getIntent().getStringExtra("lastName");
            specialization = getIntent().getStringExtra("specalization");
            degree = getIntent().getStringExtra("degree");
            city = getIntent().getStringExtra("city");
            area = getIntent().getStringExtra("area");
            street = getIntent().getStringExtra("street");
            startTime = getIntent().getStringExtra("startTime");
            endTime = getIntent().getStringExtra("endTime");
            specialNotice = getIntent().getStringExtra("specialNotice");

            drID.setText("ID : "+doctorRegistrationNo);
            drName.setText("Name               : "+firstName+" "+lastName);
            drSpecilazitaion.setText("Specialization  : "+specialization);
            drDegree.setText("Degree             : "+degree);
            drCityAreaStreet.setText("Address           : "+city+","+area+"\n"+street);
            drTime.setText("Time                : "+startTime+ "   to   "+endTime);
            drSpecialNotice.setText("Special Notice  : "+specialNotice);
        }

        checkID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bmdc.org.bd/doctors-info/"));
                startActivity(intent);
            }
        });

        appoinment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(DoctorDetailsActivity.this, AppoinmentActivity.class);
                intent.putExtra("drID",doctorRegistrationNo);
                startActivity(intent);
            }
        });
    }

    private void init() {
        drID = findViewById(R.id.doctorRegIDTV);
        drName = findViewById(R.id.doctorNameTV);
        drSpecilazitaion = findViewById(R.id.specializationTV);
        drDegree = findViewById(R.id.degreeTV);
        drCityAreaStreet = findViewById(R.id.cityAreaSteetTV);
        drTime = findViewById(R.id.timeTV);
        drSpecialNotice = findViewById(R.id.specialNoticeTV);
        checkID = findViewById(R.id.checkID);
        appoinment = findViewById(R.id.appoinmentBTN);

    }
}
