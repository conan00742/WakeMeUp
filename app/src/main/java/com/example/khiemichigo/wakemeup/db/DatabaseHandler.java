package com.example.khiemichigo.wakemeup.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by USER on 3/14/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //version
    public static final int DATABASE_VERSION = 1;
    //name
    private static final String DATABASE_NAME = "ALARM_MANAGEMENT";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
