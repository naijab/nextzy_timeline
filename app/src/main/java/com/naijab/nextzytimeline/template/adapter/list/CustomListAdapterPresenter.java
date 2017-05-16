package com.naijab.nextzytimeline.template.adapter.list;


import com.naijab.nextzytimeline.base.adapter.loadmore.LoadmoreAdapterPresenter;
import com.naijab.nextzytimeline.template.adapter.list.CustomListAdapterInterface.Adapter;

public class CustomListAdapterPresenter extends LoadmoreAdapterPresenter<Adapter>
        implements CustomListAdapterInterface.Presenter{

    public static CustomListAdapterInterface.Presenter create(){
        return new CustomListAdapterPresenter();
    }
}
