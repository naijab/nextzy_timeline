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
        // TODO Don't hardcode the String like this. Use constant (private static final ...)
        id = intent.getIntExtra("id", 0);

        // TODO This code don't related to this method. Shall we move to setupView()?
        toolbarTitle.setText(getString(R.string.detail_people));
    }

    @Override
    public void setupView() {
        setupToolbar();
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
        // TODO Save "id" variable
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO Restore "id" variable
    }

}

