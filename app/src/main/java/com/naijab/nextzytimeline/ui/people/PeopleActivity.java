package com.naijab.nextzytimeline.ui.people;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.people.addform.AddPeopleFragment;

public class PeopleActivity extends BaseMvpActivity<PeopleActivityInterface.Presenter>
        implements PeopleActivityInterface.View {

    private Toolbar toolbar;

    public PeopleActivity() {
        super();
    }

    @Override
    public PeopleActivityInterface.Presenter createPresenter() {
        return PeopleActivityPresenter.create();
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
                .replace(R.id.container, AddPeopleFragment.newInstance())
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

