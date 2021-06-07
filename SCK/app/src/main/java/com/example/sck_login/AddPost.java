package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.EventLogTags;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AddPost extends AppCompatActivity {
    private EditText Keyword, Description;
    Button Post;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        Keyword = findViewById(R.id.keyword);
        Description = findViewById(R.id.description);
        Post = findViewById(R.id.post);
      //  Keyword.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        Description.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        fAuth = FirebaseAuth.getInstance();
    }

    public void post(View view) {
        String keyword = Keyword.getText().toString().trim();
        String description = Description.getText().toString().trim();

        if (TextUtils.isEmpty(keyword)) {
            Keyword.setError("Keyword is required");
            return;
        }
        if (TextUtils.isEmpty(description)) {
            Description.setError("Description is required");
            return;
        }
        add(keyword, description);

        Intent intent = new Intent(this, Home.class);
        startActivity(intent);

    }

    public void add(String keyword, String description) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("keyword").child(keyword);
        key obj = new key(keyword, description, 0);
        dR.setValue(obj);
        Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show();

    }

    public void logo(View view) {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    /*class key {
        public String keyword, description;

        public key(String keyword, String description, int i) { }

        public key(String keyword, String description) {
            this.keyword = keyword;
            this.description = description;
        }

        void setKeyword(String keyword){
            this.keyword = keyword;
        }

        void setDescription(String description){
            this.description = description;
        }

        String getKeyword(){
            return keyword;
        }

        String getDescription(){
            return description;
        }

    }*/


}