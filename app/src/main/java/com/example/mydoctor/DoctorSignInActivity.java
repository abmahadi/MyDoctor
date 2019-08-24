package com.example.mydoctor;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class DoctorSignInActivity extends AppCompatActivity {
   private EditText emailDoctor,passwordDoctor;
   private Button signUpDoctor;

    private FirebaseAuth mAuth;

   private ProgressBar progressBar_doctorSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_in);

        mAuth = FirebaseAuth.getInstance();

        emailDoctor=findViewById(R.id.et_doctor_email);
        passwordDoctor=findViewById(R.id.et_doctor_password);
        signUpDoctor=findViewById(R.id.btn_doctor_signUp);
        progressBar_doctorSignUp=findViewById(R.id.progressBar_doctorSignUp);


        signUpDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DoctorRegister();




            }
        });
    }
    private void DoctorRegister() {
        String email= emailDoctor.getText().toString().trim();
        String password= passwordDoctor.getText().toString().trim();

        //checking the validity of the email

        if(email.isEmpty())
        {
            emailDoctor.setError("Enter an email address");
            emailDoctor.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailDoctor.setError("Enter a valid email address");
            emailDoctor.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            passwordDoctor.setError("Enter a password");
            passwordDoctor.requestFocus();
            return;
        }
        progressBar_doctorSignUp.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar_doctorSignUp.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(DoctorSignInActivity.this, "Register is successfull", Toast.LENGTH_SHORT).show();

                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(DoctorSignInActivity.this, "User is already Registered", Toast.LENGTH_SHORT).show();
                    }
                    else

                        Toast.makeText(DoctorSignInActivity.this, "Error :"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }

            }
        });


    }
}
