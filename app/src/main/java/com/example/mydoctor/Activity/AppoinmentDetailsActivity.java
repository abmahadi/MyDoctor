package com.example.mydoctor.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.mydoctor.R;

public class AppoinmentDetailsActivity extends AppCompatActivity {

    private TextView email,mobileNumber,date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment_details);

        init();

        setText();
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


    }
}
