package com.example.mydoctor.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mydoctor.R;

public class HomeActivity extends AppCompatActivity {

    Button doctorsBtn;
    Button patientBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        doctorsBtn=(Button) findViewById(R.id.btn_doctors_module);
        patientBtn=(Button) findViewById(R.id.btn_patient_module);

        doctorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctorsLoginActivity();
            }


        });
        patientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }

        });
    }

    public void openMainActivity(){
        Intent intent= new Intent(this, PatientLoginActivity.class);
        startActivity(intent);
    }

    public void openDoctorsLoginActivity(){
        Intent intent= new Intent(this, DoctorsLoginActivity.class);
        startActivity(intent);
    }
}
