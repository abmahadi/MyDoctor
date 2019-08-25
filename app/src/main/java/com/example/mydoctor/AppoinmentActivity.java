package com.example.mydoctor;

        import android.app.DatePickerDialog;
        import android.app.TimePickerDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.format.DateFormat;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class AppoinmentActivity extends AppCompatActivity {

   private Button appoinmentBtn;
   private DatabaseReference databaseReference;
    private String doctorRegistrationNo;
    private TextView drRegistationNo;
    private LinearLayout apoinmentDate,apoinmentTime;
    private TextView dateTV,currentPatientId,timeTV;
    private EditText mobilenumberPatient;



    private FirebaseAuth mAuth;
    private String currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);
        appoinmentBtn=findViewById(R.id.appoinment_btn);
        databaseReference= FirebaseDatabase.getInstance().getReference("AppoinmentData");

        init();
        doctorRegistrationNo = getIntent().getExtras().getString("drID");
        drRegistationNo.setText(doctorRegistrationNo);
       currentUser = mAuth.getCurrentUser().getUid();
       currentPatientId.setText(currentUser);


       apoinmentDate.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               currentDatePicker();

           }
       });
       apoinmentTime.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               currentTimePicker();

           }
       });



        appoinmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData();
            }
        });
    }

    private void init() {
        drRegistationNo = findViewById(R.id.drRegistationNoTV);
        apoinmentDate = findViewById(R.id.dateLayoutApoinment);
        apoinmentTime =findViewById(R.id.timeLayoutApoinment);
        dateTV = findViewById(R.id.dateTVApoinment);
        timeTV = findViewById(R.id.timeTVApoinment);
        currentPatientId =findViewById(R.id.currentPatientId);
        mobilenumberPatient =findViewById(R.id.mobilenumberPatient);

        mAuth =FirebaseAuth.getInstance();
    }


    public void SetData(){
        String appoinmentId=databaseReference.push().getKey();

         String drId=doctorRegistrationNo;
         String userId=currentUser;
         String mobileNumber =mobilenumberPatient.getText().toString();
         String date=dateTV.getText().toString();
         String time =timeTV.getText().toString();
         boolean appoinmentStatus=false;






        Appoinment appoinmentData= new Appoinment(appoinmentId,drId,userId,mobileNumber,date,time,appoinmentStatus);
        databaseReference.child(appoinmentId).setValue(appoinmentData);

        Toast.makeText(this, "Appointment Request Send", Toast.LENGTH_SHORT).show();

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

                dateTV.setText(simpleDateFormat.format(date));
            }
        };

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, year, month, day);
        datePickerDialog.show();
    }

    private void currentTimePicker() {
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

