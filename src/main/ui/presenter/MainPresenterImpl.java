package com.example.medvedomg.ddatest.ui.presenter;

import android.util.Log;

import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.api.StudentManagerModel;
import com.example.medvedomg.ddatest.data.db.DbModel;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.activity.MainActivityImpl;

import java.util.List;

/**
 * Created by medvedomg on 06.01.17.
 */

public class MainPresenterImpl implements MainPresenter,DbModel.ResultListener{

    MainActivityImpl mainActivity;

    DbModule dbModule;

    StudentManager studentManager;

    public static final String TAG = MainPresenterImpl.class.getSimpleName();

    public MainPresenterImpl(MainActivityImpl mainActivity, DbModule dbModule, StudentManager studentManager) {
        this.mainActivity = mainActivity;
        this.dbModule = dbModule;
        this.studentManager = studentManager;
    }

    @Override
    public void loadStudents(String offset) {
        dbModule.getTwentyStudents(dbModule, this, offset);
    }

    @Override
    public void loadStudentCourse() {

    }

    @Override
    public void filterStudents() {
        //adsdasasdaasd
    }

    @Override
    public void loadFilterStudents(String offset, String course, String mark) {
        dbModule.getTwentyFilterStudents(dbModule,this,offset, course, mark);
    }


    @Override
    public void OnSucces(List<Student> list) {

        mainActivity.setStudents(list);
    }

    public void askDbToInsertStudents(List<Student> list) {
        Log.d(TAG, "askDBtoInsertStudents " + list.size());
        dbModule.insertAllData(list, this);

    }

}
