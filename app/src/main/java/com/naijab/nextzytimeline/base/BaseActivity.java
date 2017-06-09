package com.naijab.nextzytimeline.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.akexorcist.localizationactivity.LocalizationActivity;
import com.naijab.nextzytimeline.base.exception.NotSetLayoutException;

public abstract class BaseActivity extends LocalizationActivity {

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutView();
        if( getLayoutView() == 0 ) throw new NotSetLayoutException();
        setContentView(layoutResId);
        bindView();
        setupInstance();
        setupView();
        if( savedInstanceState == null ){
            initialize();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        restoreView(savedInstanceState);
    }

    public void restoreView(Bundle savedInstanceState) {}

    public abstract int getLayoutView();

    public abstract void bindView();

    public abstract void setupInstance();

    public abstract void setupView();

    public abstract void initialize();

}
