package com.naijab.nextzytimeline.ui.people.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.people.detail.fragment.DetailPeopleFragment;

public class DetailPeopleActivity extends BaseMvpActivity<DetailPeopleActivityInterface.Presenter>
        implements DetailPeopleActivityInterface.View {

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private int id;
    private static final String ID_PEOPLE = "id";

    public DetailPeopleActivity() {
        super();
    }

    @Override
    public DetailPeopleActivityInterface.Presenter createPresenter() {
        return DetailPeopleActivityPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_detail;
    }

    @Override
    public void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) toolbar.findViewById(R.id.toolbar_tv_title);
    }

    @Override
    public void setupInstance() {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID_PEOPLE, 0);
    }

    @Override
    public void setupView() {
        setupToolbar();
        toolbarTitle.setText(getString(R.string.detail_people));
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowHomeEnabled(true);
            actionbar.setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void initialize() {
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, DetailPeopleFragment.newInstance(id))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit_people, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID_PEOPLE, id);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getInt(ID_PEOPLE);
    }

}

