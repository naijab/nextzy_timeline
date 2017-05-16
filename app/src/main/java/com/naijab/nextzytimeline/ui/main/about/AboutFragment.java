package com.naijab.nextzytimeline.ui.main.about;


import android.os.Bundle;
import android.view.View;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;

public class AboutFragment extends BaseMvpFragment<AboutFragmentInterface.Presenter>
        implements AboutFragmentInterface.View{

    public AboutFragment() {
        super();
    }

    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public AboutFragmentInterface.Presenter createPresenter(){
        return AboutFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_about;
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

