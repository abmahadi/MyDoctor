package com.example.mydoctor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorsInfirmationEditActivity extends AppCompatActivity {

    Button doctorsInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_infirmation_edit);

        doctorsInformation=findViewById(R.id.btn_Doctors_Information);


        doctorsInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctorsInformationActivity();
            }
        });


    }



    public void openDoctorsInformationActivity(){
        Intent intent= new Intent(this,DoctorsInformationActivity.class);
        startActivity(intent);

    }

}
