package com.naijab.nextzytimeline.ui.people.model;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;

public class PeopleManager {

    private static PeopleManager peopleManager;
    private final Realm realm;

    public PeopleManager(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static PeopleManager with(Fragment fragment) {

        if (peopleManager == null) {
            peopleManager = new PeopleManager(fragment.getActivity().getApplication());
        }
        return peopleManager;
    }

    public static PeopleManager with(Activity activity) {

        if (peopleManager == null) {
            peopleManager = new PeopleManager(activity.getApplication());
        }
        return peopleManager;
    }

    public static PeopleManager with(Application application) {

        if (peopleManager == null) {
            peopleManager = new PeopleManager(application);
        }
        return peopleManager;
    }

    public static PeopleManager getInstance() {
        return peopleManager;
    }

    public Realm getRealm() {
        return realm;
    }

    public RealmResults<PeopleModel> getPeople() {
        return realm.where(PeopleModel.class).findAll();
    }

    public PeopleModel getPeople(String id) {
        return realm.where(PeopleModel.class).equalTo("id", id).findFirst();
    }

}
