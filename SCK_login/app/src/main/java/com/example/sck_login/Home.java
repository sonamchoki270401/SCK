package com.example.sck_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
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

import static com.example.sck_login.R.menu.menu;

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

        adapter=new myadapter(options);
        recyclerView.setAdapter(adapter);

//        keys = new keys(this, sports);
//        recyclerView.setAdapter(keys);
//        intitiazationData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    //fetch data
//    private void intitiazationData() {
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("keyword");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("try",reference.toString());
//                sports.clear();
//                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
//                    key infos = snapshot1.getValue(key.class);
//                    sports.add(infos);
//                }
//                keys.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.search);

        SearchView searchView =(SearchView)item.getActionView();

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
        return super.onCreateOptionsMenu(menu);
    }
//
private void processsearch(String s)
{
    FirebaseRecyclerOptions<key> options =
            new FirebaseRecyclerOptions.Builder<key>()
                    .setQuery(FirebaseDatabase.getInstance().getReference().child("keyword").orderByChild("keyword").startAt(s).endAt(s+"\uf8ff"), key.class)
                    .build();

    adapter=new myadapter(options);
    adapter.startListening();
    recyclerView.setAdapter(adapter);

}


//    logout
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.exit:
//                mFirebaseAuth.signOut();
//                Toast.makeText(Home.this, "Logged out successfully", Toast.LENGTH_SHORT).show();
//        }
//
//        return true;
//    }




    public void Addcomment(View view) {

        Intent intent = new Intent(this, AddPost.class);
        startActivity(intent);

    }


}