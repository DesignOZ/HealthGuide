package com.mobile.healthguide;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HelpActivity extends AppCompatActivity{
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

		// Insert Menu item at Actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}
	public void browser1(View view){
		Intent browserIntent1=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9-3OCc5g5oE"));
		vibe.vibrate(100);
		startActivity(browserIntent1);
	}

	public void browser2(View view){
		Intent browserIntent2=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=9-3OCc5g5oE"));
		vibe.vibrate(100);
		startActivity(browserIntent2);
	}

}
