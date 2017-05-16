package com.naijab.nextzytimeline.ui.main.adapter;


import android.support.v4.app.FragmentManager;

public class NextzyPagerStateAdapterInterface {


  public interface Adapter {

    FragmentManager getFragmentManager();

    Presenter getPresenter();
  }

  public interface Presenter {

    void setAdapter(NextzyPagerStateAdapterInterface.Adapter adapter);

    Adapter getAdapter();
  }
}
