package com.naijab.nextzytimeline.ui.people.addform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class PeopleActivityPresenter extends BaseMvpPresenter<PeopleActivityInterface.View>
        implements PeopleActivityInterface.Presenter{

    public static PeopleActivityInterface.Presenter create(){
        return new PeopleActivityPresenter();
    }

}
