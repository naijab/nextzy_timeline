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
        dateJob = (EditText) view.findViewById(R.id.edt_startjob);
    }

    @Override
    public void setupInstance() {

    }

    @Override
    public void setupView() {
        dateBirth.setOnClickListener(showDatePicker);
        dateJob.setOnClickListener(showDatePicker);
    }

    @Override
    public void initialize() {

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


    private View.OnClickListener showDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final View vv = v;

            DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    mYear = year;
                    mMonth = month;
                    mDay = dayOfMonth;

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.YEAR, mYear);
                    calendar.set(Calendar.MONTH, mMonth);
                    calendar.set(Calendar.DAY_OF_MONTH, mDay);

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

