package com.example.user.driver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String TAG = "StatusActivity";
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="driver.db";
    public static final String TABLE_NAME="travellers_data";
    public static final String COLUMN_BARCODE="_barcode";
    public static final String COLUMN_NAME="_name";
    MainActivity r=new MainActivity();
    DatabaseHelper databaseHelper;
    public static String pname;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_BARCODE + " TEXT,"+ COLUMN_NAME + " TEXT" + ");";
        Log.d(TAG, "onCreate: " + sql);
        db.execSQL(sql);
        Log.d(TAG,"Query executed");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        Log.d(TAG, " Inside onUpgrade ");
        String sql = String.format("drop table if exists %s",TABLE_NAME);
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean insertData(String barcode,String name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_BARCODE,barcode);
        contentValues.put(COLUMN_NAME,name);
       long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor getName(String customer){
         SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={COLUMN_NAME,COLUMN_BARCODE};
Cursor CR=db.query(TABLE_NAME,columns,COLUMN_BARCODE+" ='"+customer+"'",null,null,null,null);
        Log.d(TAG, String.valueOf(CR));
        String result="";
        int iName=CR.getColumnIndex(COLUMN_NAME);
        int iBarcode=CR.getColumnIndex(COLUMN_BARCODE);
        Log.d(TAG,"Column no is "+iName);
        Log.d(TAG,"Column no is "+iBarcode);

        for(CR.moveToFirst();!CR.isAfterLast();CR.moveToNext()) {
            result = result + CR.getString(iName) + " " + CR.getString(iBarcode) + "\n";
            Log.d(TAG, "Final value of result is " + result);
            pname = CR.getString(iName);
            Log.d(TAG, "Name is " + pname);
        }
        return CR;
    }
}
