package com.naijab.nextzytimeline.ui.Main.Home;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class HomeFragmentPresenter extends BaseMvpPresenter<HomeFragmentInterface.View>
        implements HomeFragmentInterface.Presenter{

    public static HomeFragmentInterface.Presenter create(){
        return new HomeFragmentPresenter();
    }

}
