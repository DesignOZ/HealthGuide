package com.mobile.healthguid.db;

import com.mobile.healthguide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DbActivity extends Activity{

	TextView tv1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_db);
		
		tv1=(TextView)findViewById(R.id.textview_db);
		
	}
	
}
