package com.mobile.healthguide;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.healthguide.util.DBManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DiaryActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    SQLiteDatabase sdb;
    DBManager dbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(this);

        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbm = new DBManager(this, "Calendar.db", null, 1);
        sdb = dbm.getReadableDatabase();

        ViewSavedDB();
    }

    // event for Menu item at ActionBar
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    public void ViewSavedDB() {
        try {
            dbm = new DBManager(this, "Calendar.db", null, 1);
            sdb = dbm.getReadableDatabase();
            Cursor cur = sdb.rawQuery("select * from schedule", null);
            int count2 = 1;
            String[] schedule = new String[cur.getCount()];
            int count = 0;
            while (cur.moveToNext()) {
                schedule[count] = "\t" + count2 + "\t" + cur.getString(2) + "\t" + cur.getInt(3) + "\t" + cur.getInt(4) + "\t" + cur.getInt(5) + "\t" + cur.getInt(6);
                count++;
                count2++;
            }
            cur.close();
            dbm.close();

            ListView list = (ListView) findViewById(R.id.list_diary);
            ListAdapter ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
            list.setAdapter(ad);
        } catch (SQLiteException e) {
            Toast.makeText(this, "목록보기 오류 발생" + e, Toast.LENGTH_SHORT).show();
        }
    }
}
