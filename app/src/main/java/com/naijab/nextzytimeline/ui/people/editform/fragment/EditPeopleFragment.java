package com.naijab.nextzytimeline.ui.people.editform.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.people.manager.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

import static android.app.Activity.RESULT_OK;

@RuntimePermissions
public class EditPeopleFragment extends BaseMvpFragment<EditPeopleFragmentInterface.Presenter>
        implements EditPeopleFragmentInterface.View {

    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;
    private Button btnDelete;
    private int id;
    private String nameS, dateBirthS, dateJobS, jobS, jobDescriptionS, gameS, smartPhoneS;
    private String profileS, photoS;

    private static final String ID_PEOPLE = "id";
    private static final String SAVE_ID = "saveID";
    private static final String SAVE_PROFILE = "saveProfile";
    private static final String SAVE_PHOTO = "savePhoto";
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

    public EditPeopleFragment() {
        super();
    }

    public static EditPeopleFragment newInstance(int id) {
        EditPeopleFragment fragment = new EditPeopleFragment();
        Bundle args = new Bundle();
        args.putInt(ID_PEOPLE, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public EditPeopleFragmentInterface.Presenter createPresenter() {
        return EditPeopleFragmentPresenter.create();
    }

    @Override
    public int getLayoutView() {
        return R.layout.fragment_edit_people;
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
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
    }

    @Override
    public void setupInstance() {
    }

    @Override
    public void setupView() {
        edtDateBirth.setOnClickListener(showDatePicker);
        edtDateJob.setOnClickListener(showDatePicker);
        btnDelete.setOnClickListener(deletePeopleListener);
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
        EditPeopleFragmentPermissionsDispatcher.setupTakeCameraListenerWithCheck(this);
    }

    @Override
    public void initialize() {}

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
            case R.id.home:
                finishView();
                return true;
        }
        return false;
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

    @Override
    public void restoreView(Bundle savedInstanceState) {
        getPresenter().getPeople(id);
        if (profileS == null) {
            ivPhoto.setImageResource(R.drawable.ic_profile_circle);
        } else {
            setIvProfile(profileS);
        }

        if (photoS == null) {
            ivPhoto.setImageResource(R.drawable.ic_selfie);
        } else {
            setIvPhoto(photoS);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_ID, id);
        outState.putString(SAVE_PROFILE, profileS);
        outState.putString(SAVE_PHOTO, photoS);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getInt(SAVE_ID, 0);
        profileS = savedInstanceState.getString(SAVE_PROFILE);
        photoS = savedInstanceState.getString(SAVE_PHOTO);
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

    private void finishView() {
        getActivity().finish();
    }

    @Override
    public void response(String message) {
        Log.i("EditFragment", "Response: " + message);
        showToast(message);
    }

    @Override
    public void getPeopleEdit(PeopleModel peopleItem) {
        setupFormPeople(peopleItem);
    }

    private void setupFormPeople(PeopleModel people) {
        edtName.setText(people.getName());
        edtJob.setText(people.getJob());
        edtDateBirth.setText(people.getBirthDay());
        edtDateJob.setText(people.getJobStart());
        edtJobDescription.setText(people.getJobDescription());
        edtGame.setText(people.getGame());
        edtSmartPhone.setText(people.getSmartPhone());
        profileS = people.getProfile();
        photoS = people.getPhoto();
        setIvProfile(people.getProfile());
        setIvPhoto(people.getPhoto());
    }

    @Override
    public void saveIsFinish(boolean isSuccess) {
        if (isSuccess)
            finishView();
    }

    private void setIvProfile(String urlProfile) {
        Glide.with(getActivity())
                .load(urlProfile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivProfile);
    }

    private void setIvPhoto(String urlPhoto) {
        Glide.with(getActivity())
                .load(urlPhoto)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .crossFade()
                .into(ivPhoto);
    }

    private void saveToRealm() {
            nameS = edtName.getText().toString();
            dateBirthS = edtDateBirth.getText().toString();
            dateJobS = edtDateJob.getText().toString();
            jobS = edtJob.getText().toString();
            jobDescriptionS = edtJobDescription.getText().toString();
            gameS = edtGame.getText().toString();
            smartPhoneS = edtSmartPhone.getText().toString();

            PeopleModel people = new PeopleModel();
            people.setId(id);
            people.setName(nameS);
            people.setJob(jobS);
            people.setBirthDay(dateBirthS);
            people.setJobStart(dateJobS);
            people.setJobDescription(jobDescriptionS);
            people.setGame(gameS);
            people.setSmartPhone(smartPhoneS);
            people.setPhoto(photoS);
            people.setProfile(profileS);
            getPresenter().updateRealm(people);
    }

    private void showDeleteDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getString(R.string.you_are_want_to_delete))
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        getPresenter().deleteByID(id);
                    }
                }).show();
    }

    private void takeProfileFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFilename = "IMG_Nextzy_Profile_" + timestamp + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),
                "DCIM/Camera/" + imageFilename);
        Uri uriProfile = Uri.fromFile(file);
        if (uriProfile != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriProfile);
            startActivityForResult(Intent.createChooser(intent,
                    getString(R.string.take_with)), REQUEST_CAMERA_PROFILE);
            profileS = uriProfile.toString();
        }
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
        String imageFilename = "IMG_Nextzy_Photo_" + timestamp + ".jpg";
        File file = new File(Environment.getExternalStorageDirectory(),
                "DCIM/Camera/" + imageFilename);
        Uri uriPhoto = Uri.fromFile(file);
        if (uriPhoto != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
            startActivityForResult(Intent.createChooser(intent,
                    getString(R.string.take_with)), REQUEST_CAMERA_PHOTO);
            photoS = uriPhoto.toString();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            setIvProfile(profileS);
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            setIvPhoto(photoS);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EditPeopleFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    private View.OnClickListener deletePeopleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDeleteDialog();
        }
    };

    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard();
            final View vv = v;
            final Calendar calendar = Calendar.getInstance();
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



}

