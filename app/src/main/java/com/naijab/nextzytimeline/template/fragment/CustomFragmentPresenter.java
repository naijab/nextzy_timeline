package com.naijab.nextzytimeline.template.fragment;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;
import com.naijab.nextzytimeline.template.fragment.CustomFragmentInterface.View;

public class CustomFragmentPresenter extends BaseMvpPresenter<View>
        implements CustomFragmentInterface.Presenter{

    public static CustomFragmentInterface.Presenter create(){
        return new CustomFragmentPresenter();
    }

}
