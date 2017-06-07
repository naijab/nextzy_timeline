package com.naijab.nextzytimeline.ui.people.detail.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

import io.realm.Realm;

public class DetailPeopleFragmentPresenter extends BaseMvpPresenter<DetailPeopleFragmentInterface.View>
        implements DetailPeopleFragmentInterface.Presenter {

    public static DetailPeopleFragmentInterface.Presenter create() {
        return new DetailPeopleFragmentPresenter();
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
