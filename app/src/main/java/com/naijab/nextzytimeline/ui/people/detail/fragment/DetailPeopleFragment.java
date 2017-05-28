package com.naijab.nextzytimeline.ui.people.detail.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.people.editform.EditPeopleActivity;
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class DetailPeopleFragment extends BaseMvpFragment<DetailPeopleFragmentInterface.Presenter>
        implements DetailPeopleFragmentInterface.View {

    private int id;
    private TextView nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;
    private static final String ID = "id";
    private Realm realm;

    public DetailPeopleFragment() {
        super();
    }

    public static DetailPeopleFragment newInstance(int id) {
        DetailPeopleFragment fragment = new DetailPeopleFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public DetailPeopleFragmentInterface.Presenter createPresenter() {
        return DetailPeopleFragmentPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_detail;
    }

    @Override
    public void bindView(View view) {
        nameAndLastName = (TextView) view.findViewById(R.id.tv_name);
        job = (TextView) view.findViewById(R.id.tv_job);
        dateBirth = (TextView) view.findViewById(R.id.tv_birthday);
        dateJob = (TextView) view.findViewById(R.id.tv_startjob);
        jobDescription = (TextView) view.findViewById(R.id.tv_job_description);
        game = (TextView) view.findViewById(R.id.tv_game);
        smartPhone = (TextView) view.findViewById(R.id.tv_smartphone);
        photo = (ImageView) view.findViewById(R.id.iv_photo);
        profile = (ImageView) view.findViewById(R.id.iv_profile);
    }

    @Override
    public void setupInstance() {
        setHasOptionsMenu(true);
    }

    @Override
    public void setupView() {
    }

    @Override
    public void initialize() {
        id = getArguments().getInt("id");
        getRealm(id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_edit:
                Log.i("Detail", "onOptionsItemSelected: app edit");
                goToEditActivity();
                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        int idSave = savedInstanceState.getInt("saveID", 0);
        id = idSave;
        getRealm(idSave);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("saveID", id);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void getRealm(int id) {
        PeopleModel people = PeopleManager.getInstance(realm).getPeople(id);
        getPeopleByID(people);
    }

    public void getPeopleByID(PeopleModel people) {
        nameAndLastName.setText(people.getName());
        job.setText(people.getJob());
        dateBirth.setText(people.getBirthDay());
        dateJob.setText(people.getJobStart());
        jobDescription.setText(people.getJobDescription());
        game.setText(people.getGame());
        smartPhone.setText(people.getSmartPhone());
        setProfile(people.getProfile());
        setPhoto(people.getPhoto());
    }

    @Override
    public void updateRecycler() {
        getRealm(id);
    }

    private void setProfile(String urlProfile) {
        Glide.with(getActivity())
                .load(urlProfile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(profile);
    }

    private void setPhoto(String urlPhoto) {
        Glide.with(getActivity())
                .load(urlPhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(photo);
    }


    private void goToEditActivity() {
        Intent intent = new Intent(getActivity(), EditPeopleActivity.class);
        intent.putExtra(ID, id);
        startActivity(intent);
        getActivity().finish();
    }

}

