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

    /**
     *  该函数的流程如下
     *  首先创建一个DatabaseHelper对象，DatabaseHelper是一个数据库工作类
     *  创建一个Cursor用于查询
     *  建立适配器，实现数据与对象的绑定
     *  将listView与适配器绑定
     *  设置listView的点击监听器
     *  实现警告对话框的实现，点击确定的话就删除，点取消的话就返回
     *  操作完后关闭数据库对象
     */
    private void getRelationFromDB(){
        //从数据加载联系人信息到列表视图
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        Cursor cursor = dbHelper.query();          //光标查询？
        String[] from = {"_id","name","tel","groupName"};       //数据库字段数组
        int[] to = {R.id._id,R.id.name,R.id.tel,R.id.group};    //显示布局id数组
        SimpleCursorAdapter scadapter = new SimpleCursorAdapter(this,R.layout.relationlist,cursor,from,to);
        //建立一个适配器适配器是一个连接数据和AdapterView（ListView就是一个典型的AdapterView，后面还会学习其他的）的桥梁，
        // 通过它能有效地实现数据与AdapterView的分离设置，使AdapterView与数据的绑定更加简便，修改更加方便
        listView.setAdapter(scadapter);         //给listView设置适配器，用来匹配xml
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
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
                AlertDialog alertDialog  = adBuilder.create();              //创建一个警告对话框
                alertDialog.show();                                         //显示警告对话框

            }
        }); dbHelper.close();

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
