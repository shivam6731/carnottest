package com.example.shivam6731.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shivam6731 on 11/15/2017.
 */

public class databasehelper3 extends SQLiteOpenHelper {
    private static final String TAG="db";
    private static final String TABLE_NAME="db3";
    private static final String col1="userId";
    private static final String col2="id";
    private static final String col3="title";
    private static final String col4="completed";


    public databasehelper3(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable="CREATE TABLE "+TABLE_NAME+" (userId text,id text,title text,completed text)";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }
    public void addData(String userId ,String id,String title,String completed)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,userId);
        contentValues.put(col2,id);
        contentValues.put(col3,title);
        contentValues.put(col4,completed);

        long result=db.insert(TABLE_NAME,null,contentValues);

    }
}

