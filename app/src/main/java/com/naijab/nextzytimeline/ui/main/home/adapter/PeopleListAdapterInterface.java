package com.naijab.nextzytimeline.ui.main.home.adapter;


import com.naijab.nextzytimeline.base.adapter.loadmore.LoadmoreAdapterInterface;

public class PeopleListAdapterInterface {

    public interface Adapter extends LoadmoreAdapterInterface.Adapter{
    }

    public interface Presenter extends  LoadmoreAdapterInterface.Presenter<Adapter>{
    }
}
