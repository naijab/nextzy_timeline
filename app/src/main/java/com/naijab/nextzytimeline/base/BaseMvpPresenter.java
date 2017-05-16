package com.naijab.nextzytimeline.base;

import com.naijab.nextzytimeline.base.exception.MvpViewNotAttachedException;
import java.lang.ref.WeakReference;

/**
 * Created by Xiltron on 16/5/2560.
 */

public abstract class BaseMvpPresenter<V extends BaseMvpInterface.View>
    implements BaseMvpInterface.Presenter<V> {

  private WeakReference<V> mMvpView;

  @Override
  public void attachView(V mvpView) {
    mMvpView = new WeakReference<>(mvpView);
  }

  @Override
  public void detachView() {
    mMvpView = null;
  }

  @Override
  public V getView() throws NullPointerException {
    if (mMvpView != null) return mMvpView.get();
    throw new MvpViewNotAttachedException();
  }

  @Override
  public void onViewCreate() {

  }

  @Override
  public void onViewStart() {

  }

  @Override
  public void onViewStop() {

  }

  @Override
  public void onViewDestroy() {

  }


}
