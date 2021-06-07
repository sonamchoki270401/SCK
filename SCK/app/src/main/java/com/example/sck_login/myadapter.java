package com.example.sck_login;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class myadapter extends FirebaseRecyclerAdapter<key,myadapter.myviewholder> {
    int report = 0;
    public myadapter(@NonNull FirebaseRecyclerOptions<key> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull key model) {
        holder.mTitleText.setText(model.getKeyword());
        holder.mInfoText.setText(model.getDescription());
        Log.d("img", model.getKeyword());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("yes","Report successfully");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("keyword");
                FirebaseDatabase.getInstance().getReference("keyword").child(model.getKeyword()).getRef().child("report").setValue(report);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        report = 1;
                        for (DataSnapshot snapshot1:snapshot.getChildren()){

                            if(snapshot1.getKey().equals(model.getKeyword())){
                                report = report+model.getReport();
                                if(report>2){
                                    /*FirebaseDatabase.getInstance().getReference("keyword").child("Nothing").setValue(new key("null","null",0));*/
                                    FirebaseDatabase.getInstance().getReference("keyword").child(model.getKeyword()).removeValue();
                                    //snapshot1.getRef().child(model.getKeyword()).setValue(new key("null","null",0));
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder  {
        TextView mTitleText, mInfoText;
        ImageView imageView;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageVieww);
            mTitleText = (TextView) itemView.findViewById(R.id.titles);
            mInfoText = (TextView) itemView.findViewById(R.id.info);

        }

        }
    }


