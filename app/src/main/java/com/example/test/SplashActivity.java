package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private final String TAG = "Splash Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Log.e(TAG, "Application Running....");
            startActivity(new Intent(this, LogInActivity.class));//TODO 로그인 여부에 따라 넘어가는곳 다르게
            finish();
        }, 2000);
    }
}