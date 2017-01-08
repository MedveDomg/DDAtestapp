package com.example.medvedomg.ddatest.data.api;



import com.example.medvedomg.ddatest.data.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StudentApiInterface {

    @GET("api/test/sampleData")
    Call<List<Student>> getStudentList();
}