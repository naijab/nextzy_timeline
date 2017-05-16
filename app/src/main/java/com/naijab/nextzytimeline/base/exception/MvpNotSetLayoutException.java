package com.naijab.nextzytimeline.base.exception;

/**
 * Created by TheKhaeng on 12/18/2016.
 */

public class MvpNotSetLayoutException extends RuntimeException{
    public MvpNotSetLayoutException(){
        super( "getLayoutView() not return 0" );
    }
}

