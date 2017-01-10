package com.example.medvedomg.ddatest;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.medvedomg.ddatest.data.api.RestApiModule;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;

/**
 * Created by medvedomg on 05.01.17.
 */

public class App extends Application{

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .restApiModule(new RestApiModule())
                .build();
//        SQLiteDatabase db = appComponent.db().getWritableDatabase();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
