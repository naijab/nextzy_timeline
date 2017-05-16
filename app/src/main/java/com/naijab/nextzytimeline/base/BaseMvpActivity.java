package com.naijab.nextzytimeline.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.naijab.nextzytimeline.base.exception.MvpPresenterNotCreateException;

/**
 * Created by Xiltron on 16/5/2560.
 */

public abstract class BaseMvpActivity<P extends BaseMvpInterface.Presenter>
    extends AppCompatActivity
    implements BaseMvpInterface.View {

  private P presenter;

  @SuppressWarnings("unchecked")
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState,
      @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    presenter = createPresenter();
    presenter.attachView(this);
    int layoutResID = getLayoutView();
    if ( getLayoutView() == 0);
    setContentView(layoutResID);
    bindView();
    setupInstance();
    setupView();
    getPresenter().onViewCreate();
    if (savedInstanceState == null){
      initialize();
    }

  }

  @Override
  protected void onStart() {
    super.onStart();
    getPresenter().onViewStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    getPresenter().onViewStop();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    getPresenter().onViewDestroy();
    presenter.detachView();
  }

  @Override
  public P getPresenter() {
    if (presenter != null) return presenter;
    throw new MvpPresenterNotCreateException();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    restoreView(savedInstanceState);
  }

  public void restoreView(Bundle savedInstanceState) {}

  public abstract P createPresenter();

  public abstract int getLayoutView();

  public abstract void bindView();

  public abstract void setupInstance();

  public abstract void setupView();

  public abstract void initialize();
}
