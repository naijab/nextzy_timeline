package com.naijab.nextzytimeline.ui.people.editform.fragment;

import android.content.Context;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;
import com.naijab.nextzytimeline.utility.StringUtility;

import io.realm.Realm;

public class EditPeopleFragmentPresenter extends BaseMvpPresenter<EditPeopleFragmentInterface.View>
        implements EditPeopleFragmentInterface.Presenter {

    Realm realm;

    public static EditPeopleFragmentInterface.Presenter create() {
        return new EditPeopleFragmentPresenter();
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
    public void getPeople(int id) {
        PeopleModel people = PeopleManager.getInstance(realm).getPeople(id);
        getView().getPeopleEdit(people);
    }

    @Override
    public void updateRealm(final PeopleModel people,
                            final Context context) {
       PeopleManager.getInstance(realm).editRealm(people, context, new PeopleManager.onEditCallBack() {
           @Override
           public void onSaveSuccess(String message) {
               getView().response(message);
               getView().saveIsFinish(true);
           }

           @Override
           public void onSaveError(String message) {
               getView().response(message);
               getView().saveIsFinish(false);
           }
       });
    }

    @Override
    public void deleteByID(int id, Context context) {
        PeopleManager.getInstance(realm).deleteByID(id, context, new PeopleManager.onDeleteCallBack() {
            @Override
            public void onDeleteSuccess(String message) {
                getView().response(message);
                getView().saveIsFinish(true);
            }

            @Override
            public void onDeleteError(String message) {
                getView().response(message);
                getView().saveIsFinish(false);
            }
        });
    }

}
