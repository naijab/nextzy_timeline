package com.naijab.nextzytimeline;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NextzyApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        initRealm();
        mContext = this;
    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static Context getContext(){
        return mContext;
    }




}
