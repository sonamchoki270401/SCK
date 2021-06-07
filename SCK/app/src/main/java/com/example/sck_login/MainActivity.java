package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.DialogInterface;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText Email, Password;
    Button sign_up;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    Button SignIn;
    TextView forgotlink, forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Email = findViewById(R.id.EmailAddress1);
        Password = findViewById(R.id.Password1);
        progressBar = findViewById(R.id.progressBar);
        sign_up = findViewById(R.id.SignUpButton);
        forgotpassword = findViewById(R.id.forgotpassword);
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

    public void forgotpassword(View view) {
        EditText resetmail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset Password");
        passwordResetDialog.setMessage("Enter your mail to reset password");
        passwordResetDialog.setView(resetmail);

        passwordResetDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
String mail = resetmail.getText().toString();
fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {
        Toast.makeText(MainActivity.this, "Reset link sent to your Email",Toast.LENGTH_SHORT).show();
    }
}).addOnFailureListener(new OnFailureListener() {
    @Override
    public void onFailure(@NonNull Exception e) {
        Toast.makeText(MainActivity.this,"Error!Reset link is not sent" + e.getMessage(),Toast.LENGTH_SHORT).show();
    }
});
            }
        });
        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        passwordResetDialog.create().show();





    }
}
