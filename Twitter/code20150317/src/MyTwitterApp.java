package com.ee5453.mytwitter;

import android.app.Application;
import android.util.Log;

import winterwell.jtwitter.Twitter;

/**
 * Created by kkx358 on 3/17/2015.
 */
public class MyTwitterApp extends Application {
    static String TAG="MyTwitterApp";
    Twitter twitter;
    @Override
    public void onCreate() {
        super.onCreate();
        Twitter twitter = new Twitter("student","password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        Log.d(TAG,"onCreate");
    }
}
