package com.example.mydoctor.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mydoctor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PatientLoginActivity extends AppCompatActivity {





    Button logIn;
    EditText email,password;
    ProgressBar progressBar_patientLogIn;

    private FirebaseAuth mAuth;


    TextView tvPatientSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        logIn =(Button) findViewById(R.id.btn_patientLogo);
        email = (EditText)findViewById(R.id.et_patientEmail);
        password = (EditText)findViewById(R.id.txt_patientPassword);
        progressBar_patientLogIn=(ProgressBar)findViewById(R.id.progressBar_patientLogIn);

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                
                userLogin();

            }
        });


        tvPatientSignIn = (TextView) findViewById(R.id.tv_patientSignIn);

        tvPatientSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPatientSignupActivity();
            }
        });
    }

    private void userLogin() {
        String id=email.getText().toString().trim();
        String pass= password.getText().toString().trim();

        if(id.isEmpty())
        {
            email.setError("Enter an email address");
            email.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(id).matches())
        {
            email.setError("Enter a valid email address");
            email.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            password.setError("Enter a password");
            password.requestFocus();
            return;
        }

        progressBar_patientLogIn.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(id,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar_patientLogIn.setVisibility(View.GONE);

                if(task.isSuccessful())
                {
                    openTypesOfDoctor();
                }
                else
                {
                    Toast.makeText(PatientLoginActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void openPatientSignupActivity(){
        Intent intent =new Intent(this, PatientSignupActivity.class);
        startActivity(intent);
    }

    public void openTypesOfDoctor(){
        Intent intent= new Intent(this, DoctorFindActivity.class);
        startActivity(intent);
    }
}
