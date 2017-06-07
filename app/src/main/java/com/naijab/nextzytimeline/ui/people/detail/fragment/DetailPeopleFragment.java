package com.naijab.nextzytimeline.ui.people.detail.fragment;


import android.content.Intent;
import android.os.Bundle;
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
import com.naijab.nextzytimeline.ui.people.manager.PeopleManager;
import com.naijab.nextzytimeline.ui.people.manager.PeopleModel;

import io.realm.Realm;

public class DetailPeopleFragment extends BaseMvpFragment<DetailPeopleFragmentInterface.Presenter>
        implements DetailPeopleFragmentInterface.View {

    private int id;
    private TextView nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;
    private Realm realm;
    private static final String ID_PEOPLE = "id";
    private static final String SAVE_ID = "saveID";

    public DetailPeopleFragment() {
        super();
    }

    public static DetailPeopleFragment newInstance(int id) {
        DetailPeopleFragment fragment = new DetailPeopleFragment();
        Bundle args = new Bundle();
        args.putInt(ID_PEOPLE, id);
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
        id = getArguments().getInt(ID_PEOPLE);
        getPeopleFromRealm(id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_person:
                Log.i("Detail", "onOptionsItemSelected: app edit");
                goToEditActivity();
                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        getPeopleFromRealm(id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_ID, id);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int idSave = savedInstanceState.getInt(SAVE_ID, 0);
        id = idSave;
    }

    private void getPeopleFromRealm(int id) {
        PeopleModel people = PeopleManager.getInstance().getPeople(id);
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
        getPeopleFromRealm(id);
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
        intent.putExtra(ID_PEOPLE, id);
        startActivity(intent);
        getActivity().finish();
    }

}

