package com.example.class6;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView iv  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        //开启一个线程从网络加载图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                //可修改为其他网络的图片地址
                final Bitmap bm  =getPic("https://i.loli.net/2019/05/20/5ce21829b949c78220.jpg");
                try{
                    Thread.sleep(2000);  //睡眠两秒
                }
                catch ( Exception e)
                {
                    e.printStackTrace();
                }
                //再开始一个线程设置从网络下载图片
                iv.post(new Runnable() {
                    @Override
                    public void run() {
                        //请在此位置设置下载的图片显示
                        iv.setImageBitmap(bm);
                    }
                });
            }
        }).start();
    }
    private Bitmap getPic(String path)
    {
        Bitmap bm  = null;
        try{
            URL url = new URL(path);
            URLConnection conn  =  url.openConnection();
            conn.connect();
            InputStream is  = conn.getInputStream();
            bm = BitmapFactory.decodeStream(is);


        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return bm;
    }
    //启动第二个界面
    public void add(View v)
    {
        Intent intent = new Intent();
        intent.setClass(this,MyThread.class);
        startActivity(intent);
    }
}
