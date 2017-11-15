package com.example.shivam6731.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shivam6731 on 11/15/2017.
 */

public class databasehelper1 extends SQLiteOpenHelper {
    private static final String TAG="db";
    private static final String TABLE_NAME="db1";
    private static final String col1="PostId";
    private static final String col2="id";
    private static final String col3="name";
    private static final String col4="email";
    private static final String col5="body";

    public databasehelper1(Context context) {
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createtable="CREATE TABLE "+TABLE_NAME+" (PostId text, id text, name text, email text, body text)";
        sqLiteDatabase.execSQL(createtable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);


    }
    public void addData(String postid ,String id,String name,String email,String body)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col1,postid);
        contentValues.put(col2,id);
        contentValues.put(col3,name);
        contentValues.put(col4,email);
        contentValues.put(col5,body);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
        {
            System.out.println("no");
        }
        else
        {
            System.out.println("inserted "  );
        }
    }
}
