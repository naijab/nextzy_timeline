package com.naijab.nextzytimeline.ui;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.SplashScreenActivityInterface.View;

public class SplashScreenActivityPresenter extends BaseMvpPresenter<View>
        implements SplashScreenActivityInterface.Presenter{

    public static SplashScreenActivityInterface.Presenter create(){
        return new SplashScreenActivityPresenter();
    }

}
