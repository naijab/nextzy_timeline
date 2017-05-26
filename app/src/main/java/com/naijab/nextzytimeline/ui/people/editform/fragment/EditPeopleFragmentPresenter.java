package com.naijab.nextzytimeline.ui.people.editform.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

import io.realm.Realm;

public class EditPeopleFragmentPresenter extends BaseMvpPresenter<EditPeopleFragmentInterface.View>
        implements EditPeopleFragmentInterface.Presenter {

    private Realm realm;

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
}
