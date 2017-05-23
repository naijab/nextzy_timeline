package com.naijab.nextzytimeline.utility;

import android.content.Context;
import android.support.annotation.NonNull;

import com.naijab.nextzytimeline.R;

public class StringUtility {

    @NonNull
    public static String getHomeString(Context context) {
        return context.getResources().getString(R.string.tab_home);
    }

    @NonNull
    public static String getAboutString(Context context) {
        return context.getResources().getString(R.string.tab_about);
    }

    @NonNull
    public static String getContactString(Context context) {
        return context.getResources().getString(R.string.tab_contact);
    }

    @NonNull
    public static String getSaveSuccess(Context context) {
        return context.getResources().getString(R.string.save_success);
    }

    @NonNull
    public static String getDeleteSuccess(Context context){
        return context.getResources().getString(R.string.save_success);
    }

}
