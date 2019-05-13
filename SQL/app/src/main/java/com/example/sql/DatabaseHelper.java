package com.example.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyRelation.db";
    private static final String TABLE_NAME = "relation";
    private static final String CREATE_TABLE = "create table relation(_id integer primary key autoincrement,name text,tel text,groupName text);";
    //字段id为主键，自动增长

    private SQLiteDatabase db;          //创建SQLiteDatabase实例
    DatabaseHelper(Context context)//构造方法
    {
        super(context,DB_NAME,null,2);
    }
    public void insert(ContentValues values){//插入
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public void del(int id){//删除
        if(db==null)
        {
            db = getWritableDatabase();
            db.delete(TABLE_NAME,"_id = ?",new String[]{String.valueOf(id)});

        }
    }
    public Cursor query(){  //查询
        SQLiteDatabase db  = getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        return cursor;
    }
    public void close(){    //关闭数据库
        if(db != null) db.close();
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
            this.db  = db;
            db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
