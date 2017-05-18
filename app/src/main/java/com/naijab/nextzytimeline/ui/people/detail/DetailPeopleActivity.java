package com.naijab.nextzytimeline.ui.people.detail;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.people.addform.AddPeopleFragment;
import com.naijab.nextzytimeline.ui.people.detail.fragment.DetailPeopleFragment;

public class DetailPeopleActivity extends BaseMvpActivity<DetailPeopleActivityInterface.Presenter>
        implements DetailPeopleActivityInterface.View {

    private Toolbar toolbar;

    public DetailPeopleActivity() {
        super();
    }

    @Override
    public DetailPeopleActivityInterface.Presenter createPresenter() {
        return DetailPeopleActivityPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_add;
    }

    @Override
    public void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(false);
            actionbar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void initialize() {
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DetailPeopleFragment.newInstance())
                .commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

}

