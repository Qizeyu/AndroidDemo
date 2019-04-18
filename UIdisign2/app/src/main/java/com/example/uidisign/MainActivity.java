package com.example.uidisign;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CheckBox> favs;
    private EditText username;
    private RadioGroup sex;
    private Spinner dept;
    private EditText tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.name1);
        //获取个组件对象，此处省略
        favs  = new ArrayList<CheckBox>();

        sex = (RadioGroup)findViewById(R.id.sex);
        tel = (EditText) findViewById(R.id.tel);
        dept =(Spinner) findViewById(R.id.dept);
        CheckBox book = (CheckBox) findViewById(R.id.book);
        CheckBox sport  = (CheckBox) findViewById(R.id.sport);
        CheckBox music = (CheckBox) findViewById(R.id.music);
        CheckBox movie = (CheckBox) findViewById(R.id.movie);

        favs.add(book);
        favs.add(music);
        favs.add(movie);

    }
    public boolean check()
    {
        if(username.getText().toString().equals(""))
        {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_LONG).show();
            return false;

        }
        return true;
    }
    public  String getSex()
    {
        RadioButton radiobutton = (RadioButton) findViewById(sex.getCheckedRadioButtonId());
        return radiobutton.getText().toString();
    }
    public String getFavorite()
    {
        String favo = "";
        for(CheckBox cb:favs)
        {
            if(cb.isChecked())
            {
                favo+=cb.getText().toString();
                favo+=",";
            }
        }
        if(!"".equals(favo))
            favo = favo.substring(0,favo.length() -1);
        else
            favo = "您未选择爱好!";
        return favo;
    }
    public void myclick(View view)
    {
        if(check())
        {
                StringBuilder sb = new StringBuilder();
                sb.append("用户名："+username.getText().toString()+"\n");
                sb.append("性别："+getSex()+"\n");
                sb.append("电话："+tel.getText().toString()+"\n");
                sb.append("部门："+dept.getSelectedItem().toString()+"\n");
                sb.append("爱好："+getFavorite()+"\n");
            Toast.makeText(this,sb.toString(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent();//将注册信息提交到ResultActivity页面
            intent.setClass(this,ResultActivity.class);
            intent.putExtra("info",sb.toString());
            this.startActivity(intent);
        }
    }

}
