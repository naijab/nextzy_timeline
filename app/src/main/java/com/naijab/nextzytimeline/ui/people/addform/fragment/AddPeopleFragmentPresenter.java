package com.naijab.nextzytimeline.ui.people.addform.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.manager.PeopleManager;
import com.naijab.nextzytimeline.manager.PeopleModel;

public class AddPeopleFragmentPresenter extends BaseMvpPresenter<AddPeopleFragmentInterface.View>
        implements AddPeopleFragmentInterface.Presenter {

    public static AddPeopleFragmentInterface.Presenter create() {
        return new AddPeopleFragmentPresenter();
    }

    @Override
    public void onViewStart() {
        super.onViewStart();
        getView().startRealm();
    }

    @Override
    public void onViewStop() {
        super.onViewStop();
        getView().stopRealm();
    }


    @Override
    public void saveIntoRealm(PeopleModel peopleModel) {
        PeopleManager.getInstance().saveRealm(peopleModel, new PeopleManager.onSaveCallBack() {
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
}
