package com.ee5453.mytwitter;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.List;

import winterwell.jtwitter.Twitter;

public class StatusFetchService extends Service {
    static final String TAG = "StatusFetchService";
    public StatusFetchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      //  throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"Service is being started");
        Twitter twitter = new Twitter("student","password");
        twitter.setAPIRootUrl("http://yamba.marakana.com/api");
        List<Twitter.Status> statuses = twitter.getPublicTimeline();
        for (Twitter.Status status:statuses)
            Log.d(TAG,status.getUser().getStatus().toString());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Service is being destroyed");
    }
}
