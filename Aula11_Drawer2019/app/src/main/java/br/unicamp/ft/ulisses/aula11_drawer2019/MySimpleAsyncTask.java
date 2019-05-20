package br.unicamp.ft.ulisses.aula11_drawer2019;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by ulisses on 9/27/17.
 */

public class MySimpleAsyncTask extends AsyncTask<String, Integer, Boolean> {

    TextView textView;
    Boolean  keepRunning;


    public MySimpleAsyncTask(TextView textView){
        this.textView = textView;
        keepRunning = true;
    }

    public void stopRunning(){
        keepRunning = false;
    }

    @Override
    protected void onPreExecute(){
        textView.append("Iniciado \n ");
    }

    @Override
    protected Boolean doInBackground(String... args){
        int count = 0;
        while (keepRunning){
            publishProgress(count);
            count++;
            if (args.length > 0) {
                Log.v("Background", args[0]);
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){}
        }
        return true;
    }

    @Override
    protected void onProgressUpdate(Integer... args){
        textView.append(args[0]+",");
    }

    @Override
    protected void onPostExecute(Boolean args){
        textView.append("finished");
    }
}
