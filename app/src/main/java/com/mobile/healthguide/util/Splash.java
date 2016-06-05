package com.mobile.healthguide.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.mobile.healthguide.MainActivity;
import com.mobile.healthguide.R;

public class Splash extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                overridePendingTransition(0, android.R.anim.fade_in);
                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
