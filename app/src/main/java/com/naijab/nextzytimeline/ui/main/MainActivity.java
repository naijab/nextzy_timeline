package com.naijab.nextzytimeline.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.main.adapter.NextzyPagerStateAdapter;

public class MainActivity extends BaseMvpActivity<MainActivityInterface.Presenter>
    implements MainActivityInterface.View {

  private ViewPager viewPager;
  private TabLayout tabLayout;
  private Toolbar toolbar;
  private NextzyPagerStateAdapter nextzyPagerStateAdapter;

  public MainActivity(){
    super();
  }

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
    viewPager = (ViewPager) findViewById(R.id.container);
    tabLayout = (TabLayout) findViewById(R.id.tabs);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
  }

  @Override
  public void setupInstance() {
    nextzyPagerStateAdapter = new NextzyPagerStateAdapter(this);
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

