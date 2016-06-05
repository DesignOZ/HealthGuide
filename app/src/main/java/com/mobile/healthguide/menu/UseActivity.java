package com.mobile.healthguide.menu;

import com.mobile.healthguide.R;
import com.mobile.healthguide.R.drawable;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebIconDatabase.IconListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class UseActivity extends AppCompatActivity{
	TextView tvText;
	Button btnBack;
	String sSelect = "";
	ImageView imgview, image_tast1;
	VideoView video_test;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_use);

		// Insert Menu item at Actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		Intent intent = getIntent();
		sSelect = intent.getStringExtra("select");	

		if(sSelect.equals("팔굽혀펴기")){

			
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
			
			
		}else if(sSelect.equals("데드리프트")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("턱걸이")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("딥스")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("윗몸일으키기")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("V_크런치")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("터칭토우")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("스쿼트")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("러싱런지")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
		else if(sSelect.equals("만보기")){
			video_test = (VideoView)findViewById(R.id.test);

			MediaController mediacontroller = new MediaController(this);

			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);

			video_test.setMediaController(mediacontroller);

			video_test.setVideoURI(video);

			video_test.requestFocus();

			video_test.start();
		}

		else if(sSelect.equals("사이드런지")){
			video_test = (VideoView)findViewById(R.id.test);
			
			MediaController mediacontroller = new MediaController(this);
			
			mediacontroller.setAnchorView(video_test);

			Uri video = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.test_test);
			
			video_test.setMediaController(mediacontroller);
			
			video_test.setVideoURI(video);
			
			video_test.requestFocus();
			
			video_test.start();
		}
	}
	
}		