package com.example.rok.a0525;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            t1.setText("숫자 : "+i);
        }
    };

    int i ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 =  (TextView)findViewById(R.id.text);


    }

    public void onmyclick(View view) {
        switch (view.getId()){
            case R.id.button:
                Mythread th = new Mythread();
                th.start();

                break;


        }
    }

     class Mythread extends Thread{

        @Override
        public void run(){
            super.run();
            for(i =1;i<11;i++){
                try {
                    Thread.sleep(1000);
                    Message msg = mhandler.obtainMessage();
                    mhandler.sendMessage(msg);
//                    mhandler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            t1.setText("숫자 :"+i);
//                        }
//                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

    }
}
