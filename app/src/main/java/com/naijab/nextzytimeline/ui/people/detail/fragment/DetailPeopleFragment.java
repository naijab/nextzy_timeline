package com.naijab.nextzytimeline.ui.people.detail.fragment;


import android.os.Bundle;
import android.view.View;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;

public class DetailPeopleFragment extends BaseMvpFragment<DetailPeopleFragmentInterface.Presenter>
        implements DetailPeopleFragmentInterface.View{

    public DetailPeopleFragment() {
        super();
    }

    public static DetailPeopleFragment newInstance() {
        DetailPeopleFragment fragment = new DetailPeopleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public DetailPeopleFragmentInterface.Presenter createPresenter(){
        return DetailPeopleFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_home;
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

