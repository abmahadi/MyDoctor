package com.example.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorsInfirmationEditActivity extends AppCompatActivity {

    private Button doctorsInformation;
    private Button doctorsAppoinments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_infirmation_edit);

        doctorsInformation=findViewById(R.id.btn_Doctors_Information);
        doctorsAppoinments =findViewById(R.id.btn_Doctors_Appoinment);


        doctorsInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctorsInformationActivity();
            }
        });

        doctorsAppoinments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openAppointmentListActivity();
            }
        });


    }

    private void openAppointmentListActivity() {

        Intent intent= new Intent(this,AppoinmentListActivity.class);
        startActivity(intent);
    }


    public void openDoctorsInformationActivity(){
        Intent intent= new Intent(this,DoctorsInformationActivity.class);
        startActivity(intent);

    }

}
