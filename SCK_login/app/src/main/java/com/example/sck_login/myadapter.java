package com.example.sck_login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class myadapter extends FirebaseRecyclerAdapter<key,myadapter.myviewholder>
{
    public myadapter(@NonNull FirebaseRecyclerOptions<key> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull key model)
    {
        holder.mTitleText.setText(model.getKeyword());
        holder.mInfoText.setText(model.getDescription());
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list,parent,false);
        return new myviewholder(view);
    }


    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView mTitleText,mInfoText;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.titles);
            mInfoText = (TextView) itemView.findViewById(R.id.info);
        }
    }
}
