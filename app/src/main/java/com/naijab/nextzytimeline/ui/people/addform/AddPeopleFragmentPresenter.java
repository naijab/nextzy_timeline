package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class AddPeopleFragmentPresenter extends BaseMvpPresenter<AddPeopleFragmentInterface.View>
        implements AddPeopleFragmentInterface.Presenter{

    public static AddPeopleFragmentInterface.Presenter create(){
        return new AddPeopleFragmentPresenter();
    }

}
