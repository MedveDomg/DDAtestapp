package com.example.medvedomg.ddatest.ui.presenter;

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
    public void loadStudents() {
//       studentManager.getTwentyStudents(dbModule, this);
        dbModule.getTwentyStudents(dbModule, this);
    }

    @Override
    public void loadStudentCourse() {

    }

    @Override
    public void filterStudents() {

    }



    @Override
    public void OnSucces(List<Student> list) {
        mainActivity.setStudents(list);
    }

//    @Override
//    public void OnSucces(List<Student> studentList) {
//        mainActivity.setStudents(studentList);
//
//    }
}
