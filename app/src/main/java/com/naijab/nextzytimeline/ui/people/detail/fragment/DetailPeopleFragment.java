package com.naijab.nextzytimeline.ui.people.detail.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.util.List;

public class DetailPeopleFragment extends BaseMvpFragment<DetailPeopleFragmentInterface.Presenter>
        implements DetailPeopleFragmentInterface.View {

    private int id;
    private TextView nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;

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
        id = getArguments().getInt("id");
        getPresenter().getPeopleDetail(id);
    }

    @Override
    public void setupView() {

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

    @Override
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
}

