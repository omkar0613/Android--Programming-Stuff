package com.example.user.contact;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Lookup extends ActionBarActivity {
    public static final String TAG="StatusActivity";
       EditText enteredName,enteredPhone,enteredEmail;
   String name,phone,email;
    public static String a,b,c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookup);
        enteredName=(EditText)findViewById(R.id.id_EditLookupName);
        enteredPhone=(EditText)findViewById(R.id.id_LookupEditPhone);
        enteredEmail=(EditText)findViewById(R.id.id_EditLookupEmail);


    }
    public void button_Search(View v) {
        if (v.getId() == R.id.id_Search) {
            Log.d(TAG, "Clicked Search button from lookup page");
            name= enteredName.getText().toString();
            phone= enteredPhone.getText().toString();
            email= enteredEmail.getText().toString();
            PhoneBook phn=new PhoneBook(this);
            phn.retrieve(name,phone,email);
            Intent intent = new Intent(Lookup.this, Results.class);
            startActivity(intent);
        }

    }
}
