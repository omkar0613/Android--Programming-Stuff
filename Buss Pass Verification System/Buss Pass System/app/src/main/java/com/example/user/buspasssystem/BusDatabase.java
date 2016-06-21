package com.example.user.buspasssystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ImageView;

public class BusDatabase {
 //   private static final String COLUMN_ID ="_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_PASSWORD = "_password";
    private static final String COLUMN_IMAGE = "_image";   // names of my column*/
    private static final String COLUMN_BARCODE = "_barcode";
    private static final String TABLE_PRODUCTS = "buspass";
    MyDBHandler dbHandler;
    YourInformation r=new YourInformation();
    public static final String TAG="StatusActivity";
    public SQLiteDatabase db;
    public static String pname,ppassword,pimage,pBarcode;
    public BusDatabase(Context context){
        Log.d(TAG, String.valueOf(context));
        Log.d(TAG,"Bus Database constr called while retrieving the data");
        dbHandler = new MyDBHandler(context);
        Log.d(TAG,"Come back from dbhandler");
    }
    @Override
    protected void finalize() throws Throwable {
        try {

            db.close();

        } catch (Exception ex) {
        }
        super.finalize();

    }
    public void insert(String name,String password,String image,String barcode){
        //     Log.d(TAG,"I am in addNumber method");
        this.db = dbHandler.getWritableDatabase();
 //       Log.d(TAG, "Value of new id is "+String.valueOf(id));
        ContentValues values=new ContentValues();
        //      Log.d(TAG,"I am in addNumber method part 2");
 //       values.put("_id",id);    // added
        values.put("_name",name);
        values.put("_password",password);
       values.put("_image",image);

        values.put("_barcode",barcode);
        Log.d(TAG, String.valueOf(values));

        db.insert("buspass", null, values);
        Log.d(TAG, "Data Inserted");
    }
    public Cursor retrieve(String name,String password){
        Log.d(TAG,"Inside Retieve");
        SQLiteDatabase SQ=dbHandler.getReadableDatabase();
        Log.d(TAG,"SQ is "+SQ);
        String[] columns={COLUMN_NAME,COLUMN_BARCODE,COLUMN_IMAGE,COLUMN_PASSWORD};
        Cursor CR=SQ.query(TABLE_PRODUCTS,columns,COLUMN_NAME+" ='"+name+"'"
                +"and "+COLUMN_PASSWORD+" ='"+password+"'",null,null,null,null);
        String result="";
     //   int iRow=CR.getColumnIndex(COLUMN_ID);
        int iImage=CR.getColumnIndex(COLUMN_IMAGE);
        int iName=CR.getColumnIndex(COLUMN_NAME);
        int iPassword=CR.getColumnIndex(COLUMN_PASSWORD);
        int iBarcode=CR.getColumnIndex(COLUMN_BARCODE);
        int i=1;
        for(CR.moveToFirst();!CR.isAfterLast();CR.moveToNext()){
            result=result+CR.getString(iName)+" "+CR.getString(iPassword)+" "
                    +CR.getString(iImage)+" "+CR.getString(iBarcode)+"\n";
            pname=CR.getString(iName);

              Log.d(TAG,pname);
            pBarcode=CR.getString(iBarcode);
                Log.d(TAG,pBarcode);
            if(i==1) {
                r.setNameToBePrinted(pname);
                r.setCustNumberToBePrinted(pBarcode);
       //         pname="";
       //         pBarcode="";
            }
            Log.d(TAG, result);
        }

        return CR;
    }
}
