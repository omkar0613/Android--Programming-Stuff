package com.example.user.contact;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    MyDBHandler db;

    public static final String TAG="StatusActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void button_Click(View v) {
        if (v.getId() == R.id.id_Add) {
            Log.d(TAG, "Clicked Add Button");
            Intent intent = new Intent(MainActivity.this, AddContacts.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.id_Lookup) {

            Log.d(TAG, "Clicked Lookup Button");
    //        PhoneBook phn=new PhoneBook(this);
    //        phn.retrieve();
            Intent intent = new Intent(MainActivity.this, Lookup.class);
            startActivity(intent);
        }
    }


}
