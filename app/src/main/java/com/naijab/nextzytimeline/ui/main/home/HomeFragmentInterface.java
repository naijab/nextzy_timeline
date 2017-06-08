package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpInterface;

public class HomeFragmentInterface {

    public interface View extends BaseMvpInterface.View {
        void startRealm();

        void stopRealm();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<HomeFragmentInterface.View> {
    }
}
