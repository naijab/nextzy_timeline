package com.naijab.nextzytimeline.ui.people.addform.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseFragment;
import com.naijab.nextzytimeline.manager.PeopleManager;
import com.naijab.nextzytimeline.manager.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class AddPeopleFragment extends BaseFragment {

    public static final String PROFILE_URI = "profile_uri";
    public static final String PHOTO_URI = "photo_uri";
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;
    private String profile, photo;

    public AddPeopleFragment() {
        super();
    }

    public static AddPeopleFragment newInstance() {
        AddPeopleFragment fragment = new AddPeopleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_add_people;
    }

    @Override
    public void bindView(View view) {
        edtName = (EditText) view.findViewById(R.id.edt_name_and_lastname);
        edtJob = (EditText) view.findViewById(R.id.edt_job);
        edtDateBirth = (EditText) view.findViewById(R.id.edt_birthday);
        edtDateJob = (EditText) view.findViewById(R.id.edt_startjob);
        edtJobDescription = (EditText) view.findViewById(R.id.edt_job_description);
        edtGame = (EditText) view.findViewById(R.id.edt_game);
        edtSmartPhone = (EditText) view.findViewById(R.id.edt_your_phone);
        ivPhoto = (ImageView) view.findViewById(R.id.iv_photo);
        ivProfile = (ImageView) view.findViewById(R.id.iv_profile);
        fabPhoto = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void setupInstance() {
        setHasOptionsMenu(true);
    }

    @Override
    public void setupView() {
        edtDateBirth.setOnClickListener(showDatePicker);
        edtDateJob.setOnClickListener(showDatePicker);
        setupTakeCameraListener();
    }

    private void setupTakeCameraListener() {
        ivProfile.setImageResource(R.drawable.ic_profile_circle);
        ivPhoto.setImageResource(R.drawable.ic_selfie);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeProfile();
            }
        });
        fabPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add_person:
                checkForm();
                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        if (photo == null) {
            ivPhoto.setImageResource(R.drawable.ic_profile_circle);
        } else {
            setProfile(photo);
        }

        if (profile == null) {
            ivPhoto.setImageResource(R.drawable.ic_selfie);
        } else {
            setProfile(profile);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PROFILE_URI, photo);
        outState.putString(PHOTO_URI, profile);
    }

    @Override
    protected void onRestoreArguments(Bundle arguments) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        photo = savedInstanceState.getString(PROFILE_URI);
        profile = savedInstanceState.getString(PROFILE_URI);
    }

    private boolean isFormValidate(String name,
                                String dateBirth,
                                String dateJob,
                                String job,
                                String jobDescription,
                                String game,
                                String smartPhone) {

        if (TextUtils.isEmpty(name)) {
            showToast(getString(R.string.error_form_name));
            return false;
        } else if (TextUtils.isEmpty(dateBirth)) {
            showToast(getString(R.string.error_form_date_birth));
            return false;
        } else if (TextUtils.isEmpty(dateJob)) {
            showToast(getString(R.string.error_form_date_job));
            return false;
        } else if (TextUtils.isEmpty(job)) {
            showToast(getString(R.string.error_form_job));
            return false;
        } else if (TextUtils.isEmpty(jobDescription)) {
            showToast(getString(R.string.error_form_job_description));
            return false;
        } else if (TextUtils.isEmpty(game)) {
            showToast(getString(R.string.error_form_game));
            return false;
        } else if (TextUtils.isEmpty(smartPhone)) {
            showToast(getString(R.string.error_form_smartphone));
            return false;
        } else if (TextUtils.isEmpty(photo)) {
            showToast(getString(R.string.error_form_photo));
            return false;
        } else if (TextUtils.isEmpty(profile)) {
            showToast(getString(R.string.error_form_profile));
            return false;
        }else {
            return true;
        }
    }

    private void checkForm() {

        String name = edtName.getText().toString();
        String dateBirth = edtDateBirth.getText().toString();
        String dateJob = edtDateJob.getText().toString();
        String job = edtJob.getText().toString();
        String jobDescription = edtJobDescription.getText().toString();
        String game = edtGame.getText().toString();
        String smartPhone = edtSmartPhone.getText().toString();

        if (isFormValidate(name, dateBirth, dateJob, job, jobDescription, game, smartPhone)){
            PeopleModel people = new PeopleModel();
            people.setName(name);
            people.setJob(job);
            people.setBirthDay(dateBirth);
            people.setJobStart(dateJob);
            people.setJobDescription(jobDescription);
            people.setGame(game);
            people.setSmartPhone(smartPhone);
            people.setProfile(photo);
            people.setPhoto(profile);

            saveIntoRealm(people);
        }
    }

    private void saveIntoRealm(PeopleModel peopleModel) {
        PeopleManager.getInstance().saveRealm(peopleModel, new PeopleManager.onSaveCallBack() {
            @Override
            public void onSaveSuccess(String message) {
                showToast(message);
                saveIsFinish(true);
            }

            @Override
            public void onSaveError(String message) {
                saveIsFinish(false);
                showToast(message);
            }
        });
    }

    public void saveIsFinish(boolean isSuccess) {
        if (isSuccess)
            finishView();
    }

    private void finishView() {
        getActivity().finish();
    }

    private void takeProfile() {
        takePhotoFromCamera("IMG_Nextzy_Profile_", REQUEST_CAMERA_PROFILE);
    }

    private void takePhoto() {
        takePhotoFromCamera("IMG_Nextzy_Photo_", REQUEST_CAMERA_PHOTO);
    }

    private void takePhotoFromCamera(String pathImage, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFilename = pathImage + timestamp + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),
                "DCIM/Camera/" + imageFilename);
        Uri uriPhoto = Uri.fromFile(file);
        if (requestCode == REQUEST_CAMERA_PROFILE){
            profile = uriPhoto.toString();
        }else {
            photo = uriPhoto.toString();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
        startActivityForResult(Intent.createChooser(intent,
                getString(R.string.take_with)), requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            setProfile(profile);
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            setPhoto(photo);
        }
    }

    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideKeyboard();
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            getDatePicker(view, year, month, day);
        }
    };

    private void getDatePicker(final View view,
                               int mYear,
                               int mMonth,
                               int mDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearPick, int monthPick, int dayOfMonthPick) {
                        if (view == edtDateBirth) {
                            setDateBirth(yearPick, monthPick + 1, dayOfMonthPick);
                        }
                        if (view == edtDateJob) {
                            setDateJob(yearPick, monthPick + 1, dayOfMonthPick);
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void setDateBirth(int year,
                              int monthOfYear,
                              int dayOfMonth) {
        edtDateBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }

    private void setDateJob(int year,
                            int monthOfYear,
                            int dayOfMonth) {
        edtDateJob.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }

    private void setProfile(String urlProfile) {
        Glide.with(getActivity())
                .load(urlProfile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivProfile);
    }

    private void setPhoto(String urlPhoto) {
        Glide.with(getActivity())
                .load(urlPhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivPhoto);
    }

}

