package com.mobile.healthguide.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by WooJun on 2015-09-07.
 */
public class DBManager extends SQLiteOpenHelper{

    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory,
    				int version)
    {
    	super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "create table schedule( " +
                "id integer primary key autoincrement, " +
                "calnum integer not null, " +
                "name text not null, " +
                "count integer not null, " +
                "year integer not null, " +
                "month integer not null, " +
                "dayofmonth integer not null)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exist schedule");
        onCreate(db);
    }
}

