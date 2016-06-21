package com.ee5453.mytwitter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import winterwell.jtwitter.Twitter;
import winterwell.jtwitter.TwitterException;


public class StatusActivity extends ActionBarActivity{
        //implements View.OnClickListener {

    public static final String TAG = "StatusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        //Button update_button;
        //update_button = (Button) findViewById(R.id.statusAct_updateButton);
      //  update_button.setOnClickListener(this);
    }

    public void buttonClicked(View v){
        EditText status;
        status = (EditText) findViewById(R.id.statusAct_statusText);

        if (v.getId()==R.id.statusAct_updateButton){
            Log.d(TAG,status.getText().toString());

            try {
                Twitter twitter = new Twitter("student","password");
                twitter.setAPIRootUrl("http://yamba.marakana.com/api");
                twitter.setStatus(status.getText().toString());
            } catch (TwitterException e) {
                //e.printStackTrace();
                Log.e(TAG,e.getMessage());
            }
        }
        else if (v.getId()==R.id.statusAct_resetButton){
            status.setText("");
        }
    }
/*
    @Override
    public void onClick(View v) {
        //if(v.getId()==R.id.statusAct_updateButton){
            EditText status;
            status = (EditText) findViewById(R.id.statusAct_statusText);
            Log.d(TAG,status.getText().toString());
        //}

    }
*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_status, menu);
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
