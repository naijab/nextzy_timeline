package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddPeopleFragmentPresenter extends BaseMvpPresenter<AddPeopleFragmentInterface.View>
        implements AddPeopleFragmentInterface.Presenter {

    private Realm realm;
    private RealmResults<PeopleModel> resultRealm;
    int id = 0;

    public static AddPeopleFragmentInterface.Presenter create() {
        return new AddPeopleFragmentPresenter();
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


    @Override
    public void saveIntoRealm(final PeopleModel peopleModel) {

        resultRealm = realm.where(PeopleModel.class)
                .findAll();
        id = resultRealm.size() + 1;

        Realm realm = Realm.getDefaultInstance();
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
                getView().response("onSuccess");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                getView().response(error.getMessage());
            }
        });
    }
}
