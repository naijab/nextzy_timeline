package com.naijab.nextzytimeline.ui.Main.adapter;


import android.support.v4.app.FragmentManager;

public class MainFragmentPagerAdapterInterface {


  public interface Adapter {

    FragmentManager getFragmentManager();

    Presenter getPresenter();
  }

  public interface Presenter {

    void setAdapter(Adapter adapter);

    Adapter getAdapter();
  }
}
