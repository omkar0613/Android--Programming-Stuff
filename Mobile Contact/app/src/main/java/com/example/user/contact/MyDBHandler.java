package com.example.user.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper{

    public static final String TAG = "StatusActivity";
    private static final int DATABASE_VERSION = 3;  // consists of 1 table with name,ph and email
    private static final String DATABASE_NAME = "contacts.db"; // This is a name of the file where all data will be stored, db extension
    //tells android that the database is stored.
    private static final String TABLE_PRODUCTS = "contacts"; // name of my table
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_NUMBER = "_number";
    private static final String COLUMN_EMAIL = "_email";   // names of my column*/
    public SQLiteDatabase db;
    public MyDBHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_PRODUCTS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_NUMBER + " TEXT," + COLUMN_EMAIL + " TEXT" + ");";
        Log.d(TAG,"onCreate: "+sql);
        db.execSQL(sql);
        Log.d(TAG,"Query executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        String sql = String.format("drop table if exists %s",TABLE_PRODUCTS);
        db.execSQL(sql);
        onCreate(db);
    }

}
