package com.example.user.display_info;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    public static final String TAG="Message";
    public static  int count=0;
    public static int startcount=0;
    public static int restartcount=0;
    public static int resumecount=0;
    public static int pausecount=0;
    public static int stopcount=0;
    public static int destroycount=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count++;

        setContentView(R.layout.activity_main);
        if(count>0) {
            TextView tv = (TextView) findViewById(R.id.abc);
            tv.setText("App Created "+count+" times");
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        startcount++;

        if(startcount>0) {
            TextView tv = (TextView) findViewById(R.id.def);
            tv.setText("App Started "+startcount+" times");
        }
    }

@Override
protected void onRestart(){
    super.onRestart();
    restartcount++;
    if(restartcount>0) {
        TextView tv = (TextView) findViewById(R.id.ghi);
        tv.setText("App Restarted "+restartcount+" times");
    }
}

    @Override
    protected void onResume(){
        super.onResume();
        resumecount++;

        if(resumecount>0) {
            TextView tv = (TextView) findViewById(R.id.jkl);
            tv.setText("App Resumed "+resumecount+" times");
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        pausecount++;

        if(pausecount>0) {
            TextView tv = (TextView) findViewById(R.id.mno);
            tv.setText("App Paused "+pausecount+" times");
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        stopcount++;

        if(stopcount>0) {
            TextView tv = (TextView) findViewById(R.id.pqr);
            tv.setText("App Stopped "+stopcount+" times");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        destroycount++;

        if(destroycount>1) {
            TextView tv = (TextView) findViewById(R.id.str);
            tv.setText("App Destroyed "+stopcount+" times");
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
