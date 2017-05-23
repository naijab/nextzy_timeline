package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

public class AddPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View {

        void response(String message);

    }

    public interface Presenter extends BaseMvpInterface.Presenter<AddPeopleFragmentInterface.View> {

        void saveIntoRealm(PeopleModel peopleModel);

    }
}
