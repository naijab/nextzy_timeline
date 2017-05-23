package com.naijab.nextzytimeline.ui.people.addform;

import android.content.Context;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import io.realm.Realm;

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
        String name = PeopleManager.getInstance().getPeople().get(4).getName();
        String job = PeopleManager.getInstance().getPeople().get(4).getJob();
        String photos = PeopleManager.getInstance().getPeople().get(4).getPhoto();
        getView().response(name + " " + job + " " + photos);
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        realm.close();
    }


    @Override
    public void saveIntoRealm(PeopleModel peopleModel, Context context) {
        PeopleManager.getInstance().saveRealm(peopleModel, context, new PeopleManager.SaveCallback() {
            @Override
            public void onSaveSuccess(String message) {
                getView().response(message);
            }

            @Override
            public void onSaveError(String message) {
                getView().response(message);
            }
        });
    }
}
