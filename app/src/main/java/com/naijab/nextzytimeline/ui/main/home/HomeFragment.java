package com.naijab.nextzytimeline.ui.main.home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.main.home.adapter.PeopleAdapter;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class HomeFragment extends BaseMvpFragment<HomeFragmentInterface.Presenter>
        implements HomeFragmentInterface.View{

    private RecyclerView recyclerView;
    private RealmResults<PeopleModel> realmResults;
    private Realm realm;
    private List<PeopleModel> peopleItem;
    private PeopleAdapter adapter;

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
    public HomeFragmentInterface.Presenter createPresenter(){
        return HomeFragmentPresenter.create();
    }

    @Override
    public int getLayoutView(){
        return R.layout.fragment_home;
    }

    @Override
    public void bindView( View view ){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_people);
    }

    @Override
    public void setupInstance(){
        realm = Realm.getDefaultInstance();
        realmResults = PeopleManager.getInstance().getPeople();
        peopleItem = new ArrayList<>();
        peopleItem.addAll(realmResults.subList(0, realmResults.size()));
        adapter = new PeopleAdapter(getActivity(), peopleItem);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
//        for (int i = 0; i < realmResults.size(); i++) {
//            PeopleModel items = new PeopleModel();
//            items.setId(PeopleManager.getInstance().getPeople().get(i).getId());
//            items.setName(PeopleManager.getInstance().getPeople().get(i).getName());
//        }
    }

    @Override
    public void setupView(){

    }

    @Override
    public void initialize(){

    }

    @Override
    public void restoreView( Bundle savedInstanceState ){

    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
    }

    @Override
    public void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
    }
}

