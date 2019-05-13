package com.example.sql;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import android.widget.Spinner;

public class AddRelationActivity extends AppCompatActivity {
    private EditText addName,addTel;
    private Spinner addGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrelation);
        addName = (EditText) findViewById(R.id.addName);
        addTel = (EditText) findViewById(R.id.addTel);
        addGroup = (Spinner) findViewById(R.id.addGroup);
    }
    public void save(View view){
        final ContentValues values = new ContentValues();
        values.put("name",addName.getText().toString());
        values.put("tel",addTel.getText().toString());
        final DatabaseHelper dbHelper =  new DatabaseHelper(getApplicationContext());
        final AlertDialog.Builder adBuilder =new AlertDialog.Builder(this);
        adBuilder.setMessage("确认保存记录么？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.insert(values);
                Intent intent =getIntent();
                setResult(0x111,intent);
                AddRelationActivity.this.finish();  //关闭当前界面，返回首页
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog  = adBuilder.create();
        alertDialog.show();
    }
}
