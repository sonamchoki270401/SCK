package com.example.sck_login;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class keys extends RecyclerView.Adapter<keys.ViewHolder> {
    private ArrayList<key> mSportsData;
    private android.content.Context mContextText;

    keys(Context context, ArrayList<key> SportData){
        this.mSportsData = SportData;
        this.mContextText = context;
    }


//    public SportAdapter(MainActivity mainActivity, ArrayList<Sport> mSportsData) {
//
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContextText).inflate(R.layout.list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull keys.ViewHolder holder, int position) {
        //get current sport
        key currentSport = mSportsData.get(position);
        //populate the textview with data
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleText;
        private TextView mInfoText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitleText = (TextView) itemView.findViewById(R.id.titles);
            mInfoText = (TextView) itemView.findViewById(R.id.info);

        }

        public void bindTo(key currentSport) {
            Log.d("print",currentSport.getKeyword());
            Log.d("print",currentSport.getDescription());
            mTitleText.setText(currentSport.getKeyword());
            mInfoText.setText(currentSport.getDescription());
        }
    }
}

