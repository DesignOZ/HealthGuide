package com.mobile.healthguide;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.healthguide.util.ListData;

import java.util.ArrayList;
import java.util.Collections;


public class HelpActivity extends AppCompatActivity {
    Vibrator vibe;
    private ListView mListView = null;
    private ListViewAdapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        mListView = (ListView) findViewById(R.id.mList);

        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);

        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_1),
                "기본 스트레칭",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_2),
                "건강한 다리만들기",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_3),
                "뱃살 빼기 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_4),
                "허리 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_5),
                "스트레칭",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_6),
                "날씬한 팔 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_7),
                "등, 뒤태 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_8),
                "111 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_9),
                "유연성 트레이닝",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_10),
                "뱃살빼기 운동",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_11),
                "의자에서 하는 운동1",
                " ");
        mAdapter.addItem(getResources().getDrawable(R.drawable.juho_12),
                "의자에서 하는 운동2",
                " ");

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ListData mData = mAdapter.mListData.get(position);

                if (mData.mTitle.equals("기본 스트레칭")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=xoxyAeflLxM&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=15")));
                }

                if (mData.mTitle.equals("건강한 다리만들기")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=KTxthxKiisY&index=13&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }

                if (mData.mTitle.equals("뱃살 빼기 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=mGfpPshoQ1E&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=11")));
                }
                if (mData.mTitle.equals("허리 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9ZCm40HWe-E&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=9")));
                }
                if (mData.mTitle.equals("스트레칭")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=g-PWdph-O8Y&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=3")));
                }
                if (mData.mTitle.equals("날씬한 팔 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=IL1pRIoRtoo&index=7&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }
                if (mData.mTitle.equals("등, 뒤태 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=B5PMN17tBJE&index=10&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }
                if (mData.mTitle.equals("111 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=TNoZEcG4bfI&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=1")));
                }
                if (mData.mTitle.equals("유연성 트레이닝")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=70pLOhL4KV8&index=6&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }
                if (mData.mTitle.equals("뱃살빼기 운동")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=aN-9nAVaDkQ&index=4&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }
                if (mData.mTitle.equals("의자에서 하는 운동1")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=GS9e4ybCpUU&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec&index=5")));
                }
                if (mData.mTitle.equals("의자에서 하는 운동2")) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=4xPbQLfbdQA&index=2&list=PLvV0U7qnS9-QK-Oe0e5UnpueU9rc9o0Ec")));
                }
            }
        });
    }

    private class ViewHolder {
        public ImageView mIcon;

        public TextView mText;

        public TextView mDate;
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

        public void addItem(Drawable icon, String mTitle, String mDate) {
            ListData addInfo = null;
            addInfo = new ListData();
            addInfo.mIcon = icon;
            addInfo.mTitle = mTitle;
            addInfo.mDate = mDate;

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

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item, null);

                holder.mIcon = (ImageView) convertView.findViewById(R.id.mImage);
                holder.mText = (TextView) convertView.findViewById(R.id.mText);
                holder.mDate = (TextView) convertView.findViewById(R.id.mDate);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ListData mData = mListData.get(position);

            if (mData.mIcon != null) {
                holder.mIcon.setVisibility(View.VISIBLE);
                holder.mIcon.setImageDrawable(mData.mIcon);
            } else {
                holder.mIcon.setVisibility(View.GONE);
            }

            holder.mText.setText(mData.mTitle);
            holder.mDate.setText(mData.mDate);

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
}