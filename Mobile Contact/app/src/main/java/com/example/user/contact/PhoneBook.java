package com.example.user.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class PhoneBook {
    private static final String COLUMN_ID ="_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_NUMBER = "_number";
    private static final String COLUMN_EMAIL = "_email";   // names of my column*/
    private static final String TABLE_PRODUCTS = "contacts";
    MyDBHandler dbHandler;
  //  PhoneBook phonebook;
    Results r=new Results();
    public static final String TAG="StatusActivity";
    public SQLiteDatabase db;
    public static String pname,pphone,pemail;
    public PhoneBook(Context context){
        Log.d(TAG, String.valueOf(context));

        dbHandler = new MyDBHandler(context);

     //   this.db = dbHandler.getWritableDatabase();
    }
    @Override
    protected void finalize() throws Throwable {
        try {

            db.close();

        } catch (Exception ex) {
        }
        super.finalize();

    }
    public void insert(String name,String number,String email){
   //     Log.d(TAG,"I am in addNumber method");
        this.db = dbHandler.getWritableDatabase();
        ContentValues values=new ContentValues();
  //      Log.d(TAG,"I am in addNumber method part 2");
        values.put("_name",name);
        values.put("_number",number);
        values.put("_email",email);
 //       Log.d(TAG, String.valueOf(values));

        db.insert("contacts", null, values);
        Log.d(TAG, "Data Inserted");
    }

    public Cursor retrieve(String name, String phone, String email){
     //   Log.d(TAG,"I am in retrieve method");
     //   Log.d(TAG,name);
    //    Log.d(TAG,phone);
    //    Log.d(TAG,email);
        SQLiteDatabase SQ=dbHandler.getReadableDatabase();
    //    Log.d(TAG, String.valueOf(SQ));
        String[] columns={COLUMN_ID,COLUMN_NAME,COLUMN_NUMBER,COLUMN_EMAIL};
        Cursor CR = SQ.query(TABLE_PRODUCTS,columns,COLUMN_NAME+" ='"+name+"'"
                +"OR "+COLUMN_NUMBER+" ='"+phone+"'"
                +"OR "+COLUMN_EMAIL+" ='"+email+"'",null,null,null,null);
        String result="";

        int iRow=CR.getColumnIndex(COLUMN_ID);
        int iName=CR.getColumnIndex(COLUMN_NAME);
        int iNumber=CR.getColumnIndex(COLUMN_NUMBER);
        int iEmail=CR.getColumnIndex(COLUMN_EMAIL);
        int i=1;
        for(CR.moveToFirst();!CR.isAfterLast();CR.moveToNext()){
            result=result+CR.getString(iRow)+" "+CR.getString(iName)+" "+CR.getString(iNumber)+" "+CR.getString(iEmail)+"\n";
            pname=CR.getString(iName);

         //   Log.d(TAG,pname);
            pphone=CR.getString(iNumber);
        //    Log.d(TAG,pphone);
            pemail=CR.getString(iEmail);
            if(i==1) {
                r.setNameToBePrinted(pname);
                r.setPhoneToBePrinted(pphone);
                r.setEmailToBePrinted(pemail);
            }

        //    i++;
       //     Log.d(TAG, pemail);
            Log.d(TAG, result);
        }

  //      Log.d(TAG, String.valueOf(CR));
        return CR;
    }

}
