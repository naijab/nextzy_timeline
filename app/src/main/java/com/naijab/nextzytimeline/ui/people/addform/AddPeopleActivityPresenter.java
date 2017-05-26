package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class AddPeopleActivityPresenter extends BaseMvpPresenter<AddPeopleActivityInterface.View>
        implements AddPeopleActivityInterface.Presenter{

    public static AddPeopleActivityInterface.Presenter create(){
        return new AddPeopleActivityPresenter();
    }

}
