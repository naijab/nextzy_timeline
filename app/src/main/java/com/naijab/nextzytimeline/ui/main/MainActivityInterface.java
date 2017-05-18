package com.naijab.nextzytimeline.ui.main;

import com.naijab.nextzytimeline.base.BaseMvpInterface;

public class MainActivityInterface {


    public interface View extends BaseMvpInterface.View {

        boolean hasTabHome();

        void showFab();

        void hideFab();

    }

    public interface Presenter extends BaseMvpInterface.Presenter<MainActivityInterface.View> {
        void updateFab();
    }
}
