package com.example.medvedomg.ddatest.data.api;

import android.util.Log;

import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.data.model.StudentResponse;
import com.example.medvedomg.ddatest.ui.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

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

    private List<Student> students;
    private Call<List<Student>> call;

    public StudentManager(StudentApiInterface studentApiInterface) {
        this.studentApiInterface = studentApiInterface;
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
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Log.d(TAG, "onFailure()");
            }
        });
//        Log.d(TAG, students.size() + "");
        return students;
    }


}
