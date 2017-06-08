package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class HomeFragmentPresenter extends BaseMvpPresenter<HomeFragmentInterface.View>
        implements HomeFragmentInterface.Presenter {

    public static HomeFragmentInterface.Presenter create() {
        return new HomeFragmentPresenter();
    }

    @Override
    public void onViewStart() {
        super.onViewStart();
        getView().startRealm();
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        getView().stopRealm();
    }

}
