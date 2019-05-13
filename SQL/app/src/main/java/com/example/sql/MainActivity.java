package com.example.sql;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView  = (ListView) findViewById(R.id.listview);
        getRelationFromDB();
    }
    private void getRelationFromDB(){
        //从数据加载联系人信息到列表视图
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.query();
        String[] from = {"_id","name","tel","groupName"};       //数据库字段数组
        int[] to = {R.id._id,R.id.name,R.id.tel,R.id.group};    //显示布局id数组
        SimpleCursorAdapter scadapter = new SimpleCursorAdapter(this,R.layout.relationlist,cursor,from,to);
        listView.setAdapter(scadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final long temp = id;
                AlertDialog.Builder adBuilder = new AlertDialog.Builder(MainActivity.this);
                adBuilder.setMessage("确认要删除记录么？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.del((int) temp);
                        Cursor cursor =dbHelper.query();
                        String[] from ={"_id","name","tel","groupName"};
                        int[] to = {R.id._id,R.id.name,R.id.tel,R.id.group};
                        SimpleCursorAdapter scadapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.relationlist,cursor,from,to);
                        MainActivity.this.listView.setAdapter(scadapter);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog  = adBuilder.create();
                alertDialog.show();

            }
        });
        dbHelper.close();
    }
    public void add(View view){ //增加单击按钮
        Intent intent = new Intent(MainActivity.this,AddRelationActivity.class);
        startActivityForResult(intent,0x111);

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode ==0x111&&requestCode==0x111);
        getRelationFromDB();// 增加新联系人后返回刷新列表框
    }
}
