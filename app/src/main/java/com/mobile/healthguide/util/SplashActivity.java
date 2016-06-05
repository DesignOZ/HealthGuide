package com.mobile.healthguide.util;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mobile.healthguide.R;

public class SplashActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Handler handler = new Handler(){
            public void  handleMessage(Message msg){
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,3000);
    }
}
