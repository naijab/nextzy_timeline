package com.naijab.nextzytimeline.ui.main.adapter;


import com.naijab.nextzytimeline.base.exception.MvpViewNotAttachedException;
import java.lang.ref.WeakReference;


public class NextzyPagerStateAdapterPresenter
    implements NextzyPagerStateAdapterInterface.Presenter {

  private WeakReference<NextzyPagerStateAdapterInterface.Adapter> adapter;

  public static NextzyPagerStateAdapterInterface.Presenter create() {
    return new NextzyPagerStateAdapterPresenter();
  }


  @Override
  public void setAdapter(NextzyPagerStateAdapterInterface.Adapter adapter) {
    this.adapter = new WeakReference<>(adapter);
  }

  @Override
  public NextzyPagerStateAdapterInterface.Adapter getAdapter() {
    if (adapter != null) {
      return adapter.get();
    }
    throw new MvpViewNotAttachedException();
  }

}
