package com.example.medvedomg.ddatest.ui.module;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;

import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.ui.ActivityScope;
import com.example.medvedomg.ddatest.ui.activity.MainActivity;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by medvedomg on 06.01.17.
 */
@Module
public class MainActivityModule {

    private MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application);
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainActivity mainActivity, DbModule dbModule, StudentManager studentManager) {
        return new MainPresenter(mainActivity, dbModule, studentManager);
    }



}
