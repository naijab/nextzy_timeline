package com.naijab.nextzytimeline.ui;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class SplashScreenActivityPresenter extends BaseMvpPresenter<SplashScreenActivityInterface.View>
        implements SplashScreenActivityInterface.Presenter{

    public static SplashScreenActivityInterface.Presenter create(){
        return new SplashScreenActivityPresenter();
    }

}
