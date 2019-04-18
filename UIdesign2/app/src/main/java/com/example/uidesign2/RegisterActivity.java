package com.example.uidesign2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class RegisterActivity extends AppCompatActivity {
    private EditText username ;
    private EditText password ;
    private EditText mpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);  //隐藏标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);//隐藏运营商图标、电量
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.register_name);
        password = findViewById(R.id.register_password);
        mpassword= findViewById(R.id.register_make_sure_password);


    }
//    public void onclick(View v)
////    {
////            if(check())
////            {
////
////                Map<String, String> map = new HashMap<String, String>();
////                System.out.println("asd");
////               // Toast.makeText(this,"asd",Toast.LENGTH_LONG).show();
////                map.put(username.getText().toString(),password.getText().toString());
////                try {
//////                    FileOutputStream outStream = new FileOutputStream("1.txt");
//////                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outStream);
//////                    objectOutputStream.writeObject(map);
//////                    outStream.close();
////
////                    Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();
////                } catch (FileNotFoundException e) {
////                    e.printStackTrace();
////                }catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
////
////
////
////
////            }
////    }
    public void onclick(View v)
    {
        if(check())
        {
            if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            {
                //获得sd卡的路径
                File sdCardDir = Environment.getExternalStorageDirectory();
                String t = username.getText().toString();
                String t2 = password.getText().toString();
                try{
                    FileOutputStream out = new FileOutputStream(sdCardDir.getCanonicalPath()+"/"+"register");
                    PrintStream ps = new PrintStream(out);
                    ps.println(t+","+t2);
                    ps.close();
                    Toast.makeText(this,"注册成功",Toast.LENGTH_LONG).show();

                }catch (Exception e)
                {
                    e.printStackTrace();;
                }
            }
        }
    }

    public void cancel(View V)
    {
            username.setText("");
            password.setText("");
            mpassword.setText("");
    }
    public boolean check()
    {
        String a = mpassword.getText().toString();
        if(username.getText().toString().equals(""))
        {
            Toast.makeText(this,"用户名不能为空，请重新输入",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText().toString().equals(""))
        {
            Toast.makeText(this,"密码不能为空，请重新输入",Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.getText().toString().length()<6)
        {
            Toast.makeText(this,"密码不得小于6位",Toast.LENGTH_LONG).show();
            return false;

        }
        if(!password.getText().toString().equals(a))
            {
                Toast.makeText(this,"两次输入的密码不一致!,请重新输入",Toast.LENGTH_LONG).show();
                return false;
            }
           return true;


    }
}
