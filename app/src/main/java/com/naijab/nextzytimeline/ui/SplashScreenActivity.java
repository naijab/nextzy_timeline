package com.naijab.nextzytimeline.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.Main.MainActivity;

public class SplashScreenActivity extends BaseMvpActivity<SplashScreenActivityInterface.Presenter>
    implements SplashScreenActivityInterface.View {

  private static final long DELAY_TIME = 1000;

  @Override
  public SplashScreenActivityInterface.Presenter createPresenter() {
    return SplashScreenActivityPresenter.create();
  }

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

  private void delayHandler(Runnable rannable){
    Handler handler = new Handler();
    handler.postDelayed(rannable, DELAY_TIME);
  }

  private void goToMainActivity() {
    delayHandler(new Runnable() {
      @Override
      public void run() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity( intent );
        finish();
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
      }
    });
  }
}

