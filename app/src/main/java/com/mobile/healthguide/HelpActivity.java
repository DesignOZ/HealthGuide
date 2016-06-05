package com.mobile.healthguide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpActivity extends Activity {
	Vibrator vibe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);

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
