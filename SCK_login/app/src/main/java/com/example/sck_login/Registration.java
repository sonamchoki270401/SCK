package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Registration extends AppCompatActivity {
 EditText Firstname, Lastname, Email, Password, Confirmpassword;
 Button Registerbtn;
 FirebaseAuth fAuth;

 ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Firstname = findViewById(R.id.firstname);
        Lastname = findViewById(R.id.lastname);
        Email = findViewById(R.id.EmailAddress);
        Password = findViewById(R.id.Password1);
        Confirmpassword = findViewById(R.id.confirmPassword);
        Registerbtn = findViewById(R.id.Registerbtn);

        fAuth = FirebaseAuth.getInstance();
        progressBar= findViewById(R.id.progressBar2);
//
//        if(fAuth.getCurrentUser() != null){
//            startActivity(new Intent(getApplicationContext(),MainActivity.class));
//            finish();
//        }
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String FirstName = Firstname.getText().toString();
                String LastName = Firstname.getText().toString();
                String ConfirmPasword = Confirmpassword.getText().toString().trim();

                if (TextUtils.isEmpty(FirstName)) {
                    Firstname.setError("Firstname is required");
                    return;
                }
                if (TextUtils.isEmpty(LastName)) {
                    Lastname.setError("Firstname is required");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Password.setError("Password is required");
                    return;
                }
                if(password.length()<6) {
                    Password.setError("Password must be of equal to or more than 6 characters");
                }
                if (TextUtils.isEmpty(ConfirmPasword)){
                    Confirmpassword.setError("Enter your confirmation password");
                    return;
                }
                if (!ConfirmPasword.equals(password)){
                    Toast.makeText(Registration.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //register the user in firebase
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(Registration.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    public void logo(View view) {
        Intent logo = new Intent(this,MainActivity.class);
        startActivity(logo);
    }
}