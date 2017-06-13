package com.naijab.nextzytimeline.ui.people.editform.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
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

public class EditPeopleFragment extends BaseFragment implements PermissionListener {

    private static final String ID_PEOPLE = "id";
    private static final String SAVE_ID = "saveID";
    private static final String SAVE_PROFILE = "saveProfile";
    private static final String SAVE_PHOTO = "savePhoto";
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;
    private Button btnDelete;
    private int id;
    private String profile, photo;

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
        checkPermission();
    }

    private void checkPermission() {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(this)
                .check();
    }

    @Override
    public void setupView() {
        edtDateBirth.setOnClickListener(showDatePicker);
        edtDateJob.setOnClickListener(showDatePicker);
        btnDelete.setOnClickListener(deletePeopleListener);
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
        setHasOptionsMenu(true);
        id = getArguments().getInt(ID_PEOPLE, 0);
        getPeople(id);
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
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }
        return false;
    }



    @Override
    public void restoreView(Bundle savedInstanceState) {
        getPeople(id);
        if (profile == null) {
            ivPhoto.setImageResource(R.drawable.ic_profile_circle);
        } else {
            setIvProfile(profile);
        }

        if (photo == null) {
            ivPhoto.setImageResource(R.drawable.ic_selfie);
        } else {
            setIvPhoto(photo);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVE_ID, id);
        outState.putString(SAVE_PROFILE, profile);
        outState.putString(SAVE_PHOTO, photo);
    }

    @Override
    protected void onRestoreArguments(Bundle arguments) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        id = savedInstanceState.getInt(SAVE_ID, 0);
        profile = savedInstanceState.getString(SAVE_PROFILE);
        photo = savedInstanceState.getString(SAVE_PHOTO);
    }

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
        profile = people.getProfile();
        photo = people.getPhoto();
        setIvProfile(people.getProfile());
        setIvPhoto(people.getPhoto());
    }

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
        } else {
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

        if (isFormValidate(name, dateBirth, dateJob, job, jobDescription, game, smartPhone)) {
            PeopleModel people = new PeopleModel();
            people.setId(id);
            people.setName(name);
            people.setJob(job);
            people.setBirthDay(dateBirth);
            people.setJobStart(dateJob);
            people.setJobDescription(jobDescription);
            people.setGame(game);
            people.setSmartPhone(smartPhone);
            people.setPhoto(photo);
            people.setProfile(profile);
            updateRealm(people);
        }
    }

    public void getPeople(int id) {
        PeopleModel people = PeopleManager.getInstance().getPeople(id);
        getPeopleEdit(people);
    }

    public void updateRealm(final PeopleModel people) {
        PeopleManager.getInstance().editRealm(people, new PeopleManager.onEditCallBack() {
            @Override
            public void onSaveSuccess(String message) {
                showToast(message);
                saveIsFinish(true);
            }

            @Override
            public void onSaveError(String message) {
                showToast(message);
                saveIsFinish(false);
            }
        });
    }

    public void deleteByID(int id) {
        PeopleManager.getInstance().deleteByID(id, new PeopleManager.onDeleteCallBack() {
            @Override
            public void onDeleteSuccess(String message) {
                showToast(message);
            }

            @Override
            public void onDeleteError(String message) {
                showToast(message);
            }
        });
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
                        deleteByID(id);
                    }
                }).show();
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
            setIvProfile(profile);
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            setIvPhoto(photo);
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

    private void finishView() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(ID_PEOPLE, id);
        getActivity().setResult(Activity.RESULT_OK, returnIntent);
        getActivity().finish();
    }

    private View.OnClickListener deletePeopleListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showDeleteDialog();
        }
    };

    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideKeyboard();
            Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH);
            int mDay = calendar.get(Calendar.DAY_OF_MONTH);
            getDatePicker(view, mYear, mMonth, mDay);
        }
    };

    private void getDatePicker(final View view, int mYear, int mMonth, int mDay) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
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


    @Override
    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
        if (permissionGrantedResponse == null) {
            showToast(getString(R.string.want_permission_ok));
        }
        setupTakeCameraListener();
    }

    @Override
    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
        showToast(getString(R.string.want_permission_cancel));
        checkPermission();
    }

    @Override
    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, final PermissionToken permissionToken) {
        new AlertDialog.Builder(getActivity()).setTitle(getString(R.string.want_permission))
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        permissionToken.cancelPermissionRequest();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        permissionToken.continuePermissionRequest();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override public void onDismiss(DialogInterface dialog) {
                        permissionToken.cancelPermissionRequest();
                    }
                })
                .show();
    }
}

