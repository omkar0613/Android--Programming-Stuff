package com.example.user.buspasssystem;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    public static final String TAG="StatusActivity";
TextView enteredName,enteredPassword;
    String name,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enteredName= (TextView) findViewById(R.id.id_name);
        enteredPassword= (TextView) findViewById(R.id.id_pass);
    }

    public void button_signIn(View v){
        if(v.getId()==R.id.id_signIn){
            Log.d(TAG, "Clicked sign in button");
            name=enteredName.getText().toString();
            password=enteredPassword.getText().toString();
//            YourInformation.name.setText("");
//            YourInformation.customerNumber.setText("");
            Log.d(TAG, "Entered name is "+name);
            Log.d(TAG, "Entered Password is "+password);
            BusDatabase database=new BusDatabase(this);
database.retrieve(name,password);
            Intent intent = new Intent(MainActivity.this, YourInformation.class);
            startActivity(intent);
            Log.d(TAG,"Access comes here after YourInfo Screen?");
        }
    }

    public void button_signUp(View v){
        if(v.getId()==R.id.id_signUp){
            Log.d(TAG, "Clicked sign up button");
            Intent intent = new Intent(MainActivity.this, NewRegistration.class);
            startActivity(intent);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
