package com.naijab.nextzytimeline.ui.Main.Contact;


import android.os.Bundle;
import android.view.View;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;

public class ContactFragment extends BaseMvpFragment<ContactFragmentInterface.Presenter>
        implements ContactFragmentInterface.View{

    public ContactFragment() {
        super();
    }

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public ContactFragmentInterface.Presenter createPresenter(){
        return ContactFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_contact;
    }

    @Override
    public void bindView( View view ){

    }

    @Override
    public void setupInstance(){

    }

    @Override
    public void setupView(){

    }

    @Override
    public void initialize(){

    }

    @Override
    public void restoreView( Bundle savedInstanceState ){

    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
    }
}

