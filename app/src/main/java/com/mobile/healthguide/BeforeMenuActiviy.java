package com.mobile.healthguide;

import com.mobile.healthguide.MenuActivity;
import com.mobile.healthguide.MenuActiviry2;
import com.mobile.healthguide.MenuActivity3;

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

public class BeforeMenuActiviy extends Activity{

	Button btnup,btnab, btnlower;
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beforemenu);
		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);


		btnup=(Button)findViewById(R.id.btnup_before);
		btnup.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BeforeMenuActiviy.this, MenuActivity.class);
				vibe.vibrate(100);
				startActivity(intent);			
				}
		});
		
		btnab=(Button)findViewById(R.id.btnab_before);
		btnab.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BeforeMenuActiviy.this, MenuActiviry2.class);
				vibe.vibrate(100);
				startActivity(intent);	
			}
		});
		btnlower=(Button)findViewById(R.id.btnlower_before);
		btnlower.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(BeforeMenuActiviy.this, MenuActivity3.class);
				vibe.vibrate(100);
				startActivity(intent);	
				
			}
		});
	}
}
