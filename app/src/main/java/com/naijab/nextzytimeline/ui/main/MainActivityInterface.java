package com.naijab.nextzytimeline.ui.main;

import com.naijab.nextzytimeline.base.BaseMvpInterface;

public class MainActivityInterface {


    public interface View extends BaseMvpInterface.View{
    }

    public interface Presenter extends BaseMvpInterface.Presenter<MainActivityInterface.View>{
    }
}
