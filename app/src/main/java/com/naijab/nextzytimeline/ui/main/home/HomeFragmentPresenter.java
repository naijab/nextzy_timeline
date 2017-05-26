package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeFragmentPresenter extends BaseMvpPresenter<HomeFragmentInterface.View>
        implements HomeFragmentInterface.Presenter{

    private Realm realm;

    public static HomeFragmentInterface.Presenter create(){
        return new HomeFragmentPresenter();
    }

    @Override
    public void onViewStart() {
        super.onViewStart();
        realm = Realm.getDefaultInstance();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                getView().updateRecycler();
            }
        });
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        realm.close();
    }

    @Override
    public void peopleItem(List<PeopleModel> item, int position) {
        getView().goToDetailActivity(item, position);
    }

}
