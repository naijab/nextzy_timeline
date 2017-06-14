package com.naijab.nextzytimeline.ui.people.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseActivity;
import com.naijab.nextzytimeline.ui.people.detail.fragment.DetailPeopleFragment;

public class DetailPeopleActivity extends BaseActivity {

    private static final String ID_PEOPLE = "id";
    private static final String TAG_FRAGMENT_DETAIL = "DetailFragment";
    public static final int REQUEST_ID_PEOPLE = 19;

    private Toolbar toolbar;
    private TextView toolbarTitle;
    private int id;

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
        Log.i("DetailActivity", "Fragment born ");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DetailPeopleFragment.newInstance(id), TAG_FRAGMENT_DETAIL)
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
            if (requestCode == REQUEST_ID_PEOPLE && resultCode == RESULT_OK) {
                id = data.getIntExtra(ID_PEOPLE, 0);
                Log.i("IDActivity", "onActivityResult: " + id);
                Fragment fragment = getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_DETAIL);
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}

