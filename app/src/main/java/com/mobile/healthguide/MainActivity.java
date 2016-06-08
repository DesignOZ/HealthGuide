package com.mobile.healthguide;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.healthguide.util.BackPressCloseHandler;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    static int workout = 0;
    Button btndb, btnpri, btnContent, btnmanbo;
    Vibrator vibe;
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout, Navigation Drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        backPressCloseHandler = new BackPressCloseHandler(this);

        //160605 추가
        findViewById(R.id.main_walk).setOnClickListener(this);          // 만보계

        findViewById(R.id.txt_accessory).setOnClickListener(this);      // 악세서리 (아두이노)
        findViewById(R.id.txt_stretching).setOnClickListener(this);     // 스트레칭

        findViewById(R.id.workout_top).setOnClickListener(this);        // 상체
        findViewById(R.id.workout_middle).setOnClickListener(this);     // 복근
        findViewById(R.id.workout_bottom).setOnClickListener(this);     // 하체

        findViewById(R.id.txt_diary).setOnClickListener(this);          // 다이어리 (DB)
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_walk:
                Intent walk = new Intent(MainActivity.this, WalkActivity.class);
                startActivity(walk);
                Toast.makeText(this, "만보계", Toast.LENGTH_SHORT).show();
                break;

            case R.id.txt_accessory:
                Intent accessory = new Intent(MainActivity.this, Accessory.class);
                startActivity(accessory);
                break;

            case R.id.txt_stretching:
                Intent help = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(help);
                break;


            case R.id.workout_top:
                Intent workout_top = new Intent(MainActivity.this, Workout.class);
                workout_top.putExtra("workout", 1);
                startActivity(workout_top);
                break;

            case R.id.workout_middle:
                Intent workout_middle = new Intent(MainActivity.this, Workout.class);
                workout_middle.putExtra("workout", 2);
                startActivity(workout_middle);
                break;

            case R.id.workout_bottom:
                Intent workout_bottom = new Intent(MainActivity.this, Workout.class);
                workout_bottom.putExtra("workout", 3);
                startActivity(workout_bottom);
                break;

            case R.id.txt_diary:
                Intent diary = new Intent(MainActivity.this, DiaryActivity.class);
                startActivity(diary);
                break;
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
