package com.naijab.nextzytimeline.ui.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseFragment;
import com.naijab.nextzytimeline.manager.PeopleManager;
import com.naijab.nextzytimeline.manager.PeopleModel;
import com.naijab.nextzytimeline.ui.main.home.adapter.PeopleAdapter;
import com.naijab.nextzytimeline.ui.people.detail.DetailPeopleActivity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class HomeFragment extends BaseFragment {

    private final static String ID = "id";

    private RecyclerView recyclerView;
    private PeopleAdapter adapter;
    private Realm realm;
    private RealmResults<PeopleModel> realmResults;
    private List<PeopleModel> peopleItem;

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
    public int getLayoutView() {
        return R.layout.fragment_home;
    }

    @Override
    public void bindView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_people);
    }

    @Override
    public void setupInstance() {
        setHasOptionsMenu(true);
        realm = PeopleManager.getInstance().getRealm();
        realm.addChangeListener(new RealmChangeListener<Realm>() {
            @Override
            public void onChange(Realm element) {
                setupRealmByLatest();
            }
        });
    }

    @Override
    public void setupView() {}

    @Override
    public void initialize() {
        setupRealmByLatest();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort_by_latest:
                setupRealmByLatest();
                setupRecyclerViewToList();
                return true;
            case R.id.sort_by_name:
                setupRealmByName();
                setupRecyclerViewToList();
                return true;
            case R.id.sort_by_position:
                setupRealmByPosition();
                setupRecyclerViewToList();
                return true;
        }
        return false;
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

    private void setupRealmByLatest() {
        realmResults = PeopleManager.getInstance().getPeople();
        peopleItem = new ArrayList<>();
        peopleItem.addAll(realmResults.subList(0, realmResults.size()));
    }

    private void setupRealmByName() {
        realmResults = PeopleManager.getInstance().getPeopleByName();
        peopleItem = new ArrayList<>();
        peopleItem.addAll(realmResults.subList(0, realmResults.size()));
    }

    private void setupRealmByPosition() {
        realmResults = PeopleManager.getInstance().getPeopleByPosition();
        peopleItem = new ArrayList<>();
        peopleItem.addAll(realmResults.subList(0, realmResults.size()));
    }

    private void setupRecyclerViewToList() {
        adapter = new PeopleAdapter(getActivity(), peopleItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        onRecyclerItemClick();
    }

    private void onRecyclerItemClick() {
        adapter.setOnClickPeopleItem(new PeopleAdapter.OnAdapterListener() {
            @Override
            public void onClickAdapter(List<PeopleModel> item, int position) {
                goToDetailActivity(item, position);
            }
        });
    }

    public void goToDetailActivity(List<PeopleModel> item, int position) {
        Intent intent = new Intent(getActivity(), DetailPeopleActivity.class);
        intent.putExtra(ID, item.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        realm.removeAllChangeListeners();
    }

    @Override
    public void onStop() {
        super.onStop();
        realm.removeAllChangeListeners();
    }
}

