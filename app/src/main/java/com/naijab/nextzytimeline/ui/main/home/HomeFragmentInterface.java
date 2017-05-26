package com.naijab.nextzytimeline.ui.main.home;

import com.naijab.nextzytimeline.base.BaseMvpInterface;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.List;

public class HomeFragmentInterface {

    public interface View extends BaseMvpInterface.View {
        void goToDetailActivity(List<PeopleModel> item, int position);

        void updateRecycler();
    }

    public interface Presenter extends BaseMvpInterface.Presenter<HomeFragmentInterface.View> {

        void peopleItem(List<PeopleModel> item, int position);

    }
}
