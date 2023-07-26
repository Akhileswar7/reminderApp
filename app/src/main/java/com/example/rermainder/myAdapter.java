package com.example.rermainder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myviewholder> {
    ArrayList<model> dataholder = new ArrayList<model>();
    //array list to hold the reminders
    public myAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_remainder, parent, false);  //inflates the xml file in recyclerview
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") final int position) {
        holder.mid.setText(String.valueOf(dataholder.get(position).getId()));
        holder.mTitle.setText(dataholder.get(position).getTitle());                                 //Binds the single reminder objects to recycler view
        holder.mDate.setText(dataholder.get(position).getDate());
        holder.mTime.setText(dataholder.get(position).getTime());
    }
    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    class myviewholder extends RecyclerView.ViewHolder {

        TextView mTitle, mDate, mTime,mid;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.txtTitle);                               //holds the reference of the materials to show data in recyclerview
            mDate = (TextView) itemView.findViewById(R.id.txtDate);
            mTime = (TextView) itemView.findViewById(R.id.txtTime);
            mid=(TextView) itemView.findViewById(R.id.id);
        }
    }
}
