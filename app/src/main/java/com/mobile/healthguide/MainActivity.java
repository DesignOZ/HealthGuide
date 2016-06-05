package com.mobile.healthguide;

import com.mobile.healthguid.db.DbActivity;
import com.mobile.healthguide.menu.IntroActivity;
import com.mobile.healthguide.util.Splash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btndb,btnpri, btnContent, btnmanbo;
	Vibrator vibe;
	private BackPressCloseHandler backPressCloseHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		backPressCloseHandler = new BackPressCloseHandler(this);

		btndb = (Button)findViewById(R.id.btnpri_Main);
		btndb.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, BeforeMenuActiviy.class);
				vibe.vibrate(100);
				startActivity(intent);
			}
		});

		btndb = (Button)findViewById(R.id.btndb_Main);
		btndb.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DbActivity.class);
				vibe.vibrate(100);
				startActivity(intent);
			}
		});

		btnContent = (Button)findViewById(R.id.btnContent_Main);
		btnContent.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, HelpActivity.class);
				vibe.vibrate(100);
				startActivity(intent);
			}
		});

		btnmanbo = (Button)findViewById(R.id.btnmanbo);
		btnmanbo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, IntroActivity.class);
				intent.putExtra("select", "만보기");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});

	}
	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
}
