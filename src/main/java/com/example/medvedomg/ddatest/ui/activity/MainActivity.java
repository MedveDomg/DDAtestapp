package com.example.medvedomg.ddatest.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.medvedomg.ddatest.App;
import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.api.ApiClient;
import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.data.model.StudentResponse;
import com.example.medvedomg.ddatest.ui.component.DaggerMainActivityComponent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    StudentManager studentManager;


    private StudentApiInterface studentApiInterface;

    @Inject
    DbModule dbModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .build()
                .inject(this);

        List<Student> students = studentManager.getStudentList();

        SQLiteDatabase db = dbModule.getWritableDatabase();
        dbModule.insertAllData(students,db);
    }


}
