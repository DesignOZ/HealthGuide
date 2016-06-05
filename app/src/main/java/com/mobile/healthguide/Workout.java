package com.mobile.healthguide;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Workout extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // getIntent
        Intent intent = getIntent();
        int workout = intent.getIntExtra("workout", 0);

        // SetOnClickLIstener
        findViewById(R.id.txt_accessory).setOnClickListener(this);
        findViewById(R.id.txt_stretching).setOnClickListener(this);



        //RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<Recycler_item> items = new ArrayList<>();

        if (workout == 1) {
            Recycler_item[] item = new Recycler_item[4];

            item[0] = new
                    Recycler_item("팔굽혀펴기");

            item[1] = new
                    Recycler_item("데드리프트");

            item[2] = new
                    Recycler_item("턱걸이");

            item[3] = new
                    Recycler_item("딥스");

            for (
                    int i = 0; i < item.length; i++)
                items.add(item[i]);

        } else if (workout == 2) {

            // 복근
            Recycler_item[] item = new Recycler_item[3];
            item[0] = new

                    Recycler_item("윗몸일으키기");

            item[1] = new

                    Recycler_item("V 크런치");

            item[2] = new

                    Recycler_item("터칭토우");

            for (
                    int i = 0; i < item.length; i++)
                items.add(item[i]);

        } else if (workout == 3) {
            Recycler_item[] item = new Recycler_item[3];
            item[0] = new

                    Recycler_item("스쿼트");

            item[1] = new

                    Recycler_item("러싱런지");

           item[2] = new

                    Recycler_item("사이드런지");


            for (
                    int i = 0; i < item.length; i++)
                items.add(item[i]);
        }

        recyclerView.setAdapter(new
                RecyclerAdapter(getApplicationContext(), items, R
                .layout.activity_main));
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
    public void onClick(View v) {
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        switch (v.getId())  {
            case R.id.txt_stretching :
                Intent help = new Intent(this, HelpActivity.class);
                startActivity(help);
                vibe.vibrate(100);
                break;

            case R.id.txt_accessory :
                Intent accessory = new Intent(this, Accessory.class);
                startActivity(accessory);
                vibe.vibrate(100);
                break;
        }
    }
}
