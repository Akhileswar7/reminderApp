package com.example.rermainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper {
    private static String dbname = "reminderdb";                                                      //Table  name to store reminders in sqllite

    public dbmanager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {                                           //sql query to insert data in sqllite
        String query = "create table tbl_reminder(id int,title text,date text,time text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS tbl_reminder";                                         //sql query to check table with the same name or not
        sqLiteDatabase.execSQL(query);                                                              //executes the sql command
        onCreate(sqLiteDatabase);

    }

    public String addreminder(String id,String title, String date, String time) {
        SQLiteDatabase database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("title", title);                                                          //Inserts  data into sqllite database
        contentValues.put("date", date);
        contentValues.put("time", time);

        float result = database.insert("tbl_reminder", null, contentValues);    //returns -1 if data successfully inserts into database

        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }
    }

    public Cursor readallreminders() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from tbl_reminder order by id desc";                               //Sql query to  retrieve  data from the database
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }
    public Cursor getItems(String[] ids){
        SQLiteDatabase db = this.getWritableDatabase();
        String q=String.format("select date,time from tbl_reminder where id in (%S)");
        Cursor c=db.rawQuery(q,ids);
        return c;
    }
    public Cursor delete_selected(String[] ids) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(String.format("delete from tbl_reminder where id in (%S)",ids));
        String query = "select * from tbl_reminder order by id desc";                               //Sql query to  retrieve  data from the database
        Cursor cursor =db.rawQuery(query, null);
        db.close();
        return cursor;
    }

    public Cursor get() {
        SQLiteDatabase db = this.getWritableDatabase();
        String q=String.format("select max(id) from tbl_reminder");
        Cursor c=db.rawQuery(q,null);
        return c;
    }
}
