package com.example.rok.a0525;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity {
    Handler handler  =  new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String name2 = (String)msg.obj;
            e2.setText(name2);
        }
    };
    MyThread subThread = new MyThread();

    @Override
    protected void onDestroy() {

        subThread.subHandler.getLooper().quit();
    }

    class MyThread extends Thread{
        SubHandler subHandler = new SubHandler();

        @Override
        public void run() {
            Looper.prepare();
            Looper.loop();
        }
    }
    class SubHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            String name = (String)msg.obj;
            name ="Hello"+name;
            Message msg2 = Message.obtain();
            msg2.obj = name;
            subThread.subHandler.sendMessage(msg2);
        }
    }


    EditText e1,e2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e1  = (EditText)findViewById(R.id.editText);
        e2  = (EditText)findViewById(R.id.editText2);
        subThread.start();



    }
    public void onmyclick(View view) {
        String name = e1.getText().toString();
        Message msg = Message.obtain();
        msg.obj = name;
        subThread.subHandler.sendMessageDelayed(msg,3000);
    }



}
