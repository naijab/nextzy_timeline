package com.naijab.nextzytimeline.ui.people.editform;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class EditPeopleActivityPresenter extends BaseMvpPresenter<EditPeopleActivityInterface.View>
        implements EditPeopleActivityInterface.Presenter{

    public static EditPeopleActivityInterface.Presenter create(){
        return new EditPeopleActivityPresenter();
    }

}
