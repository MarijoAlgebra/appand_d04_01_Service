package org.bubulescu.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    Handler handler = new Handler();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            int taskCount = intent.getIntExtra(MainActivity.TASK_COUNT_KEY, 0);
            for (int i = 0; i < taskCount; i++) {
                performLongTask();
                int progress = ((int) ((i + 1) / (double) taskCount * 100));
                showProgress(progress);
            }
        }
    }

    private void showProgress(final int progress) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                String msg = progress + "% completed";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performLongTask() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
