package com.example.class6;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.util.Random;


public class MyThread extends AppCompatActivity implements Runnable{
    private ImageView iv = null;
    private Handler handler = null;
    private  int[] path = {R.drawable.ing,R.drawable.ic_launcher_background};
    private Thread t;
    @SuppressLint("handlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_thread);
       iv = (ImageView) findViewById(R.id.iv1);
       t = new Thread( this);
       t.start();
       handler = new Handler(){
           @Override
           public  void  handleMessage(Message msg)  {
               if(msg.what == 0x111)
               {
                   iv.setImageResource(path[msg.arg1]);
                   super.handleMessage(msg);
               }


           }
       };

    }
    @Override
    public void run(){
        int index = 0;
        while(!Thread.currentThread().isInterrupted()){
            index = new Random().nextInt(path.length);
            Message  msg = handler.obtainMessage();
            msg.arg1 = index;
            msg.what = 0x111;
            handler.sendMessage(msg);
            try{
                Thread.sleep(2000);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       t.interrupt();
        //请在此关闭线程
    }


}
