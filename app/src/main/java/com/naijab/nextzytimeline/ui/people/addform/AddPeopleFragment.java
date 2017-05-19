package com.naijab.nextzytimeline.ui.people.addform;

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
import com.naijab.nextzytimeline.ui.people.model.PeopleManager;
import com.naijab.nextzytimeline.ui.people.model.PeopleModel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

import static android.app.Activity.RESULT_OK;

public class AddPeopleFragment extends BaseMvpFragment<AddPeopleFragmentInterface.Presenter>
        implements AddPeopleFragmentInterface.View {

    private EditText nameAndLastName, dateBirth, dateJob, job, game, smartphone;
    private ImageView photo;
    private FloatingActionButton fab;
    private Uri uri;
    private Realm realm;

    private int mYear, mMonth, mDay;
    public static final int REQUEST_CAMERA = 12;

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
        dateBirth = (EditText) view.findViewById(R.id.edt_birthday);
        dateJob = (EditText) view.findViewById(R.id.edt_startjob);
        job = (EditText) view.findViewById(R.id.edt_job);
        game = (EditText) view.findViewById(R.id.edt_game);
        smartphone = (EditText) view.findViewById(R.id.edt_your_phone);
        photo = (ImageView) view.findViewById(R.id.iv_photo);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void setupInstance() {
        setHasOptionsMenu(true);
    }

    @Override
    public void setupView() {
        dateBirth.setOnClickListener(showDatePicker);
        dateJob.setOnClickListener(showDatePicker);
        fab.setOnClickListener(takePhoto);
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
                Toast.makeText(getActivity(), "Are done", Toast.LENGTH_SHORT).show();
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

        final String nameS = nameAndLastName.getText().toString();
        final String dateBirthS = dateBirth.getText().toString();
        final String dateJobS = dateJob.getText().toString();
        final String jobS = job.getText().toString();
        final String gameS = game.getText().toString();
        final String smartphoneS = smartphone.getText().toString();
        final String photoS = uri.toString();

        PeopleModel people = new PeopleModel();
        realm.beginTransaction();
        people.setId(PeopleManager.getInstance().getPeople().size() + 1);
        people.setName(nameS);
        people.setBirthday("16-2-25");
        people.setJobstart("16-2-25");
        people.setJob("iLike");
        people.setGame("Need for Speed");
        people.setSmartphone("iphone");
        people.setPhoto(photoS);
        realm.copyToRealmOrUpdate(people);
        realm.commitTransaction();
        Log.i("AddToRealm", "Save is Okay");


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

    private View.OnClickListener takePhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
            String imagFilename = "IMG_Nextzy_" + timestamp + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(),
                    "DCIM/Camera/" + imagFilename);
            uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(Intent.createChooser(intent, "Take Photo with"), REQUEST_CAMERA);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            getActivity().getContentResolver().notifyChange(uri, null);
            ContentResolver contentResolver = getActivity().getContentResolver();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(contentResolver, uri);
                photo.setImageBitmap(bitmap);

                Log.i("AddPeople", "Image: " + uri.toString());
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


}

