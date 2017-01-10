package com.example.medvedomg.ddatest;

import android.app.Application;

import com.example.medvedomg.ddatest.data.api.RestApiModule;
import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created b
 * y medvedomg on 05.01.17.
 */
@Singleton
@Component(modules = {AppModule.class, RestApiModule.class})
public interface AppComponent {

    Application application();

    DbModule db();


    StudentApiInterface apiInterface();
}
