package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText Email, Password;
    Button sign_up;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Button SignIn;
    TextView forgotlink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.EmailAddress1);
        Password = findViewById(R.id.Password1);
        progressBar = findViewById(R.id.progressBar);
        sign_up = findViewById(R.id.SignUpButton);
       
        fAuth = FirebaseAuth.getInstance();


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

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
                progressBar.setVisibility(View.VISIBLE);
                // authenticate the user
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "LOGGED IN SUCCESSFULLY.", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Error!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }
        });


    }


    public void SignIn(View view) {
       Intent intent = new Intent (this, Registration.class);
       startActivity(intent);
    }

}
