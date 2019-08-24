package com.example.mydoctor;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;

public class AppoinmentActivity extends AppCompatActivity {

   private Button appoinmentBtn;
   private DatabaseReference databaseReference;
    private String doctorRegistrationNo;
    private TextView drRegistationNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinment);
        appoinmentBtn=findViewById(R.id.appoinment_btn);
        databaseReference= FirebaseDatabase.getInstance().getReference("AppoinmentData");

        init();
        doctorRegistrationNo = getIntent().getExtras().getString("drID");
        drRegistationNo.setText(doctorRegistrationNo);


        appoinmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetData();
            }
        });
    }

    private void init() {
        drRegistationNo = findViewById(R.id.drRegistationNoTV);
    }


    public void SetData(){
        String appoinmentId=databaseReference.push().getKey();

         String drId=doctorRegistrationNo;
         String userId="0000";
         String date="10-12-2019";
         String appoinmentStatus="ok";
         String remarks="ok";





        Appoinment appoinmentData= new Appoinment(appoinmentId,drId,userId,date,appoinmentStatus,remarks);
        databaseReference.child(appoinmentId).setValue(appoinmentData);

        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();

    }
}

