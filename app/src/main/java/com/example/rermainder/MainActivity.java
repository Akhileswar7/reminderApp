package com.example.rermainder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mCreateRem,delete_selected;
    RecyclerView mRecyclerview;
    ArrayList<model> dataholder = new ArrayList<model>();
    myAdapter adapter;
Cursor cursor;


List<Boolean> c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mCreateRem = (FloatingActionButton) findViewById(R.id.create_reminder);
        delete_selected=(FloatingActionButton)findViewById(R.id.del_selected); //Floating action button to change activity
        mCreateRem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), remainderActivity.class);
                startActivity(intent);                                                              //Starts the new activity to add Reminders
            }
        });
        delete_selected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),delete.class);
                startActivity(i);
            }
        });


        cursor = new dbmanager(getApplicationContext()).readallreminders();                  //Cursor To Load data From the database
        while (cursor.moveToNext()) {
            model model = new model(cursor.getInt(0),cursor.getString(1), cursor.getString(2), cursor.getString(3));
            dataholder.add(model);
        }

        adapter = new myAdapter(dataholder);
        mRecyclerview.setAdapter(adapter);                                                          //Binds the adapter with recyclerview

    }

    @Override
    public void onBackPressed() {
        finish();                                                                                   //Makes the user to exit form the app
        super.onBackPressed();

    }

    public RecyclerView get() {
        return mRecyclerview;
    }
}
