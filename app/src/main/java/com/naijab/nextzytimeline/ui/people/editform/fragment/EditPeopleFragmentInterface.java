package com.naijab.nextzytimeline.ui.people.editform.fragment;

import android.content.Context;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

public class EditPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View {
        void response(String message);

        void getPeopleEdit(PeopleModel people);

        void saveIsFinish(boolean isSuccess);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<EditPeopleFragmentInterface.View> {
        void getPeople(int id);

        void updateRealm(PeopleModel people,  Context context);

        void deleteByID(int id, Context context);
    }
}