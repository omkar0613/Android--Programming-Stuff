package com.example.user.contact;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddContacts extends ActionBarActivity {
    public static final String TAG="StatusActivity";
    EditText Name,Phone,Email;
    MyDBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        Name=(EditText)findViewById(R.id.id_EditName);
        Phone=(EditText)findViewById(R.id.id_EditPhone);
        Email=(EditText)findViewById(R.id.id_EditEmail);
    }


    public void button_Save(View v) {
        if (v.getId() == R.id.id_Save) {
            PhoneBook phoneBook=new PhoneBook(this);
            String name=Name.getText().toString();
            String phone=Phone.getText().toString();
            String email=Email.getText().toString();
            phoneBook.insert(name,phone,email);
            Log.d(TAG, "Clicked Save Button from Add-Contacts page");
            Toast.makeText(this, "Data Saved in Phone Memory", Toast.LENGTH_LONG).show();
            Name.setText("");
            Phone.setText("");
            Email.setText("");

            finish();
        }
    }
}
