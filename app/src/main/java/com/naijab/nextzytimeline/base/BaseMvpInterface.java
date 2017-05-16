package com.naijab.nextzytimeline.base;

/**
 * Created by Xiltron on 16/5/2560.
 */

public interface BaseMvpInterface {

  interface View {

    Presenter getPresenter();
  }

  interface Presenter<V extends BaseMvpInterface.View> {

    void attachView(V mvpView);

    void detachView();

    V getView();

    void onViewCreate();

    void onViewStart();

    void onViewStop();

    void onViewDestroy();
  }


}
