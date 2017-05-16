package com.naijab.nextzytimeline.ui.Home;

import com.naijab.nextzytimeline.base.BaseMvpInterface;

public class HomeFragmentInterface {

    public interface View extends BaseMvpInterface.View{
    }

    public interface Presenter extends BaseMvpInterface.Presenter<HomeFragmentInterface.View>{
    }
}
