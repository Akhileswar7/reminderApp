package com.example.rermainder;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class delete extends Activity {
    TextView id;
    String[] ids;
    RecyclerView mRecyclerview;
    myAdapter adapter;
    ArrayList<model> dataholder = new ArrayList<model>();
    //array list to hold the reminders
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletion);
        id=(TextView) findViewById(R.id.remid);
    }

    public void remove(View view) {
        ids=id.getText().toString().split(",");
        new dbmanager(getApplicationContext()).delete_selected(ids);
        adapter = new myAdapter(dataholder);
        mRecyclerview =new MainActivity().get();
        mRecyclerview.setAdapter(adapter);
    }
}
