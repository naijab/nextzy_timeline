package com.naijab.nextzytimeline.ui.Main.adapter;


import com.naijab.nextzytimeline.base.exception.MvpViewNotAttachedException;
import java.lang.ref.WeakReference;


public class MainFragmentPagerAdapterPresenter
    implements MainFragmentPagerAdapterInterface.Presenter {

  private WeakReference<MainFragmentPagerAdapterInterface.Adapter> adapter;

  public static MainFragmentPagerAdapterInterface.Presenter create() {
    return new MainFragmentPagerAdapterPresenter();
  }


  @Override
  public void setAdapter(MainFragmentPagerAdapterInterface.Adapter adapter) {
    this.adapter = new WeakReference<>(adapter);
  }

  @Override
  public MainFragmentPagerAdapterInterface.Adapter getAdapter() {
    if (adapter != null) {
      return adapter.get();
    }
    throw new MvpViewNotAttachedException();
  }

}
