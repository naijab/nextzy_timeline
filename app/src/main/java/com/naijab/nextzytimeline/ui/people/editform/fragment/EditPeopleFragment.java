package com.naijab.nextzytimeline.ui.people.editform.fragment;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import static android.app.Activity.RESULT_OK;

public class EditPeopleFragment extends BaseMvpFragment<EditPeopleFragmentInterface.Presenter>
        implements EditPeopleFragmentInterface.View {

    private EditText nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;
    private FloatingActionButton fabPhoto;
    private Button btnDelete;
    private Uri uriProfile, uriPhoto;
    private int id;
    private int realmID;

    private String stringProfile, stringPhoto;
    private String stringName, stringJob, stringBirth, stringStartJob, stringJobDescription, stringGame, stringSmartPhone;
    private int mYear, mMonth, mDay;
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

    public EditPeopleFragment() {
        super();
    }

    public static EditPeopleFragment newInstance(int id) {
        EditPeopleFragment fragment = new EditPeopleFragment();
        Bundle args = new Bundle();
        args.putInt("id", id);
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
        nameAndLastName = (EditText) view.findViewById(R.id.edt_name_and_lastname);
        job = (EditText) view.findViewById(R.id.edt_job);
        dateBirth = (EditText) view.findViewById(R.id.edt_birthday);
        dateJob = (EditText) view.findViewById(R.id.edt_startjob);
        jobDescription = (EditText) view.findViewById(R.id.edt_job_description);
        game = (EditText) view.findViewById(R.id.edt_game);
        smartPhone = (EditText) view.findViewById(R.id.edt_your_phone);
        photo = (ImageView) view.findViewById(R.id.iv_photo);
        profile = (ImageView) view.findViewById(R.id.iv_profile);
        fabPhoto = (FloatingActionButton) view.findViewById(R.id.fab);
        btnDelete = (Button) view.findViewById(R.id.btn_delete);
    }

    @Override
    public void setupInstance() {
    }

    @Override
    public void setupView() {
        dateBirth.setOnClickListener(showDatePicker);
        dateJob.setOnClickListener(showDatePicker);
        profile.setOnClickListener(takeProfile);
        fabPhoto.setOnClickListener(takePhoto);
        btnDelete.setOnClickListener(deletePeopleListener);
    }

    @Override
    public void initialize() {
        setHasOptionsMenu(true);
        id = getArguments().getInt("id", 0);
        getPresenter().getPeople(id);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_done:
                saveToRealm();
                return true;
            case R.id.home:
                finishView();
                return true;
        }
        return false;
    }

    @Override
    public void restoreView(Bundle savedInstanceState) {
        int idSave = savedInstanceState.getInt("saveID", 0);
        id = idSave;
        getPresenter().getPeople(idSave);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriProfile, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriProfile);
                profile.setImageBitmap(bitmap);

                Log.i("EditPeople", "Image: " + uriProfile.toString());

            } catch (Exception e) {

                Log.e("EditPeople", "" + e.getMessage());
            }
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriPhoto, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriPhoto);
                photo.setImageBitmap(bitmap);

                Log.i("EditPeople", "Image: " + uriPhoto.toString());

            } catch (Exception e) {

                Log.e("EditPeople", "" + e.getMessage());
            }
        }

    }

    private void setDateBirth(int year,
                              int monthOfYear,
                              int dayOfMonth) {
        dateBirth.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }

    private void setDateJob(int year,
                            int monthOfYear,
                            int dayOfMonth) {
        dateJob.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
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
    public void getPeopleEdit(PeopleModel people) {
        nameAndLastName.setText(people.getName());
        job.setText(people.getJob());
        dateBirth.setText(people.getBirthDay());
        dateJob.setText(people.getJobStart());
        jobDescription.setText(people.getJobDescription());
        game.setText(people.getGame());
        smartPhone.setText(people.getSmartPhone());

        realmID = people.getId();
        stringName = people.getName();
        stringJob = people.getJob();
        stringBirth = people.getBirthDay();
        stringStartJob = people.getJobStart();
        stringJobDescription = people.getJobDescription();
        stringGame = people.getGame();
        stringSmartPhone = people.getSmartPhone();

        stringProfile = people.getProfile();
        stringPhoto = people.getPhoto();
        setProfile(stringProfile);
        setPhoto(stringPhoto);
    }

    @Override
    public void saveIsFinish(boolean isSuccess) {
        if (isSuccess)
            finishView();
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

    private void saveToRealm() {

        if (nameAndLastName != null &&
                dateBirth != null &&
                dateJob != null &&
                job != null &&
                jobDescription != null &&
                game != null &&
                smartPhone != null) {
            String name = nameAndLastName.getText().toString();
            String dateBirthS = dateBirth.getText().toString();
            String dateJobS = dateJob.getText().toString();
            String jobS = job.getText().toString();
            String jobDescriptionS = jobDescription.getText().toString();
            String gameS = game.getText().toString();
            String smartPhoneS = smartPhone.getText().toString();

            PeopleModel people = new PeopleModel();
            people.setId(realmID);
            people.setName(name);
            people.setJob(jobS);
            people.setBirthDay(dateBirthS);
            people.setJobStart(dateJobS);
            people.setJobDescription(jobDescriptionS);
            people.setGame(gameS);
            people.setSmartPhone(smartPhoneS);
            people.setPhoto(stringPhoto);
            people.setProfile(stringProfile);

            getPresenter().updateRealm(people, getActivity());

        } else {
            PeopleModel people = new PeopleModel();
            people.setId(realmID);
            people.setName(stringName);
            people.setJob(stringJob);
            people.setBirthDay(stringBirth);
            people.setJobStart(stringStartJob);
            people.setJobDescription(stringJobDescription);
            people.setGame(stringGame);
            people.setSmartPhone(stringSmartPhone);
            people.setPhoto(stringPhoto);
            people.setProfile(stringProfile);

            getPresenter().updateRealm(people, getActivity());
        }

    }

    private void showDeleteDialog() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(getString(R.string.you_are_want_to_delete))
                .setNegativeButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        dialoginterface.cancel();
                    }
                })
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialoginterface, int i) {
                        getPresenter().deleteByID(id, getActivity());
                    }
                }).show();
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
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

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
    };

    private View.OnClickListener takeProfile = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String imagFilename = "IMG_Nextzy_Profile_" + timestamp + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(),
                    "DCIM/Camera/" + imagFilename);
            uriProfile = Uri.fromFile(file);
            if (uriProfile != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriProfile);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.take_with)), REQUEST_CAMERA_PROFILE);
                stringProfile = uriProfile.toString();
            }
        }
    };

    private View.OnClickListener takePhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String imagFilename = "IMG_Nextzy_Photo_" + timestamp + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(),
                    "DCIM/Camera/" + imagFilename);
            uriPhoto = Uri.fromFile(file);
            if (uriPhoto != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
                startActivityForResult(Intent.createChooser(intent,
                        getString(R.string.take_with)), REQUEST_CAMERA_PHOTO);
                stringPhoto = uriPhoto.toString();
            }
        }
    };

}

