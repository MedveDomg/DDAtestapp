package com.example.medvedomg.ddatest;

import android.app.Application;

import com.example.medvedomg.ddatest.data.api.RestApiModule;
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
                .dbModule(new DbModule(this))
                .restApiModule(new RestApiModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
