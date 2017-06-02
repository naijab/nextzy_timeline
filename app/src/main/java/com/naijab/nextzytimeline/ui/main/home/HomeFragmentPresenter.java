package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

import io.realm.Realm;

public class HomeFragmentPresenter extends BaseMvpPresenter<HomeFragmentInterface.View>
        implements HomeFragmentInterface.Presenter {

    private Realm realm;

    public static HomeFragmentInterface.Presenter create() {
        return new HomeFragmentPresenter();
    }

    @Override
    public void onViewStart() {
        super.onViewStart();
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        realm.close();
    }

}
