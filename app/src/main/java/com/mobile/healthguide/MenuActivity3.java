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

public class MenuActivity3 extends Activity{
	Button btnSquate,btnRushingLunged,btnsideLunged;
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu3);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		btnSquate=(Button)findViewById(R.id.btnSquate_menu3);
		btnSquate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity3.this, IntroActivity.class);
				intent.putExtra("select", "스쿼트");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		btnRushingLunged=(Button)findViewById(R.id.btnRushingLunged_menu3);
		btnRushingLunged.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity3.this, IntroActivity.class);
				intent.putExtra("select", "러싱런지");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
		btnsideLunged=(Button)findViewById(R.id.btnsideLunged_menu3);
		btnsideLunged.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MenuActivity3.this, IntroActivity.class);
				intent.putExtra("select", "사이드런지");
				vibe.vibrate(100);
				startActivity(intent);
			}
		});
	}
}
