package com.naijab.nextzytimeline.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseActivity;
import com.naijab.nextzytimeline.ui.main.home.HomeFragment;
import com.naijab.nextzytimeline.ui.people.addform.AddPeopleActivity;

public class MainActivity extends BaseActivity {

    private static final String ENGLISH = "en";
    private static final String THAI = "th";

    private Toolbar toolbar;
    private FloatingActionButton fab;

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void setupInstance() {
    }

    @Override
    public void setupView() {
        setupToolbar();
        setupFab();
    }

    @Override
    public void initialize() {
        setupFragment();
    }

    private void setupFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit();
    }

    private void setupFab() {
        fab.setOnClickListener(fabListener);
    }

    private View.OnClickListener fabListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToAddPeople();
        }
    };

    private void goToAddPeople(){
        Intent intent = new Intent(MainActivity.this, AddPeopleActivity.class);
        startActivity(intent);
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out );
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home_people, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_english:
                setLanguage(ENGLISH);
                return true;
            case R.id.menu_thai:
                setLanguage(THAI);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

