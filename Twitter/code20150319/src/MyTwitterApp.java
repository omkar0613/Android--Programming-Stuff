package com.ee5453.mytwitter;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import winterwell.jtwitter.Twitter;

/**
 * Created by kkx358 on 3/17/2015.
 */
public class MyTwitterApp extends Application {
    static String TAG="MyTwitterApp";
    private Twitter twitter;
    private int delay;
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String user = sp.getString("user_name","student");
        String password = sp.getString("password","password");
        twitter = new Twitter(user,password);
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        Log.d(TAG,"onCreate");
    }

    Twitter getTwitterObject(){
        return twitter;
    }

    public int getDelay(){
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String delayString = sp.getString("sync_frequency","10");
        delay = Integer.parseInt(delayString);
        Log.d(TAG,delayString);
        return delay;
    }
}
