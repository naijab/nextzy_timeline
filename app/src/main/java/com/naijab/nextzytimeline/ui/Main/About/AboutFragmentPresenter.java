package com.naijab.nextzytimeline.ui.Main.About;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class AboutFragmentPresenter extends BaseMvpPresenter<AboutFragmentInterface.View>
        implements AboutFragmentInterface.Presenter{

    public static AboutFragmentInterface.Presenter create(){
        return new AboutFragmentPresenter();
    }

}
