package com.naijab.nextzytimeline.template.fragment;


import android.os.Bundle;
import android.view.View;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.template.fragment.CustomFragmentInterface.Presenter;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class CustomFragment extends BaseMvpFragment<Presenter>
        implements CustomFragmentInterface.View{

    public CustomFragment() {
        super();
    }

    public static CustomFragment newInstance() {
        CustomFragment fragment = new CustomFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CustomFragmentInterface.Presenter createPresenter(){
        return CustomFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return 0;
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

