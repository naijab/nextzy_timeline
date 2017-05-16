package com.naijab.nextzytimeline.ui.Main.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.exception.MvpPresenterNotCreateException;
import com.naijab.nextzytimeline.ui.Main.About.AboutFragment;
import com.naijab.nextzytimeline.ui.Main.Contact.ContactFragment;
import com.naijab.nextzytimeline.ui.Main.Home.HomeFragment;
import com.naijab.nextzytimeline.utility.StringUtility;
import java.lang.ref.WeakReference;

public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter
    implements MainFragmentPagerAdapterInterface.Adapter {

  private final static String TAG = MainFragmentPagerAdapter.class.getSimpleName();
  private final WeakReference<FragmentManager> fm;
  private static final int TOTAL_PAGE = 3;
  private final MainFragmentPagerAdapterInterface.Presenter presenter;
  private final Context context;

  public MainFragmentPagerAdapter(FragmentActivity view) {
    super(view.getSupportFragmentManager());
    fm = new WeakReference<>(view.getSupportFragmentManager());
    context = view;
    presenter = MainFragmentPagerAdapterPresenter.create();
    presenter.setAdapter(this);
  }

  @Override
  public int getCount() {
    return TOTAL_PAGE;
  }

  @Override
  public Fragment getItem(int position) {

    switch (position) {
      case 0:
        return HomeFragment.newInstance();
      case 1:
        return AboutFragment.newInstance();
      case 2:
        return ContactFragment.newInstance();
      default:
        throw new NullPointerException("Position more than: " + TOTAL_PAGE);
    }
  }

  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return StringUtility.getHomeString(context);
      case 1:
        return StringUtility.getAboutString(context);
      case 2:
        return StringUtility.getContactString(context);
    }
    return null;
  }

  @Override
  public FragmentManager getFragmentManager() {
    return fm.get();
  }

  @Override
  public MainFragmentPagerAdapterInterface.Presenter getPresenter() {
    if (presenter != null) {
      return presenter;
    }
    throw new MvpPresenterNotCreateException();
  }
}
