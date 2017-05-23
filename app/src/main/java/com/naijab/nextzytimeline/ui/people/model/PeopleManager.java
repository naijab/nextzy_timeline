package com.naijab.nextzytimeline.ui.people.model;

import io.realm.Realm;
import io.realm.RealmResults;

public class PeopleManager {

    private static PeopleManager peopleManager;
    private Realm realm = Realm.getDefaultInstance();

    public static PeopleManager getInstance() {
        if (peopleManager == null) {
            peopleManager = new PeopleManager();
        }
        return peopleManager;
    }

    public Realm getRealm(){
        return realm;
    }

    public RealmResults<PeopleModel> getPeople() {
        return realm.where(PeopleModel.class)
                .findAll();
    }

    public PeopleModel getPeople(String id) {
        return realm.where(PeopleModel.class).equalTo("id", id).findFirst();
    }

}
