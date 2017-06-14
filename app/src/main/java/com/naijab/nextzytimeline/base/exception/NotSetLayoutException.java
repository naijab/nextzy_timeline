package com.naijab.nextzytimeline.base.exception;

/**
 * Created by TheKhaeng on 12/18/2016.
 */

public class NotSetLayoutException extends RuntimeException{
    public NotSetLayoutException(){
        super( "getLayoutView() not return 0" );
    }
}

