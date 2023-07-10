package com.thiwaan.simplealarm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarms.db";
    private static final int DATABASE_VERSION = 3;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alarms (_id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        onCreate(db);
    }

    public boolean saveAlarm(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("time", time);
        long result = db.insert("alarms", null, contentValues);
        return result != -1;
    }

//    public boolean updateAlarm(String time) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("time", time);
//        long result = db.update("alarms", contentValues, "time = ?", new String[]{time});
//        return result != -1;
//    }
}
