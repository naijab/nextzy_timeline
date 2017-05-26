package com.naijab.nextzytimeline.ui.people.addform.fragment;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class AddPeopleFragment extends BaseMvpFragment<AddPeopleFragmentInterface.Presenter>
        implements AddPeopleFragmentInterface.View {

    private EditText nameAndLastName, job, dateBirth, dateJob, jobDescription, game, smartPhone;
    private ImageView profile, photo;
    private FloatingActionButton fabPhoto;
    private Uri uriProfile, uriPhoto;

    private int mYear, mMonth, mDay;
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
    }

    @Override
    public void setupInstance() {
        setHasOptionsMenu(true);
    }

    @Override
    public void setupView() {
        dateBirth.setOnClickListener(showDatePicker);
        dateJob.setOnClickListener(showDatePicker);
        profile.setOnClickListener(takeProfile);
        fabPhoto.setOnClickListener(takePhoto);
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
            case R.id.app_bar_done:
                saveToRealm();
                return true;
        }
        return false;
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


    private void saveToRealm() {
        String name = nameAndLastName.getText().toString();
        String dateBirthS = dateBirth.getText().toString();
        String dateJob = this.dateJob.getText().toString();
        String job = this.job.getText().toString();
        String jobDescription = this.jobDescription.getText().toString();
        String game = this.game.getText().toString();
        String smartphone = this.smartPhone.getText().toString();
        String profile = uriProfile.toString();
        String photo = uriPhoto.toString();

        PeopleModel people = new PeopleModel();
        people.setName(name);
        people.setProfile(profile);
        people.setJob(job);
        people.setBirthDay(dateBirthS);
        people.setJobStart(dateJob);
        people.setJobDescription(jobDescription);
        people.setGame(game);
        people.setSmartPhone(smartphone);
        people.setPhoto(photo);
        getPresenter().saveIntoRealm(people, getActivity());
    }


    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
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
                        setDateBirth(mYear, mMonth, mDay);
                    }
                    if (vv.getId() == R.id.edt_startjob) {
                        setDateJob(mYear, mMonth, mDay);
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriProfile);
            startActivityForResult(Intent.createChooser(intent,
                    getString(R.string.take_with)), REQUEST_CAMERA_PROFILE);
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
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
            startActivityForResult(Intent.createChooser(intent,
                    getString(R.string.take_with)), REQUEST_CAMERA_PHOTO);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriProfile, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriProfile);
                profile.setImageBitmap(bitmap);

                Log.i("AddPeople", "Image: " + uriProfile.toString());

            } catch (Exception e) {

                Log.e("AddPeople", "" + e.getMessage());
            }
        }
        else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriPhoto, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriPhoto);
                photo.setImageBitmap(bitmap);

                Log.i("AddPeople", "Image: " + uriPhoto.toString());

            } catch (Exception e) {

                Log.e("AddPeople", "" + e.getMessage());
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


    @Override
    public void response(String message) {
        Log.i("AddFragment", "Response: " + message);
        Toast.makeText(getActivity(), "Response: "+ message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveIsFinish(boolean isSuccess) {
        if (isSuccess)
            finishView();
    }

    private void finishView() {
        getActivity().finish();
    }
}

