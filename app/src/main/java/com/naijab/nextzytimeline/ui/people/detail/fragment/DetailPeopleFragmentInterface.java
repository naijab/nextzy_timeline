package com.naijab.nextzytimeline.ui.people.detail.fragment;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.manager.PeopleModel;

public class DetailPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View{
        void getPeopleByID(PeopleModel people);

        void startRealm();

        void stopRealm();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<DetailPeopleFragmentInterface.View>{
    }
}
