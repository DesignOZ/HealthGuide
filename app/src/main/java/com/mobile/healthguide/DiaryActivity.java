package com.mobile.healthguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.healthguide.util.DBManager;
import com.mobile.healthguide.util.ListData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class DiaryActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    SQLiteDatabase sdb;
    DBManager dbm;

    java.util.Calendar cal = java.util.Calendar.getInstance();
    int year = cal.get(cal.YEAR);
    int month = cal.get(cal.MONTH) + 1;
    int day = cal.get(cal.DATE);
    int maxday_month = cal.getMaximum(Calendar.DAY_OF_MONTH);

    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(this);

        cv.setMinDate(month/01/year);
        cv.setMaxDate(month/maxday_month/year);
        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView txt_today = (TextView) findViewById(R.id.txt_today);
        txt_today.setText(year + "년 " + month + "월 " + day + "일" + "(오늘)");

        ViewSavedDB();
    }

    private class ListViewAdapter extends BaseAdapter {
        private Context mContext = null;
        private ArrayList<ListData> mListData = new ArrayList<ListData>();

        public ListViewAdapter(Context mContext) {
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void addItem(String mDate, String mTitle, String mCount) {
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.mTitle = mTitle;
            addInfo.mDate = mDate;
            addInfo.mCount = mCount;

            mListData.add(addInfo);
        }

        public void remove(int position) {
            mListData.remove(position);
            dataChange();
        }

        public void sort() {
            Collections.sort(mListData, ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        public void dataChange() {
            mAdapter.notifyDataSetChanged();
        }

        private class ViewHolder {
            public TextView mDate;
            public TextView mText;
            public TextView mCount;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_diary, null);

                holder.mDate = (TextView) convertView.findViewById(R.id.txt_diary_date);
                holder.mText = (TextView) convertView.findViewById(R.id.txt_diary_name);
                holder.mCount = (TextView) convertView.findViewById(R.id.txt_diary_count);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            holder.mText.setText(mData.mTitle);
            holder.mDate.setText(mData.mDate);
            holder.mCount.setText(mData.mCount);

            return convertView;
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

    @Override
    public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
        Toast.makeText(this, year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
    }

    public void ViewSavedDB() {
        mListView = (ListView) findViewById(R.id.list_diary);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        try {
            dbm = new DBManager(this, "Calendar.db", null, 1);
            sdb = dbm.getReadableDatabase();
            Cursor cur = sdb.rawQuery("select * from schedule", null);
            String Date, Count;

            while (cur.moveToNext()) {
                Date = cur.getString(6) + "일";
                Count = cur.getString(3) + "회";
                mAdapter.addItem(Date, cur.getString(2), Count);
                Log.d("mAdapter Added", "ViewSavedDB: " + cur.getString(1) + cur.getString(2) + cur.getString(3) + cur.getString(4) + cur.getString(5) + cur.getString(6));
//
//                for (int i = 0; i < 5; i++)
//                    schedule[count][i] = cur.getString(i + 2);
//                count++;
            }
            cur.close();
            dbm.close();
        } catch (SQLiteException e) {
            Toast.makeText(this, "목록보기 오류 발생" + e, Toast.LENGTH_SHORT).show();
        }
    }
}
