package com.naijab.nextzytimeline.ui.people.detail.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.naijab.nextzytimeline.base.BaseFragment;
import com.naijab.nextzytimeline.manager.PeopleManager;
import com.naijab.nextzytimeline.manager.PeopleModel;
import com.naijab.nextzytimeline.ui.people.editform.EditPeopleActivity;

import io.realm.Realm;
import io.realm.RealmChangeListener;

public class DetailPeopleFragment extends BaseFragment {

    private static final String ID_PEOPLE = "id";
    private static final String SAVE_ID = "saveID";
    public static final int REQUEST_ID_PEOPLE = 19;

    private Realm realm;
    private int id;
    private TextView nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Check", "onCreate : " + (savedInstanceState == null));
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
        realm = PeopleManager.getInstance().getRealm();
        realm.addChangeListener(realmChangeListener);
    }

    private RealmChangeListener<Realm> realmChangeListener = new RealmChangeListener<Realm>() {
        @Override
        public void onChange(Realm element) {
            Log.i("DetailFragment", "getPeople in Realm Change listener");
            getPeopleFromRealm(id);
        }
    };

    @Override
    public void setupView() {
    }

    @Override
    public void initialize() {
        Log.i("DetailFragment", "getPeople in Realm initialize: " + id);
        getPeopleFromRealm(id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit_people:
                goToEditActivity();
                return true;
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        Log.i("DetailFragment", "getPeople in restore view");
        getPeopleFromRealm(id);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_ID, id);
    }

    @Override
    protected void onRestoreArguments(Bundle arguments) {
        Log.i("DetailFragment", "getPeople in onRestoreArguments: ");
        id = arguments.getInt(ID_PEOPLE);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("DetailFragment", "getPeople in onRestoreInstanceState: ");

        id = savedInstanceState.getInt(SAVE_ID, 0);
    }

    private void getPeopleFromRealm(int id) {
        Log.i("DetailFragment", "getPeopleFromRealm: " + id);
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
        startActivityForResult(intent, REQUEST_ID_PEOPLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("ID", "onActivityResult: ");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ID_PEOPLE && resultCode == Activity.RESULT_OK){
            Log.e("ID", "Equal");
            id = data.getIntExtra(ID_PEOPLE, 0);
            getPeopleFromRealm(id);
        }else {
            Log.e("ID", "Not Equal");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.removeChangeListener(realmChangeListener);
    }
}

