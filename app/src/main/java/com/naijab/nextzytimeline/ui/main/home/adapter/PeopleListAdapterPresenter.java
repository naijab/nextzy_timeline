package com.naijab.nextzytimeline.ui.main.home.adapter;


import com.naijab.nextzytimeline.base.adapter.loadmore.LoadmoreAdapterPresenter;

public class PeopleListAdapterPresenter
        extends LoadmoreAdapterPresenter<PeopleListAdapterInterface.Adapter>
        implements PeopleListAdapterInterface.Presenter{

    public static PeopleListAdapterInterface.Presenter create(){
        return new PeopleListAdapterPresenter();
    }
}
