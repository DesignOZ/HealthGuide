package com.mobile.healthguide.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.mobile.healthguide.R;

public class IntroActivity extends AppCompatActivity {
    Button btnUse, btnStart, btnBack;
    String sSelect = "";
    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Insert Menu item at Actionbar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Intent intent = getIntent();
        sSelect = intent.getStringExtra("select");
        actionBar.setTitle(sSelect);

        btnUse = (Button) findViewById(R.id.btnUse_Intro);
        btnUse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, UseActivity.class);
                intent.putExtra("select", sSelect);
                vibe.vibrate(100);
                startActivity(intent);
            }
        });

        btnStart = (Button) findViewById(R.id.btnStart_Intro);
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroActivity.this, ExerActivity.class);
                intent.putExtra("select", sSelect);
                vibe.vibrate(100);
                startActivity(intent);
            }
        });

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
