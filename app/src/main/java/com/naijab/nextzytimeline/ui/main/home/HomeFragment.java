package com.naijab.nextzytimeline.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.main.home.adapter.PeopleAdapter;
import com.naijab.nextzytimeline.ui.people.detail.DetailPeopleActivity;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeFragment
        extends BaseMvpFragment<HomeFragmentInterface.Presenter>
        implements HomeFragmentInterface.View, RealmChangeListener {

    private RecyclerView recyclerView;
    private PeopleAdapter adapter;
    private Realm realm;
    private RealmResults<PeopleModel> realmResults;
    private List<PeopleModel> peopleItem;
    private final static String ID = "id";

    public HomeFragment() {
        super();
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public HomeFragmentInterface.Presenter createPresenter() {
        return HomeFragmentPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_home;
    }

    @Override
    public void bindView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_people);
    }

    @Override
    public void setupInstance() {
        setupRealm();
    }

    @Override
    public void setupView() {
        setupRecyclerView();
    }

    @Override
    public void initialize() {
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setupRealm() {
        realmResults = PeopleManager.getInstance(realm).getPeople();
        peopleItem = new ArrayList<>();
        peopleItem.addAll(realmResults.subList(0, realmResults.size()));
    }

    private void setupRecyclerView() {
        adapter = new PeopleAdapter(getActivity(), peopleItem);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        onClick();
    }

    private void onClick() {
        adapter.setOnClickPeopleItem(new PeopleAdapter.OnAdapterListener() {
            @Override
            public void onClickAdapter(List<PeopleModel> item, int position) {
                getPresenter().peopleItem(item, position);
            }
        });
    }


    @Override
    public void goToDetailActivity(List<PeopleModel> item, int position) {
        Intent intent = new Intent(getActivity(), DetailPeopleActivity.class);
        intent.putExtra(ID, item.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onChange(Object element) {
        adapter.notifyDataSetChanged();
        setupRecyclerView();
    }
}

