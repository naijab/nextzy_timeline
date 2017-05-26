package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.List;

public class HomeFragmentInterface {

    public interface View extends BaseMvpInterface.View {
    }

    public interface Presenter extends BaseMvpInterface.Presenter<HomeFragmentInterface.View> {
    }
}
