package com.example.mydoctor.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mydoctor.Class.Patientest;
import com.example.mydoctor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientSignupActivity extends AppCompatActivity {

   private Button btnSignUp;
   private EditText txt_fname, txt_lname,  txt_password,  txt_phoneNumber, txt_email;
   private DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

   private ProgressBar progressBar_patentSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);

        mAuth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference("Patientest");



        btnSignUp = (Button) findViewById(R.id.btn_signUp);

        txt_fname = (EditText) findViewById(R.id.txt_fname);
        txt_lname = (EditText) findViewById(R.id.txt_lname);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_phoneNumber = (EditText) findViewById(R.id.txt_phoneNumber);
        txt_email = (EditText) findViewById(R.id.txt_email);

        progressBar_patentSignUp = (ProgressBar)findViewById(R.id.progressBar_patientSignUp);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userRegister();
                saveData();



            }
        });

    }

    private void userRegister() {
        String email= txt_email.getText().toString().trim();
        String password= txt_password.getText().toString().trim();

        //checking the validity of the email

        if(email.isEmpty())
        {
            txt_email.setError("Enter an email address");
            txt_email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            txt_email.setError("Enter a valid email address");
            txt_email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            txt_password.setError("Enter a password");
            txt_password.requestFocus();
            return;
        }
        progressBar_patentSignUp.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar_patentSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(PatientSignupActivity.this, "Register is successfull", Toast.LENGTH_SHORT).show();

                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(PatientSignupActivity.this, "User is already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else

                    Toast.makeText(PatientSignupActivity.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
    public void saveData(){
        String id=databaseReference.push().getKey();
        String fname= txt_fname.getText().toString().trim();
        String lname= txt_lname.getText().toString().trim();


        String phonenumber= txt_phoneNumber.getText().toString().trim();
        String email= txt_email.getText().toString().trim();

      Patientest patientest = new Patientest(id,fname,lname,email,phonenumber);
      databaseReference.child(id).setValue(patientest);

        Toast.makeText(this, "data saved", Toast.LENGTH_SHORT).show();

    }


}
