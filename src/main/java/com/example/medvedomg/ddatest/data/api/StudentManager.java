package com.example.medvedomg.ddatest.data.api;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medvedomg on 05.01.17.
 */


public class StudentManager {

    public static final String TAG = MainActivity.class.getSimpleName();

    private StudentApiInterface studentApiInterface;
    private Student student;

    private List<Student> students = new ArrayList<>();
    private Call<List<Student>> call;
    private DbModule dbHelper;

    @Singleton
    public StudentManager(StudentApiInterface studentApiInterface, DbModule db) {
        this.dbHelper = db;
//        this.studentApiInterface = studentApiInterface;
//        getStudentList();
    }

    public List<Student> getStudentList() {
        Log.d(TAG, "getStudentList()");
        call = studentApiInterface.getStudentList();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                int statusCode = response.code();
                Log.d("TAG", "response.code() " + response.code());

                students = response.body();
                Log.d("TAG", "response.body().get(3).getFirstName() " + response.body().get(3).getFirstName());
                startInsertInDB(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d(TAG, "onFailure()");
            }
        });
        return students;
    }

    private void startInsertInDB(List<Student> students) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        new AsyncTask<List<Student>, Void, Void>() {
            @Override
            protected Void doInBackground(List<Student>... params) {
                dbHelper.insertAllData(params[0]);
                return null;
            }
        }.execute(students);
    }


    public List<Student> getTwentyStudents() {
        return dbHelper.getTwentyStudents();
    }
}
