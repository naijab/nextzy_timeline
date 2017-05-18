package com.naijab.nextzytimeline.ui.main;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class MainActivityPresenter extends BaseMvpPresenter<MainActivityInterface.View>
        implements MainActivityInterface.Presenter{

    public static MainActivityInterface.Presenter create(){
        return new MainActivityPresenter();
    }


    @Override
    public void updateFab() {
        if (getView().hasTabHome()){
            getView().showFab();
        }else {
            getView().hideFab();
        }
    }
}
