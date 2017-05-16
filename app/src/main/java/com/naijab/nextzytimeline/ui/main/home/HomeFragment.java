package com.naijab.nextzytimeline.ui.main.home;


import android.os.Bundle;
import android.view.View;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;

public class HomeFragment extends BaseMvpFragment<HomeFragmentInterface.Presenter>
        implements HomeFragmentInterface.View{

    public HomeFragment() {
        super();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public HomeFragmentInterface.Presenter createPresenter(){
        return HomeFragmentPresenter.create();
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

