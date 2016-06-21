package com.example.user.driver;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    public static final String TAG = "StatusActivity";
    DatabaseHelper myDB;
    EditText custNumber;
    String customerNumber,nameToBePrinted;
    TextView name;
 //   private String package_name=com.example.user.buspasssystem;
Button verification,insert,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB=new DatabaseHelper(this);
        verification=(Button)findViewById(R.id.id_verify);
        insert=(Button)findViewById(R.id.id_Insert);
        data=(Button)findViewById(R.id.id_availableData);
        custNumber=(EditText)findViewById(R.id.id_custNumber);
        name=(TextView)findViewById(R.id.id_searchedName);
    }
    public void setNameToBePrinted(String nameToBePrinted) {
        this.nameToBePrinted = nameToBePrinted;

    }
public void button_verify(View v){
    if(v.getId()==R.id.id_verify){
        Log.d(TAG,"Verify button pressed");
        Intent intent=getPackageManager().getLaunchIntentForPackage("com.example.user.buspasssystem");
          startActivity(intent);
    }
}

 public void button_Data(View v){
        if(v.getId()==R.id.id_availableData){
            Log.d(TAG,"Available Data button pressed");
            customerNumber=custNumber.getText().toString();
            Log.d(TAG,customerNumber);
            //////// check once ///////////////
            DatabaseHelper myDB=new DatabaseHelper(this);
            myDB.getName(customerNumber);
            name.setText(DatabaseHelper.pname);
        }
    }

    public void button_Insert(View v){
        if(v.getId()==R.id.id_Insert){
Log.d(TAG,"Insert button pressed");
  /*          boolean isInserted1=myDB.insertData("14a4929","MADHAV");
            boolean isInserted2= myDB.insertData("37febd1","OMKAR");
            boolean isInserted3= myDB.insertData("15e6f62","MAYUR");
            boolean isInserted4= myDB.insertData("1675364","JOHN");
            boolean isInserted5= myDB.insertData("2ace323","KATE");
            boolean isInserted6= myDB.insertData("3f08270","MATTHEW");
            boolean isInserted7= myDB.insertData("20a9c72","SOHAN");
            boolean isInserted8= myDB.insertData("15791c4","KUNAL");*/
            boolean isInserted11= myDB.insertData("3b28772","BRIAN");
            if(isInserted11==true){
                Log.d(TAG,"Eleventh row Inserted");
            }
            else{
                Log.d(TAG,"Eleventh row not Inserted");
            }
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
    /*        if(isInserted1==true){
                Log.d(TAG,"First row Inserted");
            }
            else{
                Log.d(TAG,"First row not Inserted");
            }

            if(isInserted2==true){
                Log.d(TAG,"Second row Inserted");
            }
            else{
                Log.d(TAG,"Second row not Inserted");
            }

            if(isInserted3==true){
                Log.d(TAG,"Third row Inserted");
            }
            else{
                Log.d(TAG,"Third row not Inserted");
            }

            if(isInserted4==true){
                Log.d(TAG,"Fourth row Inserted");
            }
            else{
                Log.d(TAG,"Fourth row not Inserted");
            }

            if(isInserted5==true){
                Log.d(TAG,"Fifth row Inserted");
            }
            else{
                Log.d(TAG,"Fifth row not Inserted");
            }

            if(isInserted6==true){
                Log.d(TAG,"Sixth row Inserted");
            }
            else{
                Log.d(TAG,"Sixth row not Inserted");
            }

            if(isInserted7==true){
                Log.d(TAG,"Seventh row Inserted");
            }
            else{
                Log.d(TAG,"Seventh row not Inserted");
            }

            if(isInserted8==true){
                Log.d(TAG,"Eighth row Inserted");
            }
            else{
                Log.d(TAG,"Eighth row not Inserted");
            }*/
        }
    }
}
