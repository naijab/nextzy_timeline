package com.naijab.nextzytimeline.ui.people.manager;

import com.naijab.nextzytimeline.utility.StringUtility;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

// TODO This class should be in "ui" package?
public class PeopleManager {

    private static PeopleManager peopleManager;
    private RealmResults<PeopleModel> resultRealm;
    private PeopleModel peopleItem;
    private Realm realm = Realm.getDefaultInstance();
    private int id = 0;

    public PeopleManager(Realm realm) {
        this.realm = realm;
    }

    public static PeopleManager getInstance(Realm realm) {
        if (peopleManager == null) {
            peopleManager = new PeopleManager(realm);
        }
        return peopleManager;
    }

    public Realm getRealm() {
        return realm;
    }

    public RealmResults<PeopleModel> getPeople() {
        realm = Realm.getDefaultInstance();
        return realm.where(PeopleModel.class)
                .findAllSorted("id", Sort.DESCENDING);
    }

    public RealmResults<PeopleModel> getPeopleByName() {
        realm = Realm.getDefaultInstance();
        return realm.where(PeopleModel.class)
                .findAllSorted("name", Sort.ASCENDING);
    }

    public RealmResults<PeopleModel> getPeopleByPosition() {
        realm = Realm.getDefaultInstance();
        return realm.where(PeopleModel.class)
                .findAllSorted("job", Sort.ASCENDING);
    }

    public PeopleModel getPeople(int id) {
        return realm.where(PeopleModel.class)
                .equalTo("id", id)
                .findFirst();
    }

    public void deleteAll(final onDeleteCallBack callBack) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(PeopleModel.class);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onDeleteSuccess(StringUtility.getDeleteSuccess());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onDeleteError(StringUtility.getDeleteError() + ": " + error.getMessage());
            }
        });

    }

    public void deleteByID(final int id,
                           final onDeleteCallBack callBack) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                RealmResults<PeopleModel> results = bgRealm.where(PeopleModel.class)
                        .equalTo("id", id)
                        .findAll();
                results.deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onDeleteSuccess(StringUtility.getDeleteSuccess());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onDeleteError(StringUtility.getDeleteError() + ": " + error.getMessage());
            }
        });

    }

    public void saveRealm(final PeopleModel peopleModel,
                          final onSaveCallBack callBack) {

        resultRealm = realm.where(PeopleModel.class)
                .findAll();

        id = resultRealm.size() + 1;

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                PeopleModel people = bgRealm.createObject(PeopleModel.class, id);
                people.setName(peopleModel.getName());
                people.setJob(peopleModel.getJob());
                people.setBirthDay(peopleModel.getBirthDay());
                people.setJobStart(peopleModel.getJobStart());
                people.setJobDescription(peopleModel.getJobDescription());
                people.setGame(peopleModel.getGame());
                people.setSmartPhone(peopleModel.getSmartPhone());
                people.setPhoto(peopleModel.getPhoto());
                people.setProfile(peopleModel.getProfile());


            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onSaveSuccess(StringUtility.getSaveSuccess());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onSaveError(StringUtility.getDeleteError() + ": " + error.getMessage());
            }
        });
    }


    public void editRealm(final PeopleModel peopleModel,
                          final onEditCallBack callBack) {


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                RealmResults<PeopleModel> results = bgRealm.where(PeopleModel.class)
                        .findAll();

                peopleItem = results.where().equalTo("id", peopleModel.getId())
                        .findFirst();

                peopleItem.setName(peopleModel.getName());
                peopleItem.setJob(peopleModel.getJob());
                peopleItem.setBirthDay(peopleModel.getBirthDay());
                peopleItem.setJobStart(peopleModel.getJobStart());
                peopleItem.setJobDescription(peopleModel.getJobDescription());
                peopleItem.setGame(peopleModel.getGame());
                peopleItem.setSmartPhone(peopleModel.getSmartPhone());
                peopleItem.setPhoto(peopleModel.getPhoto());
                peopleItem.setProfile(peopleModel.getProfile());

                bgRealm.copyToRealmOrUpdate(peopleItem);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                callBack.onSaveSuccess(StringUtility.getSaveSuccess());
                realm.close();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                callBack.onSaveError(StringUtility.getSaveError() + ": " + error.getMessage());
            }
        });
    }


    public interface onSaveCallBack {
        void onSaveSuccess(String message);

        void onSaveError(String message);
    }

    public interface onEditCallBack {
        void onSaveSuccess(String message);

        void onSaveError(String message);
    }

    public interface onDeleteCallBack {
        void onDeleteSuccess(String message);

        void onDeleteError(String message);
    }

}
