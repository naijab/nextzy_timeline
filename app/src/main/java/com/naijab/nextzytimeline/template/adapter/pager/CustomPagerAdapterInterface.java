package com.naijab.nextzytimeline.template.adapter.pager;


import android.support.v4.app.FragmentManager;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class CustomPagerAdapterInterface{


    public interface Adapter{
        FragmentManager getFragmentManager();

        Presenter getPresenter();
    }

    public interface Presenter{
        void setAdapter(Adapter adapter);
        Adapter getAdapter();
    }
}
