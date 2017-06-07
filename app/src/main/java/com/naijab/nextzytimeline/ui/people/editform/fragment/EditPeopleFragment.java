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

    private EditText edtName, edtJob, edtDateBirth, edtDateJob, edtJobDescription, edtGame, edtSmartPhone;
    private ImageView ivProfile, ivPhoto;
    private FloatingActionButton fabPhoto;
    private Button btnDelete;
    private Uri uriProfile, uriPhoto;
    private int id;
    private int realmID;

    // TODO prefix "string" to all String variable? What's about String variable in AddPeopleFragment.java?
    private String stringName, stringJob, stringBirth, stringStartJob, stringJobDescription, stringGame, stringSmartPhone;
    private String stringProfile, stringPhoto;
    // TODO Useless variable
    private int mYear, mMonth, mDay;
    public static final int REQUEST_CAMERA_PROFILE = 11;
    public static final int REQUEST_CAMERA_PHOTO = 12;

    public EditPeopleFragment() {
        super();
    }

    public static EditPeopleFragment newInstance(int id) {
        EditPeopleFragment fragment = new EditPeopleFragment();
        Bundle args = new Bundle();
        // TODO Don't hardcode the String key
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
        ivProfile.setOnClickListener(takeProfile);
        fabPhoto.setOnClickListener(takePhoto);
        btnDelete.setOnClickListener(deletePeopleListener);
    }

    @Override
    public void initialize() {
        setHasOptionsMenu(true);
        // TODO Don't hardcode the String key
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
            case R.id.menu_add_person:
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
        // TODO Should be called in onRestoreInstanceState(Bundle savedInstanceState)
        /* TODO It would be better if you do like this
         * id = savedInstanceState.getInt("saveID", 0);
         * getRealm(id);
         *
         * TODO Say goodbye to useless "idSave" variable
         */
        int idSave = savedInstanceState.getInt("saveID", 0);
        id = idSave;
        getPresenter().getPeople(idSave);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO Don't hardcode the String key
        outState.putInt("saveID", id);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    // TODO Duplicated code (See AddPeopleFragment.java)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA_PROFILE && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriProfile, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriProfile);
                ivProfile.setImageBitmap(bitmap);

                Log.i("EditPeople", "Image: " + uriProfile.toString());

            } catch (Exception e) {

                Log.e("EditPeople", "" + e.getMessage());
            }
        } else if (requestCode == REQUEST_CAMERA_PHOTO && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uriPhoto, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uriPhoto);
                ivPhoto.setImageBitmap(bitmap);

                Log.i("EditPeople", "Image: " + uriPhoto.toString());

            } catch (Exception e) {

                Log.e("EditPeople", "" + e.getMessage());
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
        edtName.setText(people.getName());
        edtJob.setText(people.getJob());
        edtDateBirth.setText(people.getBirthDay());
        edtDateJob.setText(people.getJobStart());
        edtJobDescription.setText(people.getJobDescription());
        edtGame.setText(people.getGame());
        edtSmartPhone.setText(people.getSmartPhone());

        // TODO Why don't store PeopleModel in global variable?
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

        setIvProfile(stringProfile);
        setIvPhoto(stringPhoto);
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
        // TODO Null checking with View is useless
        // TODO because you're already bound these view in bindView()
        if (edtName != null &&
                edtDateBirth != null &&
                edtDateJob != null &&
                edtJob != null &&
                edtJobDescription != null &&
                edtGame != null &&
                edtSmartPhone != null) {
            String nameS = edtName.getText().toString();
            String dateBirthS = edtDateBirth.getText().toString();
            String dateJobS = edtDateJob.getText().toString();
            String jobS = edtJob.getText().toString();
            String jobDescriptionS = edtJobDescription.getText().toString();
            String gameS = edtGame.getText().toString();
            String smartPhoneS = edtSmartPhone.getText().toString();

            PeopleModel people = new PeopleModel();
            people.setId(realmID);
            people.setName(nameS);
            people.setJob(jobS);
            people.setBirthDay(dateBirthS);
            people.setJobStart(dateJobS);
            people.setJobDescription(jobDescriptionS);
            people.setGame(gameS);
            people.setSmartPhone(smartPhoneS);
            people.setPhoto(stringPhoto);
            people.setProfile(stringProfile);

            getPresenter().updateRealm(people);

        } else {
            // TODO These can be called? As above comment, EditText always not null
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

            getPresenter().updateRealm(people);
        }

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

    // TODO Duplicated code (See AddPeopleFragment.java)
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

    // TODO Duplicated code (See AddPeopleFragment.java)
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

