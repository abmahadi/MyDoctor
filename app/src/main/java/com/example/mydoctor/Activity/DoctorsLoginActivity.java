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

public class DoctorsLoginActivity extends AppCompatActivity {

    TextView doctorSignIn;

    EditText emailDoctorLogin,passwordDoctorLogin;
    Button doctorLogin;

    ProgressBar progressBar_doctorLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_login);

        mAuth = FirebaseAuth.getInstance();

        doctorSignIn=findViewById(R.id.txt_doctor_signIn);
        emailDoctorLogin=findViewById(R.id.et_doctor_email_login);
        passwordDoctorLogin=findViewById(R.id.et_doctor_password_login);
        progressBar_doctorLogin=findViewById(R.id.progressBar_doctorLogin);
        doctorLogin=findViewById(R.id.btn_doctorLogin);

        doctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                DoctorLogin();


            }
        });


        doctorSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoctorSignInActivity();
            }
        });
    }

    private void openDoctorSignInActivity() {
        Intent intent =new Intent(this, DoctorSignInActivity.class);
        startActivity(intent);
    }
    public void openDoctorInfirmationEditActivity(){
        Intent intent= new Intent(this, DoctorsMenuActivity.class);
        startActivity(intent);
    }

    private void DoctorLogin() {
        String id=emailDoctorLogin.getText().toString().trim();
        String pass= passwordDoctorLogin.getText().toString().trim();

        if(id.isEmpty())
        {
            emailDoctorLogin.setError("Enter an email address");
            emailDoctorLogin.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(id).matches())
        {
            emailDoctorLogin.setError("Enter a valid email address");
            emailDoctorLogin.requestFocus();
            return;
        }

        //checking the validity of the password
        if(pass.isEmpty())
        {
            passwordDoctorLogin.setError("Enter a password");
            passwordDoctorLogin.requestFocus();
            return;
        }

        progressBar_doctorLogin.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(id,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar_doctorLogin.setVisibility(View.GONE);

                if(task.isSuccessful())
                {

                    openDoctorInfirmationEditActivity();
                }
                else
                {
                    Toast.makeText(DoctorsLoginActivity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
