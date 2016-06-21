package com.example.user.contact;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class Results extends ActionBarActivity {
    TextView name, phone, email;
  //  Results r;
    String nameToBePrinted,phoneToBePrinted,emailToBePrinted;
    public static final String TAG = "StatusActivity";


public String ab,cd,ef;



    public String getNameToBePrinted() {
  //      Log.d(TAG, "In getNameToBePrinted " +nameToBePrinted);
        return nameToBePrinted;
    }

    public void setNameToBePrinted(String nameToBePrinted) {
        this.nameToBePrinted = nameToBePrinted;
      //  ab=nameToBePrinted;
  //      Log.d(TAG, "In setNameToBeMethod " +this.nameToBePrinted);
  //      Log.d(TAG, "In setNameToBeMethod " +ab);
    }

    public String getPhoneToBePrinted() {
        return phoneToBePrinted;
    }

    public void setPhoneToBePrinted(String a) {
        this.phoneToBePrinted = phoneToBePrinted;
        cd=a;
  //      Log.d(TAG, "In setPhoneToBeMethod " +this.phoneToBePrinted);
  //      Log.d(TAG, "In setPhoneToBeMethod " +cd);
    }

    public String getEmailToBePrinted() {
        return emailToBePrinted;
    }

    public void setEmailToBePrinted(String emailToBePrinted) {
        this.emailToBePrinted = emailToBePrinted;
   //     ef=emailToBePrinted;
   //     Log.d(TAG, "In setEmailToBeMethod " +this.emailToBePrinted);
   //     Log.d(TAG, "In setEmailToBeMethod tried diff one" +ef);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


       name= (TextView) findViewById(R.id.id_SearchedName);
        phone= (TextView) findViewById(R.id.id_SearchedNumber);
        email= (TextView) findViewById(R.id.id_SearchedEmail);

        Log.d(TAG,"I am in Results onCreate");

 //
    name.setText(PhoneBook.pname);
    phone.setText(PhoneBook.pphone);
    email.setText(PhoneBook.pemail);
}
    public void button_Call(View v) {
        if (v.getId() == R.id.id_Call) {
            Intent intent = new Intent(Results.this, DialerApp.class);
            startActivity(intent);
        }
    }
    public void button_Email(View v){
        if (v.getId() == R.id.id_Email) {
            Intent intent = new Intent(Results.this, EmailApp.class);
            startActivity(intent);
        }
    }
}
