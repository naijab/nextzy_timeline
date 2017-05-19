package com.naijab.nextzytimeline;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class NextzyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        initRealm();

    }

    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
