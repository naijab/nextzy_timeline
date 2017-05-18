package com.naijab.nextzytimeline.ui.people.detail.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class DetailPeopleFragmentPresenter extends BaseMvpPresenter<DetailPeopleFragmentInterface.View>
        implements DetailPeopleFragmentInterface.Presenter{

    public static DetailPeopleFragmentInterface.Presenter create(){
        return new DetailPeopleFragmentPresenter();
    }

}
