package com.example.medvedomg.ddatest.data.api;

import android.os.AsyncTask;
import android.util.Log;

import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.activity.MainActivityImpl;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by medvedomg on 05.01.17.
 */


public class StudentManager implements StudentManagerModel{

    public static final String TAG = StudentManager.class.getSimpleName();

    private StudentApiInterface studentApiInterface;
    private Student student;

    private List<Student> students = new ArrayList<>();
    private Call<List<Student>> call;
    private DbModule dbModule;
    private MainActivityImpl mainActivity;


    @Singleton
    public StudentManager(StudentApiInterface studentApiInterface, MainActivityImpl mainActivity) {
        this.studentApiInterface = studentApiInterface;
        this.mainActivity = mainActivity;
//        getStudentList();
    }

    public List<Student> getStudentList(final DbModule dbModule) {
        Log.d(TAG, "getStudentList()");
        this.dbModule = dbModule;
        call = studentApiInterface.getStudentList();
        call.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                int statusCode = response.code();
                Log.d("TAG", "response.code() " + response.code());

                students = response.body();
                Log.d("TAG", "response.body().get(3).getFirstName() " + response.body().get(3).getFirstName());
//                startInsertInDB(students);
//                dbModule.insertAllData(students);
//                mainPresenter.setStudentInPresenter(students);
                mainActivity.setStudentsInMainActivity(students);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d(TAG, "onFailure()");
            }
        });
        return students;
    }

    private void startInsertInDB(List<Student> students) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();



    }

//


    @Override
    public void getTwentyStudents(DbModule dbModule, final ResultListener listener) {



    }

    public void setFirstTwentyStudents() {
            mainActivity.loadFirstItems();
    }
}
