package com.naijab.nextzytimeline.ui.people.editform;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.people.editform.fragment.EditPeopleFragment;

public class EditPeopleActivity extends BaseMvpActivity<EditPeopleActivityInterface.Presenter>
        implements EditPeopleActivityInterface.View {

    private Toolbar toolbar;
    private int id;
    private static final String ID_PEOPLE = "id";

    public EditPeopleActivity() {
        super();
    }

    @Override
    public EditPeopleActivityInterface.Presenter createPresenter() {
        return EditPeopleActivityPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_edit;
    }

    @Override
    public void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
    }

    @Override
    public void setupInstance() {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID_PEOPLE, 0);
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
                .replace(R.id.container, EditPeopleFragment.newInstance(id))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_add_people, menu);
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

