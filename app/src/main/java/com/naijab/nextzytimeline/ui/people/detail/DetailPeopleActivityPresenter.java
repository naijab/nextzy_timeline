package com.naijab.nextzytimeline.ui.people.detail;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class DetailPeopleActivityPresenter extends BaseMvpPresenter<DetailPeopleActivityInterface.View>
        implements DetailPeopleActivityInterface.Presenter{

    public static DetailPeopleActivityInterface.Presenter create(){
        return new DetailPeopleActivityPresenter();
    }

}
