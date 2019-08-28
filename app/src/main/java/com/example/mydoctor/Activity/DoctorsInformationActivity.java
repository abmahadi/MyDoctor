package com.example.mydoctor.Activity;

import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mydoctor.Class.Doctor;
import com.example.mydoctor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DoctorsInformationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private  EditText doctorRegistrationNo, doctorFirstName,doctorLastName;
   private Spinner spinnerSpecialization,spinnerDoctorDegree;
   private Spinner spinnerOfficeCity,spinnerOfficeArea;
   private EditText etStreetAddress,specialNotice;
   private String doctorId;
   private TextView  startTimeTV,endTime;

    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    Button saveData;
    ProgressBar progressBarDoctorSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_information);

        databaseReference= FirebaseDatabase.getInstance().getReference("DoctorData");

        spinnerSpecialization=findViewById(R.id.spinner_Specialization);



        spinnerOfficeCity=findViewById(R.id.spinner_officeCity);
        spinnerOfficeArea=findViewById(R.id.spinner_officeArea);
        etStreetAddress=findViewById(R.id.et_officeStreetAddress);
        spinnerDoctorDegree=findViewById(R.id.spinner_Degrees);

        mAuth=FirebaseAuth.getInstance();
        doctorId=mAuth.getUid();

        final String city[]=getResources().getStringArray(R.array.division);
        final String degree[]=getResources().getStringArray(R.array.Degrees);

        final String Dhaka[]=getResources().getStringArray(R.array.Dhaka);
        final String Rajshahi[]=getResources().getStringArray(R.array.Rajshahi);
        final String Rangpur[]=getResources().getStringArray(R.array.Rangpur);


        saveData=findViewById(R.id.btn_doctorSaveData);
        progressBarDoctorSaveData=findViewById(R.id.progressBar_doctorSaveData);

        doctorRegistrationNo=findViewById(R.id.et_doctorsRegistrationNo);
        doctorFirstName=findViewById(R.id.et_doctor_firstName);
        doctorLastName=findViewById(R.id.et_Doctor_lastName);
        startTimeTV=findViewById(R.id.et_startTime);
        endTime=findViewById(R.id.et_endTime);
        specialNotice=findViewById(R.id.et_SpecialNotice);

        startTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTimePicker(startTimeTV);
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTimePicker(endTime);
            }
        });


        final String specialization[]=getResources().getStringArray(R.array.type_of_doctor);

        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        ArrayAdapter adapter_degree=ArrayAdapter.createFromResource(this,R.array.Degrees,android.R.layout.simple_spinner_item);
        spinnerDoctorDegree.setAdapter(adapter_degree);

        spinnerDoctorDegree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView myText = (TextView) view;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter_city=ArrayAdapter.createFromResource(this,R.array.division,android.R.layout.simple_spinner_item);
        spinnerOfficeCity.setAdapter(adapter_city);

        spinnerOfficeCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelect = city[position];

                Toast.makeText(DoctorsInformationActivity.this, "Select "+itemSelect, Toast.LENGTH_SHORT).show();
                if(position == 0){
                    ArrayAdapter adapter_Dhaka = ArrayAdapter.createFromResource(DoctorsInformationActivity.this ,R.array.Dhaka,android.R.layout.simple_spinner_item);
                    spinnerOfficeArea.setAdapter(adapter_Dhaka);
                }
                if(position == 1){
                    ArrayAdapter adapter_Rajshahi =ArrayAdapter.createFromResource(DoctorsInformationActivity.this ,R.array.Rajshahi,android.R.layout.simple_spinner_item);
                    spinnerOfficeArea.setAdapter(adapter_Rajshahi);
                }
                if(position == 2){
                    ArrayAdapter adapter_Rangpur = ArrayAdapter.createFromResource(DoctorsInformationActivity.this ,R.array.Rangpur,android.R.layout.simple_spinner_item);
                    spinnerOfficeArea.setAdapter(adapter_Rangpur);
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ArrayAdapter adapter_typesOfDoctor= ArrayAdapter.createFromResource(this,R.array.type_of_doctor,android.R.layout.simple_spinner_item);
        spinnerSpecialization.setAdapter(adapter_typesOfDoctor);
        spinnerSpecialization.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView myText = (TextView) view;
        final String specialization[]=getResources().getStringArray(R.array.type_of_doctor);
        String itemSelect = specialization[position];


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void saveData(){

        String id=doctorRegistrationNo.getText().toString().trim();
        String fname= doctorFirstName.getText().toString().trim();
        String lname= doctorLastName.getText().toString().trim();
        String specilization=spinnerSpecialization.getSelectedItem().toString() ;
        String degree=spinnerDoctorDegree.getSelectedItem().toString();

        String City=spinnerOfficeCity.getSelectedItem().toString() ;
        String Area=spinnerOfficeArea.getSelectedItem().toString() ;
        String StreetAddress= etStreetAddress.getText().toString().trim();
        String startTime=startTimeTV.getText().toString().trim();
        String EndTime=endTime.getText().toString().trim();
        String special=specialNotice.getText().toString().trim();




        Doctor doctor = new Doctor(doctorId,id,fname,lname,specilization,degree,City,Area,StreetAddress,startTime,EndTime,special);
        databaseReference.child(specilization).push().setValue(doctor);

        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();

    }
    private void currentTimePicker(final TextView timeTV) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(calendar.HOUR);
        int minute = calendar.get(calendar.MINUTE);
        boolean is24HourFormat = false;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Calendar time = Calendar.getInstance();
                time.set(Calendar.HOUR, hour);
                time.set(Calendar.MINUTE, minute);
                CharSequence charSequence = DateFormat.format("hh:mm a", time);
                timeTV.setText(charSequence);
            }
        }, hour, minute, is24HourFormat);
        timePickerDialog.show();
    }
}
