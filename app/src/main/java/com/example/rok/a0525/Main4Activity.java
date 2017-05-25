package com.example.rok.a0525;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {
    TextView t1;
    ProgressBar p1;
    myTasks mytasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        t1 = (TextView)findViewById(R.id.text);
        p1 = (ProgressBar)findViewById(R.id.progressbar);
    }

    public void onmyclick(View view) {
        switch(view.getId()){
            case R.id.progress:
                mytasks = new myTasks();
                mytasks.execute();
                break;
            case R.id.stop:
                mytasks.cancel(true);
                mytasks =null;
                break;
        }
    }
    class myTasks extends AsyncTask<Integer,Integer,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p1.setProgress(0);
            t1.setTextColor(Color.RED);
            t1.setText("진행율 : 0%");
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for(int i = 1;i<101;i++){
                if(isCancelled() ==true){
                    return null;
                }
                try {
                    Thread.sleep(200);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            p1.setProgress(values[0]);
            t1.setTextColor(Color.RED);
            t1.setText("진행율"+values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            p1.setProgress(100);
            t1.setText("진행율 :100%");
            t1.setTextColor(Color.BLUE);
        }

        @Override
        protected void onCancelled() {
            t1.setText("취소");
            super.onCancelled();
        }
    }
}
