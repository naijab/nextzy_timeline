package com.naijab.nextzytimeline.template.activity;


import android.os.Bundle;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.template.activity.CustomActivityInterface.Presenter;

/**
 * Created by TheKhaeng on 12/17/2016.
 */

public class CustomActivity extends BaseMvpActivity<Presenter>
        implements CustomActivityInterface.View{


    @Override
    public CustomActivityInterface.Presenter createPresenter(){
        return CustomActivityPresenter.create();
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

