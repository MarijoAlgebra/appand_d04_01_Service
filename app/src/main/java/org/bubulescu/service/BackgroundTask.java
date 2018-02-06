package org.bubulescu.service;

import android.app.Service;
import android.os.AsyncTask;
import android.widget.Toast;


public class BackgroundTask extends AsyncTask<Integer, Integer, String> {

    private Service service;

    public BackgroundTask(Service service) {
        this.service = service;
    }

    @Override
    protected String doInBackground(Integer... params) {

        int taskCount = params[0];

        for (int i = 0; i < taskCount; i++) {
            performLongTask();
            // publishProgress(i + 1);
            publishProgress((int)((i + 1)/(double)taskCount * 100));
        }
        String resultMsg = taskCount + " zadataka izvršeno";
        return resultMsg;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        String updateMsg = values[0] + "% izvršeno";
        Toast.makeText(service, updateMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String resultMsg) {
        Toast.makeText(service, resultMsg, Toast.LENGTH_SHORT).show();
        service.stopSelf();
    }

    private void performLongTask() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
