package org.bubulescu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {

    private boolean serviceOn = false;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        
        if (!serviceOn) {

            BackgroundTask backgroundTask = new BackgroundTask(this);
            backgroundTask.execute(5);
            serviceOn = true;
        } else {
            Toast.makeText(this, "Service already running!", Toast.LENGTH_SHORT).show();
        }
        
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service stopped..", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
