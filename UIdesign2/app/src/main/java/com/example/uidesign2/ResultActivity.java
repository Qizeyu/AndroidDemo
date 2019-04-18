package com.example.uidesign2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏运营商图标、电量
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView result = (TextView) findViewById(R.id.result);
        result.setText("从前一个页面传过来的内容如下：\n\n" + this.getIntent().getStringExtra("info"));


    }
}
