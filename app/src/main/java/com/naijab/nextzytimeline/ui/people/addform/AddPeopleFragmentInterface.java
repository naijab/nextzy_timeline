package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpInterface;

public class AddPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View {

        void response(String message);

    }

    public interface Presenter extends BaseMvpInterface.Presenter<AddPeopleFragmentInterface.View> {

        void saveIntoRealm(String name,
                           String birthday,
                           String startJob,
                           String job,
                           String game,
                           String smartphone,
                           String photos);

    }
}
