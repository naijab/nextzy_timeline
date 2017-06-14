package com.naijab.nextzytimeline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseActivity;
import com.naijab.nextzytimeline.ui.main.MainActivity;

public class SplashScreenActivity extends BaseActivity {

    private Handler handler;
    private Runnable runnable;
    private long delayTime;
    private long time = 1000L;

    @Override
    public int getLayoutView() {
        return R.layout.splash_screen;
    }

    @Override
    public void bindView() {
    }

    @Override
    public void setupInstance() {
        goToMainActivity();
    }

    @Override
    public void setupView() {
    }

    @Override
    public void initialize() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    private void goToMainActivity() {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();
        delayTime = time;
        handler.postDelayed(runnable, delayTime);
        time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delayTime - (System.currentTimeMillis() - time);
    }

}


