package com.example.mydoctor.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctor.RecyclerView.InDateAppoinmentActivity;
import com.example.mydoctor.RecyclerView.AppoinmentListActivity;
import com.example.mydoctor.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DoctorsMenuActivity extends AppCompatActivity {

    private Button doctorsInformation;
    private Button doctorsAppoinments,inDateAppoinmentCheck;

    private FirebaseAuth mAuth;

    private String doctorId;

    private TextView dateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_infirmation_edit);

        mAuth=FirebaseAuth.getInstance();
        doctorId=mAuth.getUid();

        doctorsInformation=findViewById(R.id.btn_Doctors_Information);
        doctorsAppoinments =findViewById(R.id.btn_Doctors_Appoinment);

        inDateAppoinmentCheck= findViewById(R.id.btn_Doctors_Appoinment_Date);
        dateEt =findViewById(R.id.dateET);


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

        dateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentDatePicker();
            }
        });

        inDateAppoinmentCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorsMenuActivity.this, InDateAppoinmentActivity.class);
                intent.putExtra("date",dateEt.getText().toString());

                if(dateEt.getText().toString().isEmpty())
                {
                    Toast.makeText(DoctorsMenuActivity.this, "Select a date", Toast.LENGTH_SHORT).show();
                }
                else

                startActivity(intent);
            }
        });


    }

    private void openAppointmentListActivity() {

        Intent intent= new Intent(this, AppoinmentListActivity.class);


        intent.putExtra("drId",doctorId);
        startActivity(intent);
    }


    public void openDoctorsInformationActivity(){
        Intent intent= new Intent(this, DoctorsInformationActivity.class);
        startActivity(intent);

    }

    private void currentDatePicker() {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                month = month + 1;

                String currentDate = year + "/" + month + "/" + day;

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

                Date date = null;

                try {
                    date = simpleDateFormat.parse(currentDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                dateEt.setText(simpleDateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

}
