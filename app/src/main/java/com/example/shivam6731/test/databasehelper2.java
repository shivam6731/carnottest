package com.example.shivam6731.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shivam6731 on 11/15/2017.
 */

public class databasehelper2 extends SQLiteOpenHelper {
    private static final String TAG="db";
    private static final String TABLE_NAME="db2";
    private static final String col1="albumId";
    private static final String col2="id";
    private static final String col3="title";
    private static final String col4="url";
    private static final String col5="thumbnailUrl";

    public databasehelper2(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable="CREATE TABLE "+TABLE_NAME+" (albumId text, id text, title text, url text, thumbnailUrl text)";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }
    //addData id used to add data in rows and column
    public void addData(String albumId ,String id,String title,String url,String thumbnailUrl)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,albumId);
        contentValues.put(col2,id);
        contentValues.put(col3,title);
        contentValues.put(col4,url);
        contentValues.put(col5,thumbnailUrl);
        long result=db.insert(TABLE_NAME,null,contentValues);

    }
}

