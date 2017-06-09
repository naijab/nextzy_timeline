package com.naijab.nextzytimeline.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.akexorcist.localizationactivity.LocalizationActivity;

public abstract class BaseActivity extends LocalizationActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutResId = getLayoutView();
        setContentView(layoutResId);
        bindView();
        setupInstance();
        setupView();
        if( savedInstanceState == null ){
            initialize();
        }
    }

    public abstract int getLayoutView();

    public abstract void bindView();

    public abstract void setupInstance();

    public abstract void setupView();

    public abstract void initialize();

}
