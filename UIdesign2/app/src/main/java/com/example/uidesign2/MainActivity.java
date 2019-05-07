package com.example.uidesign2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏运营商图标、电量
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.userName);
        password = findViewById(R.id.password);

    }
    public void login(View V)
    {
            if(check())
            {
                StringBuilder sb = new StringBuilder();
                sb.append("登陆成功！"+"\n");
                sb.append("用户名："+username.getText().toString()+"\n");
                sb.append("密码："+password.getText().toString()+"\n");
                Toast.makeText(this,sb.toString(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(this,ResultActivity.class);
                intent.putExtra("info",sb.toString());
                this.startActivity(intent);

            }
    }
    //忘记密码按钮事件
    public void forgetPass(View v)
    {

            Intent intent  = new Intent();
            intent.setClass(this,PassActivity.class);
            this.startActivity(intent);

    }
    //注册界面按钮事件
    public void register(View V)
    {
        Intent intent  = new Intent();
        intent.setClass(this,RegisterActivity.class);
        this.startActivity(intent);
    }
    public boolean check()         //检验是否合法
    {
        if(username.getText().toString().equals(""))
        {
            Toast.makeText(this,"用户名不能为空，请输入",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText().toString().equals(""))
        {
            Toast.makeText(this,"密码不能为空,请输入",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }
}
