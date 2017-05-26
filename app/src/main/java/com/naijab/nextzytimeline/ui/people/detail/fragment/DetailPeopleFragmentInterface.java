package com.naijab.nextzytimeline.ui.people.detail.fragment;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

public class DetailPeopleFragmentInterface {

    public interface View extends BaseMvpInterface.View{
        void getPeopleByID(PeopleModel people);
    }

    public interface Presenter extends BaseMvpInterface.Presenter<DetailPeopleFragmentInterface.View>{
        void getPeopleDetail(int id);
    }
}
