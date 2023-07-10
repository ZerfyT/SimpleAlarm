package com.thiwaan.simplealarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class RecentAlarms extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlarmAdapter alarmAdapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_alarms);

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadAlarms();
    }

    private void loadAlarms() {
        List<Alarm> alarmList = getAllAlarms();
        alarmAdapter = new AlarmAdapter(alarmList, new AlarmAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(Alarm alarm) {
                // Handle edit click
                // Implement your logic to edit the alarm
            }

            @Override
            public void onDeleteClick(Alarm alarm) {
                // Handle delete click
                // Implement your logic to delete the alarm
                deleteAlarm(alarm);
            }
        });
        recyclerView.setAdapter(alarmAdapter);
    }

    @SuppressLint("Range")
    private List<Alarm> getAllAlarms() {
        List<Alarm> alarmList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM alarms", null);
        if (cursor.moveToFirst()) {
            do {
                Alarm alarm = new Alarm();
                alarm.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                alarm.setTime(cursor.getString(cursor.getColumnIndex("time")));
                alarmList.add(alarm);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return alarmList;
    }

    private void deleteAlarm(Alarm alarm) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("alarms", "_id=?", new String[]{String.valueOf(alarm.getId())});
        db.close();

        loadAlarms();
    }
}