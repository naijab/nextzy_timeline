package com.naijab.nextzytimeline.ui.people.addform;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.naijab.nextzytimeline.R;
import com.naijab.nextzytimeline.base.BaseMvpFragment;

import java.util.Calendar;

public class AddPeopleFragment extends BaseMvpFragment<AddPeopleFragmentInterface.Presenter>
        implements AddPeopleFragmentInterface.View {

    private EditText dateBirth;
    private EditText dateJob;
    private int mYear, mMonth, mDay;

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
        dateBirth = (EditText) view.findViewById(R.id.edt_birthday);
        dateJob = (EditText) view.findViewById(R.id.edt_job);
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
        dateBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        datePickerBirth, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        dateJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog2 = new DatePickerDialog(getActivity(),
                        datePickerJob, mYear, mMonth, mDay);
                datePickerDialog2.show();
            }
        });
    }

    Calendar calendar = Calendar.getInstance();

    private DatePickerDialog.OnDateSetListener datePickerBirth = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;

            calendar.set(Calendar.YEAR, mYear);
            calendar.set(Calendar.MONTH, mMonth);
            calendar.set(Calendar.DAY_OF_MONTH, mDay);

            setDateBirth(year, month, dayOfMonth);
        }
    };

    private DatePickerDialog.OnDateSetListener datePickerJob = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;

            calendar.set(Calendar.YEAR, mYear);
            calendar.set(Calendar.MONTH, mMonth);
            calendar.set(Calendar.DAY_OF_MONTH, mDay);

            setDateJob(year, month, dayOfMonth);
        }
    };

    @Override
    public void initialize() {

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

}

