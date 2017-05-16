package com.naijab.nextzytimeline.ui.main.contact;

import com.naijab.nextzytimeline.base.BaseMvpPresenter;

public class ContactFragmentPresenter extends BaseMvpPresenter<ContactFragmentInterface.View>
    implements ContactFragmentInterface.Presenter {

  public static ContactFragmentInterface.Presenter create() {
    return new ContactFragmentPresenter();
  }

}
