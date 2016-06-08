package com.mobile.healthguide;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.mobile.healthguide.util.DBManager;


public class DBActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edt_name, edt_price, edt_su;
    Button btn_insert, btn_result, btn_delete;
    SQLiteDatabase sdb;
    DBManager dbm;

    java.util.Calendar cal = java.util.Calendar.getInstance();
    int year = cal.get(cal.YEAR);
    int month = cal.get(cal.MONTH) + 1;
    int day = cal.get(cal.DATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        dbm = new DBManager(this, "Calendar.db", null, 1);
        sdb = dbm.getReadableDatabase();

        edt_name = (EditText) findViewById(R.id.edt1);
        edt_price = (EditText) findViewById(R.id.edt2);
        edt_su = (EditText) findViewById(R.id.edt3);

        btn_insert = (Button) findViewById(R.id.btn1);
        btn_result = (Button) findViewById(R.id.btn2);
        btn_delete = (Button) findViewById(R.id.btn3);
        btn_insert.setOnClickListener(this);
        btn_result.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        ViewSavedDB();
    }

    @Override
    public void onClick(View v) {
        ContentValues value = new ContentValues();
        switch (v.getId()) {
            case R.id.btn1:
                if (edt_name.getText().toString().equals("")) {
                    Toast.makeText(this, "운동명을 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edt_price.getText().toString().equals("")) {
                    Toast.makeText(this, "횟수를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (edt_su.getText().toString().equals("")) {
                    Toast.makeText(this, "셋트를 입력하세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                value.put("calnum", 0);
                value.put("name", edt_name.getText().toString());
                value.put("count", Integer.parseInt(edt_price.getText().toString()));
                value.put("year", year);
                value.put("month", month);
                value.put("dayofmonth", day);

                try {
                    dbm = new DBManager(this, "Calendar.db", null, 1);
                    sdb = dbm.getWritableDatabase();
                    Log.i("db", "경로:" + sdb.getPath());
                    sdb.insert("schedule", null, value);
                    dbm.close();
                    Toast.makeText(this, "운동입력 : " + value.get("year") + "/" + value.get("month") + "/" + value.get("dayofmonth") + "\n" + value.get("name") + " " + value.get("count"), Toast.LENGTH_SHORT).show();
                } catch (SQLiteException e) {
                    Toast.makeText(this, "입력 오류 발생" + e, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn2:
                ViewSavedDB();
                break;

            case R.id.btn3:
                try {
                    dbm = new DBManager(this, "Calendar.db", null, 1);
                    sdb = dbm.getWritableDatabase();
                    Log.i("db", "경로:" + sdb.getPath());
                    sdb.execSQL("Delete From schedule;");
                    dbm.close();
                    Toast.makeText(this, "전체 레코드 삭제", Toast.LENGTH_SHORT).show();

                } catch (SQLiteException e) {
                    Toast.makeText(this, "전체 삭제 오류 발생" + e, Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void ViewSavedDB() {
        edt_name.setText("");
        edt_price.setText("");
        edt_su.setText("");

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

            ListView list = (ListView) findViewById(R.id.list_db);
            ListAdapter ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, schedule);
            list.setAdapter(ad);
        } catch (SQLiteException e) {
            Toast.makeText(this, "목록보기 오류 발생" + e, Toast.LENGTH_SHORT).show();
        }
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
}