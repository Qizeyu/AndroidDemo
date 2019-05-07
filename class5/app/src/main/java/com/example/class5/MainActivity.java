package com.example.class5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView itemlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemlist = (ListView) findViewById(R.id.listview);
        getItemList();
    }
    //该方法是其他子Activity回到主Activity时，有可能时
    // 返回一些子模块完成的数据交给主Activity处理。
    // 这样的数据交流就要用到回调函数onActivityResult()。
//    requestCode 请求码，即调用startActivityForResult()传递过去的值
//      resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==0x111 && resultCode==0x111)
        {
            getItemList();
        }
    }

    public void add(View v)
    {
        Intent intent = new Intent(MainActivity.this,AddItemActivity.class);
        startActivityForResult(intent,0x111);//打开新界面，0x111是请求码，
}
    private void getItemList(){
        ArrayList<String> list = new ArrayList<String>();
        try{
            FileInputStream in = openFileInput("item");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line ="";
            while((line=br.readLine())!=null)
            {
                list.add(line);
            }
            br.close();
            in.close();
            String[] contents = {};
            String[] allItem = list.toArray(contents);      //将动态数组转化成字符串数组
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,allItem);
            itemlist.setAdapter(adapter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
