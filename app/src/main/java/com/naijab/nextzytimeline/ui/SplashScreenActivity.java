package com.naijab.nextzytimeline.ui;

import android.os.Bundle;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.SplashScreenActivityInterface.Presenter;

public class SplashScreenActivity extends BaseMvpActivity<Presenter>
        implements SplashScreenActivityInterface.View{


    @Override
    public Presenter createPresenter(){
        return SplashScreenActivityPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return 0;
    }


    @Override
    public void bindView(){
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
    protected void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
    }
}

