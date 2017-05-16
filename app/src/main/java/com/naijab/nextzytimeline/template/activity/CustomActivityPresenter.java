package com.naijab.nextzytimeline.template.activity;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.template.activity.CustomActivityInterface.View;

public class CustomActivityPresenter extends BaseMvpPresenter<View>
        implements CustomActivityInterface.Presenter{

    public static CustomActivityInterface.Presenter create(){
        return new CustomActivityPresenter();
    }

}
