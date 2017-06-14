package com.naijab.nextzytimeline.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.naijab.nextzytimeline.base.exception.NotSetLayoutException;

public abstract class BaseFragment extends Fragment{

    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            onRestoreArguments(getArguments());
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
    }

    protected abstract void onRestoreArguments(Bundle arguments);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int layoutResId = getLayoutView();
        if (getLayoutView() == 0) {
            throw new NotSetLayoutException();
        }
        return inflater.inflate(layoutResId, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindView(view);
        setupInstance();
        setupView();
        if (savedInstanceState == null) {
            initialize();
        }else {
            restoreView(savedInstanceState);
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    public abstract int getLayoutView();

    public abstract void bindView(View view);

    public abstract void setupInstance();

    public abstract void setupView();

    public abstract void initialize();

    public void restoreView(Bundle savedInstanceState) {
    }

    public void hideKeyboard(){
        View view = this.getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showToast(String message){
        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
    }

}
