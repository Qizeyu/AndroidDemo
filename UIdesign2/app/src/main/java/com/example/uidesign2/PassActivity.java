package com.example.uidesign2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class PassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏运营商图标、电量
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);


    }
    public void onclick(View v)
    {
        Toast.makeText(this,"请查看您的邮箱确认",Toast.LENGTH_LONG).show();
    }
}
