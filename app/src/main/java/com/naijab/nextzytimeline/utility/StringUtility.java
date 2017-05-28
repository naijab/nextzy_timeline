package com.naijab.nextzytimeline.utility;

import android.content.Context;
import android.support.annotation.NonNull;

import com.naijab.nextzytimeline.R;

public class StringUtility {

    @NonNull
    public static String getSaveSuccess(Context context) {
        return context.getResources().getString(R.string.save_success);
    }

    @NonNull
    public static String getSaveError(Context context) {
        return context.getResources().getString(R.string.save_error);
    }

    @NonNull
    public static String getDeleteSuccess(Context context){
        return context.getResources().getString(R.string.delete_success);
    }

    @NonNull
    public static String getDeleteError(Context context){
        return context.getResources().getString(R.string.delete_error);
    }

}
