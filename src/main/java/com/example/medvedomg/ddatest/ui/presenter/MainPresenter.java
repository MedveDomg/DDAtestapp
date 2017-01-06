package com.example.medvedomg.ddatest.ui.presenter;

import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.ui.activity.MainActivity;

/**
 * Created by medvedomg on 06.01.17.
 */

public class MainPresenter {

    MainActivity mainActivity;

    DbModule dbModule;

    StudentManager studentManager;

    public MainPresenter(MainActivity mainActivity, DbModule dbModule, StudentManager studentManager) {
        this.mainActivity = mainActivity;
        this.dbModule = dbModule;
        this.studentManager = studentManager;
    }
}
