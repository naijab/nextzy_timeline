package com.naijab.nextzytimeline.ui.Main;

import android.os.Bundle;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;

public class MainActivity extends BaseMvpActivity<MainActivityInterface.Presenter>
    implements MainActivityInterface.View {

  @Override
  public MainActivityInterface.Presenter createPresenter() {
    return MainActivityPresenter.create();
  }

  @Override
  public int getLayoutView() {
    return R.layout.activity_main;
  }

  @Override
  public void bindView() {
  }

  @Override
  public void setupInstance() {

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

}

