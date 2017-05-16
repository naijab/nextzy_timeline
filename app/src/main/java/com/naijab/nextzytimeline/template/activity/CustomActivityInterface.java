package com.naijab.nextzytimeline.template.activity;


import com.naijab.nextzytimeline.base.BaseMvpInterface;

/**
 * Created by TheKhaeng on 9/20/2016.
 */

public class CustomActivityInterface{


    public interface View extends BaseMvpInterface.View{
    }

    public interface Presenter extends BaseMvpInterface.Presenter<View>{
    }
}
