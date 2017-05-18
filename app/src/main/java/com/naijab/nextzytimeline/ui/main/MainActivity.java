package com.naijab.nextzytimeline.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpActivity;
import com.naijab.nextzytimeline.ui.main.adapter.NextzyPagerStateAdapter;
import com.naijab.nextzytimeline.ui.people.PeopleActivity;

public class MainActivity extends BaseMvpActivity<MainActivityInterface.Presenter>
        implements MainActivityInterface.View {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private NextzyPagerStateAdapter nextzyPagerStateAdapter;

    public MainActivity() {
        super();
    }

    @Override
    public MainActivityInterface.Presenter createPresenter() {
        return MainActivityPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView() {
        viewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
    }

    @Override
    public void setupInstance() {
        nextzyPagerStateAdapter = new NextzyPagerStateAdapter(this);
    }

    @Override
    public void setupView() {
        setupToolbar();
        setupViewPager();
        setupTabLayout();
        setupFab();
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
        Intent intent = new Intent(MainActivity.this, PeopleActivity.class);
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

    private void setupViewPager() {
        viewPager.setAdapter(nextzyPagerStateAdapter);
        viewPager.addOnPageChangeListener(onViewPagerPageListener);
    }

    private void setupTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initialize() {
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    boolean isHasAtHome;

    private ViewPager.OnPageChangeListener onViewPagerPageListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    isHasAtHome = true;
                    getPresenter().updateFab();
                    break;

                default:
                    isHasAtHome = false;
                    getPresenter().updateFab();
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @Override
    public boolean hasTabHome() {
        if (isHasAtHome) return true;
        else return false;
    }

    @Override
    public void showFab() {
        fab.show();
    }

    @Override
    public void hideFab() {
        fab.hide();
    }
}

