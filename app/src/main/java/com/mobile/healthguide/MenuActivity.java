package com.mobile.healthguide;

import com.mobile.healthguide.menu.IntroActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {
	Button btnPullup, btnDeadlift, btnChinup, btnDeeps;
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		btnPullup = (Button)findViewById(R.id.btnPullup_menu);
		btnPullup.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, IntroActivity.class);
				intent.putExtra("select", "팔굽혀펴기");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		
		btnDeadlift = (Button)findViewById(R.id.btnDeadlift_menu);
		btnDeadlift.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, IntroActivity.class);
				intent.putExtra("select", "데드리프트");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		
		btnChinup = (Button)findViewById(R.id.btnChinup_menu);
		btnChinup.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, IntroActivity.class);
				intent.putExtra("select", "턱걸이");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		
		btnDeeps = (Button)findViewById(R.id.btnDeeps_menu);
		btnDeeps.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity.this, IntroActivity.class);
				intent.putExtra("select", "딥스");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
	}
	
}
