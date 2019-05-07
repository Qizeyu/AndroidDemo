package com.example.class5;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class AddItemActivity extends AppCompatActivity {
    private EditText date,startTime,endTime,item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);
        date = (EditText) findViewById(R.id.date);
        startTime = (EditText) findViewById(R.id.startTime);
        endTime = (EditText) findViewById(R.id.endTime);
        item = (EditText) findViewById(R.id.item);

    }
    //通过日期对话框选择日期
    public void chooseDate(View view){
        DatePickerDialog datepd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker dp, int year, int month, int day) {

                date.setText(year+"-"+(month+1)+"-"+day);           //写入到日期框
            }
        },2019,5,20);                       //默认时间
        datepd.setMessage("请选择日期");
        datepd.show();

    }
    //通过时间对话框选择开始时间
    public void chooseStartTime(View view){
        TimePickerDialog timepd  = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String t = "0";
                t = t+Integer.toString(minute);
                if(minute<10)
                    startTime.setText(hourOfDay+":"+t);
                else

                startTime.setText(hourOfDay+":"+minute);
            }
        },10,0,true);//设置时间对话框，并确定默认时间
        timepd.setMessage("请选择开始时间");
        timepd.show();
    }
    //通过时间对话框选择结束时间
    public void chooseEndTime(View view){
        TimePickerDialog timepd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String t = "0";
                t = t+Integer.toString(minute);
                if(minute<10)
                    endTime.setText(hourOfDay+":"+t);
                else

                    endTime.setText(hourOfDay+":"+minute);

            }
        },12,0,true);
        timepd.setMessage("请选择结束时间");
        timepd.show();
    }
    //保存活动消息
    public void save(View view){
        StringBuilder result = new StringBuilder();
        result.append(date.getText().toString()+" ");
        result.append(startTime.getText().toString()+"-");
        result.append(endTime.getText().toString()+" ");
        result.append(item.getText().toString());
        try{
            //以追加的方式保存信息到item文件中
            FileOutputStream out = openFileOutput("item",MODE_APPEND);
            PrintStream ps = new PrintStream(out);
            ps.println(result.toString());
            ps.close();
            out.close();
            Toast.makeText(this,"保存完毕！",Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            setResult(0x111,intent);//设置结果，返回值，用于MainActivity的onActivityResult接收
            finish();           //关闭当前页面，返回前页
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
