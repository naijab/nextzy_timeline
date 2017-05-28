package com.naijab.nextzytimeline.ui.people.addform.fragment;

import android.content.Context;
import android.util.Log;

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
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        realm.close();
    }


    @Override
    public void saveIntoRealm(PeopleModel peopleModel, Context context) {
        PeopleManager.getInstance(realm).saveRealm(peopleModel, context, new PeopleManager.onSaveCallBack() {
            @Override
            public void onSaveSuccess(String message) {
                getView().response(message);
                getView().saveIsFinish(true);
            }

            @Override
            public void onSaveError(String message) {
                getView().response(message);
            }
        });
    }

    @Override
    public void deleteAllRealm(Context context) {
        PeopleManager.getInstance(realm).deleteAll(context, new PeopleManager.onDeleteCallBack() {
            @Override
            public void onDeleteSuccess(String message) {
                getView().response(message);
            }

            @Override
            public void onDeleteError(String message) {
                getView().response(message);
            }
        });
    }
}
