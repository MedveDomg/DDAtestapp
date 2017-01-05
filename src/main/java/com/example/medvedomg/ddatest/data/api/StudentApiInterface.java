package com.example.medvedomg.ddatest.data.api;



import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.data.model.StudentResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentApiInterface {
//    @GET("api/v1/images/latest")

    @GET("api/test/sampleData")
    Call<List<Student>> getStudentList();

//    @GET("api/students/id")
//    Call<Student> getStudent();
}