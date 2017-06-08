package com.naijab.nextzytimeline.ui.people.addform.fragment;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.manager.PeopleModel;

public class AddPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View {

        void response(String message);

        void saveIsFinish(boolean isSuccess);

        void startRealm();

        void stopRealm();

    }

    public interface Presenter extends BaseMvpInterface.Presenter<AddPeopleFragmentInterface.View> {

        void saveIntoRealm(PeopleModel peopleModel);

    }
}
