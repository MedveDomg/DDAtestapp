package com.example.medvedomg.ddatest.ui.component;

import com.example.medvedomg.ddatest.AppComponent;
import com.example.medvedomg.ddatest.ui.ActivityScope;
import com.example.medvedomg.ddatest.ui.activity.MainActivity;

import dagger.Component;

/**
 * Created by medvedomg on 05.01.17.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
