package com.naijab.nextzytimeline.template.adapter.pager;


import com.naijab.nextzytimeline.base.exception.MvpViewNotAttachedException;
import java.lang.ref.WeakReference;


public class CustomPagerAdapterPresenter
        implements CustomPagerAdapterInterface.Presenter{

    private WeakReference<CustomPagerAdapterInterface.Adapter> adapter;

    public static CustomPagerAdapterInterface.Presenter create(){
        return new CustomPagerAdapterPresenter();
    }


    @Override
    public void setAdapter( CustomPagerAdapterInterface.Adapter adapter ){
        this.adapter = new WeakReference<>( adapter );
    }

    @Override
    public CustomPagerAdapterInterface.Adapter getAdapter(){
        if( adapter != null ) return adapter.get();
        throw new MvpViewNotAttachedException();
    }

}
