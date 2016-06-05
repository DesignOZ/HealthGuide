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

public class MenuActiviry2 extends Activity{
	Button btnSitup, btnV_crunch,btnTouchTow;
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu2);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		btnSitup=(Button)findViewById(R.id.btnSitup_menu2);
		btnSitup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActiviry2.this, IntroActivity.class);
				intent.putExtra("select", "윗몸일으키기");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		btnV_crunch=(Button)findViewById(R.id.btnV_crunch_menu2);
		btnV_crunch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActiviry2.this, IntroActivity.class);
				intent.putExtra("select", "V_크런치");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		btnTouchTow=(Button)findViewById(R.id.btnTouchTow_menu2);
		btnTouchTow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActiviry2.this, IntroActivity.class);
				intent.putExtra("select", "터칭토우");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
	}
}
