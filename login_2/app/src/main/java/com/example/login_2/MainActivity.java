package com.example.login_2;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.Window;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    public void Clear(View v)
    {
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
                username.setText("");
                password.setText("");
    }

    public void Login(View v)
    {

        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);

        final SharedPreferences sp = getSharedPreferences("SPShare",MODE_PRIVATE);
         final SharedPreferences.Editor editor = sp.edit();
            String k = username.getText().toString();
            String value = password.getText().toString();
            if(k.equals("")||value.equals(""))
            {
                Toast.makeText(MainActivity.this,"用户名/密码不能为空，请重新输入！",Toast.LENGTH_LONG).show();
            }
            editor.putString(k,value);
            editor.commit();
            Toast.makeText(MainActivity.this,"登陆成功！",Toast.LENGTH_LONG).show();
    }
}
