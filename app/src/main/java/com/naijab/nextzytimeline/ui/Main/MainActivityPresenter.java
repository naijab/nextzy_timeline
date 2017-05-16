package com.naijab.nextzytimeline.ui.Main;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<MainActivityInterface.View>
        implements MainActivityInterface.Presenter{

    public static MainActivityInterface.Presenter create(){
        return new MainActivityPresenter();
    }

}
