package com.naijab.nextzytimeline.utility;

import android.support.annotation.NonNull;

import com.naijab.nextzytimeline.NextzyApp;
import com.naijab.nextzytimeline.R;

public class StringUtility {

    @NonNull
    public static String getSaveSuccess() {
        return NextzyApp.getContext().getResources().getString(R.string.save_success);
    }

    @NonNull
    public static String getSaveError() {
        return NextzyApp.getContext().getResources().getString(R.string.save_error);
    }

    @NonNull
    public static String getDeleteSuccess(){
        return NextzyApp.getContext().getResources().getString(R.string.delete_success);
    }

    @NonNull
    public static String getDeleteError(){
        return NextzyApp.getContext().getResources().getString(R.string.delete_error);
    }

}
