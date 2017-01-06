package com.example.medvedomg.ddatest.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.medvedomg.ddatest.App;
import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.component.DaggerMainActivityComponent;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    StudentManager studentManager;


    private StudentApiInterface studentApiInterface;

    @Inject
    DbModule dbModule;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);

        studentManager.getTwentyStudents();
//        while (students == null) {
//            students = studentManager.getRealStudentList();
//        }
//        if (students.size() != 0) {
//
//        } else {
//            Log.d(TAG, "finish");
//            Log.d(TAG, "finish");
//        }

    }


}
