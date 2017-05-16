package com.naijab.nextzytimeline.ui.Main;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.ui.Main.MainActivityInterface.View;

public class MainActivityPresenter extends BaseMvpPresenter<View>
        implements MainActivityInterface.Presenter{

    public static MainActivityInterface.Presenter create(){
        return new MainActivityPresenter();
    }

}
