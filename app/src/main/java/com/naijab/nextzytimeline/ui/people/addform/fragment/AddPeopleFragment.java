package com.naijab.nextzytimeline.ui.people.addform.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.manager.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static android.app.Activity.RESULT_OK;

@RuntimePermissions
public class AddPeopleFragment extends BaseMvpFragment<AddPeopleFragmentInterface.Presenter>
        implements AddPeopleFragmentInterface.View {

    private Realm realm;
    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;
    private String profileS, photoS;

    public static final String PROFILE_URI = "profile_uri";
    public static final String PHOTO_URI = "photo_uri";
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

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
    public AddPeopleFragmentInterface.Presenter createPresenter() {
        return AddPeopleFragmentPresenter.create();
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
        AddPeopleFragmentPermissionsDispatcher.setupTakeCameraListenerWithCheck(this);
    }

    @Override
    public void setupView() {
        edtDateBirth.setOnClickListener(showDatePicker);
        edtDateJob.setOnClickListener(showDatePicker);
        setupTakeCameraListener();
    }

    @NeedsPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
    void setupTakeCameraListener() {

        ivProfile.setImageResource(R.drawable.ic_profile_circle);
        ivPhoto.setImageResource(R.drawable.ic_selfie);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeProfileFromCamera();
            }
        });
        fabPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhotoFromCamera();
            }
        });
    }

    @OnPermissionDenied(Manifest.permission.READ_EXTERNAL_STORAGE)
    void checkCamera() {
        AddPeopleFragmentPermissionsDispatcher.setupTakeCameraListenerWithCheck(this);
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
                if (checkIsEmpty()) {
                    saveToRealm();
                } else {
                    showToast(getString(R.string.error_form));
                }

                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        if (profileS == null) {
            ivPhoto.setImageResource(R.drawable.ic_profile_circle);
        } else {
            setProfile(profileS);
        }

        if (photoS == null) {
            ivPhoto.setImageResource(R.drawable.ic_selfie);
        } else {
            setProfile(photoS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(PROFILE_URI, profileS);
        outState.putString(PHOTO_URI, photoS);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        profileS = savedInstanceState.getString(PROFILE_URI);
        photoS = savedInstanceState.getString(PROFILE_URI);
    }

    private boolean checkIsEmpty() {
        if (edtName != null &&
                edtDateBirth != null &&
                edtDateJob != null &&
                edtJob != null &&
                edtJobDescription != null &&
                edtGame != null &&
                edtSmartPhone != null &&
                profileS != null &&
                photoS != null) {
            return true;
        }
        return false;
    }

    private void saveToRealm() {
        String nameS = edtName.getText().toString();
        String dateBirthS = edtDateBirth.getText().toString();
        String dateJobS = edtDateJob.getText().toString();
        String jobS = edtJob.getText().toString();
        String jobDescriptionS = edtJobDescription.getText().toString();
        String gameS = edtGame.getText().toString();
        String smartPhoneS = edtSmartPhone.getText().toString();

        PeopleModel people = new PeopleModel();
        people.setName(nameS);
        people.setJob(jobS);
        people.setBirthDay(dateBirthS);
        people.setJobStart(dateJobS);
        people.setJobDescription(jobDescriptionS);
        people.setGame(gameS);
        people.setSmartPhone(smartPhoneS);
        people.setProfile(profileS);
        people.setPhoto(photoS);
        getPresenter().saveIntoRealm(people);
    }


    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard();
            final View vv = v;
            final Calendar calendar;
            calendar = Calendar.getInstance();
            final int mYear = calendar.get(Calendar.YEAR);
            final int mMonth = calendar.get(Calendar.MONTH);
            final int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            getDatePicker(vv, mYear, mMonth, mDay);
        }
    };

    private void getDatePicker(final View vv, int mYear, int mMonth, int mDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (vv.getId() == R.id.edt_birthday) {
                    setDateBirth(year, month + 1, dayOfMonth);
                }
                if (vv.getId() == R.id.edt_startjob) {
                    setDateJob(year, month + 1, dayOfMonth);
                }
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void takeProfileFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFilename = "IMG_Nextzy_Profile_" + timestamp + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),
                "DCIM/Camera/" + imageFilename);
        Uri uriProfile = Uri.fromFile(file);
        profileS = uriProfile.toString();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriProfile);
        startActivityForResult(Intent.createChooser(intent,
                getString(R.string.take_with)), REQUEST_CAMERA_PROFILE);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFilename = "IMG_Nextzy_Photo_" + timestamp + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),
                "DCIM/Camera/" + imageFilename);
        Uri uriPhoto = Uri.fromFile(file);
        photoS = uriPhoto.toString();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
        startActivityForResult(Intent.createChooser(intent,
                getString(R.string.take_with)), REQUEST_CAMERA_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            setProfile(profileS);
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            setPhoto(photoS);
        }

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

    @Override
    public void response(String message) {
        Log.i("AddFragment", "Response: " + message);
        Toast.makeText(getActivity(), " " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveIsFinish(boolean isSuccess) {
        if (isSuccess)
            finishView();
    }

    @Override
    public void startRealm() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public void stopRealm() {
        realm.close();
    }

    private void finishView() {
        getActivity().finish();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AddPeopleFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}

