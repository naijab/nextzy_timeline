package com.naijab.nextzytimeline.base.exception;

/**
 * Created by TheKhaeng on 12/18/2016.
 */

public class MvpViewNotAttachedException extends RuntimeException{
    public MvpViewNotAttachedException(){
        super( "Please call Presenter.attachView(MvpBaseView) before" +
                " requesting data to the View" );
    }
}

