package com.naijab.nextzytimeline.ui.people.detail.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class DetailPeopleFragmentPresenter extends BaseMvpPresenter<DetailPeopleFragmentInterface.View>
        implements DetailPeopleFragmentInterface.Presenter {

    private Realm realm;

    public static DetailPeopleFragmentInterface.Presenter create() {
        return new DetailPeopleFragmentPresenter();
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
