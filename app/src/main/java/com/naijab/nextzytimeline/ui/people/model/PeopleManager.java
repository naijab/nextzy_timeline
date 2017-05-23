package com.naijab.nextzytimeline.ui.people.model;

import android.content.Context;

import com.naijab.nextzytimeline.utility.StringUtility;

import io.realm.Realm;
import io.realm.RealmResults;

public class PeopleManager {

    private static PeopleManager peopleManager;
    private Realm realm = Realm.getDefaultInstance();
    private RealmResults<PeopleModel> resultRealm;
    private int id = 0;

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

    public void deleteAll(final Context context, final onDeleteCallBack callBack){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(PeopleModel.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onDeleteSuccess(StringUtility.getDeleteSuccess(context));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onDeleteError(error.getMessage());
            }
        });

    }

    public void saveRealm(final PeopleModel peopleModel,
                          final Context context,
                          final onSaveCallBack callBack){

        resultRealm = realm.where(PeopleModel.class)
                .findAll();

        id = resultRealm.size() + 1;

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                PeopleModel people = bgRealm.createObject(PeopleModel.class, id);
                people.setName(peopleModel.getName());
                people.setBirthday(peopleModel.getBirthday());
                people.setJobstart(peopleModel.getJobstart());
                people.setJob(peopleModel.getJob());
                people.setGame(peopleModel.getGame());
                people.setSmartphone(peopleModel.getSmartphone());
                people.setPhoto(peopleModel.getPhoto());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onSaveSuccess(StringUtility.getSaveSuccess(context));
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onSaveError(error.getMessage());
            }
        });
    }

    public interface onSaveCallBack {
        void onSaveSuccess(String message);
        void onSaveError(String message);
    }

    public interface onDeleteCallBack {
        void onDeleteSuccess(String message);
        void onDeleteError(String message);
    }

}
