package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import io.realm.Realm;
import io.realm.RealmResults;

public class AddPeopleFragmentPresenter extends BaseMvpPresenter<AddPeopleFragmentInterface.View>
        implements AddPeopleFragmentInterface.Presenter {

    private Realm realm;

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
    public void saveIntoRealm(final String name,
                              final String birthday,
                              final String startJob,
                              final String job,
                              final String game,
                              final String smartphone,
                              final String photos) {


        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                RealmResults<PeopleModel> resultRealm = realm.where(PeopleModel.class)
                        .findAll();
                int id = resultRealm.size() + 1;

                PeopleModel people = realm.createObject(PeopleModel.class, id);
                people.setName(name);
                people.setBirthday(birthday);
                people.setJobstart(startJob);
                people.setJob(job);
                people.setGame(game);
                people.setSmartphone(smartphone);
                people.setPhoto(photos);
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
