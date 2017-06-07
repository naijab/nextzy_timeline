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
import com.naijab.nextzytimeline.ui.people.manager.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class AddPeopleFragment extends BaseMvpFragment<AddPeopleFragmentInterface.Presenter>
        implements AddPeopleFragmentInterface.View {

    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;

    // TODO Use String instead of Uri
    private Uri uriProfile, uriPhoto;

    // TODO These variable don't need to declare as global variable
    // TODO Use "name", So you don't need to add "S" as postfix for String
    // TODO "names" means array of name (Plural). So "nameS" can be confuse about variable type
    private String nameS, dateBirthS, dateJobS, jobS, jobDescriptionS, gameS, smartPhoneS, photoS, profileS;
    // TODO Why nameS, dateBirthS don't have "m" as prefix like this? e.g. mNameS, mDateBirthS, ...blah blah...
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
        ivProfile.setOnClickListener(takeProfile);
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
            case R.id.menu_add_person:
                // TODO You have to check each form then alert invalid form by form
                // TODO User doesn't know which form that invalid.
                // TODO e.g. I don't know that I have to add my profile photo in this form
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
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO What's about save the "uriProfile" and "uriPhoto" into outState?
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // TODO What's about restore the "uriProfile" and "uriPhoto" from savedInstanceState?
    }

    private boolean checkIsEmpty() {
        if (edtName != null &&
                edtDateBirth != null &&
                edtDateJob != null &&
                edtJob != null &&
                edtJobDescription != null &&
                edtGame != null &&
                edtSmartPhone != null &&
                uriProfile != null &&
                uriPhoto != null) {
            return true;
        }
        return false;
    }

    private void saveToRealm() {
        // Use local variable, not global
        nameS = edtName.getText().toString();
        dateBirthS = edtDateBirth.getText().toString();
        dateJobS = edtDateJob.getText().toString();
        jobS = edtJob.getText().toString();
        jobDescriptionS = edtJobDescription.getText().toString();
        gameS = edtGame.getText().toString();
        smartPhoneS = edtSmartPhone.getText().toString();

        // TODO Convert to String immediately when retrieve uri data from camera
        // TODO So you don't need to store uri instance as global variable, use String (Handy to save instance state)
        profileS = uriProfile.toString();
        photoS = uriPhoto.toString();

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
        getPresenter().saveIntoRealm(people, getActivity());
    }


    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hideKeyboard();
            final View vv = v;
            final Calendar calendar;
            calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDay = calendar.get(Calendar.DAY_OF_MONTH);

            // TODO Should be as a method
            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    // TODO This code are useless. DatePickerDialog didn't updated to selected date when recall it.
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    // TODO You can directly check the object type like this
//                    if(vv == edtDateBirth) {
                    if (vv.getId() == R.id.edt_birthday) {
                        setDateBirth(year, month + 1, dayOfMonth);
                    }

                    // TODO You can directly check the object type like this
//                    if(vv == edtDateJob) {
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
            // TODO imagFilename ==> imageFilename
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
            // TODO Duplicated code
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
                // TODO Do this operation in background tread if it possible (Big photo size, take more time)
                // TODO Try Glide library
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriProfile);
                ivProfile.setImageBitmap(bitmap);

                Log.i("AddPeople", "Image: " + uriProfile.toString());

            } catch (Exception e) {
                // TODO User don't known what error going on. So you have to feedback to user also
                Log.e("AddPeople", "" + e.getMessage());
            }
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriPhoto, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                // TODO Do this operation in background tread if it possible (Big photo size, take more time)
                // TODO Try Glide library
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriPhoto);
                ivPhoto.setImageBitmap(bitmap);

                Log.i("AddPeople", "Image: " + uriPhoto.toString());

            } catch (Exception e) {
                // TODO User don't known what error going on. So you have to feedback to user also
                Log.e("AddPeople", "" + e.getMessage());
            }
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

    private void finishView() {
        getActivity().finish();
    }
}

