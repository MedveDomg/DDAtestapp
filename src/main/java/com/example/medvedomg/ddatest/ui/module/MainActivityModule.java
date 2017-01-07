package com.example.medvedomg.ddatest.ui.module;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;

import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.ui.ActivityScope;
import com.example.medvedomg.ddatest.ui.activity.MainActivityImpl;
import com.example.medvedomg.ddatest.ui.adapter.TestAdapter;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by medvedomg on 06.01.17.
 */
@Module
public class MainActivityModule {

    private MainActivityImpl mainActivity;

    public MainActivityModule(MainActivityImpl mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivityImpl provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager(Application application) {
        return new LinearLayoutManager(application);
    }

    @Provides
    @ActivityScope
    TestAdapter provideAdapter(MainActivityImpl mainActivity) {
        return new TestAdapter(mainActivity);
    }

    @Provides
    @ActivityScope
    MainPresenterImpl provideMainPresenter(MainActivityImpl mainActivity, DbModule dbModule, StudentManager studentManager) {
        return new MainPresenterImpl(mainActivity, dbModule, studentManager);
    }



}
