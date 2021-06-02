package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Home extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private RecyclerView recyclerView;
    private ArrayList<key> sports;
    private keys keys;

    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sports = new ArrayList<>();

        FirebaseRecyclerOptions<key> options =
                new FirebaseRecyclerOptions.Builder<key>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("keyword"), key.class)
                        .build();

        adapter = new myadapter(options);
        recyclerView.setAdapter(adapter);
        Log.d("img", "onCreate");

        //Search();

    }

    @Override
    protected void onStart() {
        Log.d("img", "onStart");
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        Log.d("img", "onStop");
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        Log.d("img", "onCreateOptionsMenu");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("img","onCreateOptionsMenu");
                processsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("img","onCreateOptionsMenu");
                processsearch(newText);
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<key> options =
                new FirebaseRecyclerOptions.Builder<key>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("keyword").orderByChild("keyword").startAt(s).endAt(s + "\uf8ff"), key.class)
                        .build();

        adapter = new myadapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }

    public void Addcomment(View view) {

        Intent intent = new Intent(this, AddPost.class);
        startActivity(intent);

    }

    public void logout(MenuItem item) {

        Intent intent = new Intent(this, MainActivity.class);
        Toast.makeText(Home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
        startActivity(intent);

    }

    /*public void Search() {
        SearchView searchView = findViewById(R.id.search);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                processsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                processsearch(newText);
                return false;
            }

        });
    }*/

}