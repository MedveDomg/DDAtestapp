package com.example.medvedomg.ddatest.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by medvedomg on 05.01.17.
 */

public class StudentResponse {

    @SerializedName("")
    private ArrayList<Student> studentsList;



    public ArrayList<Student> getStudentsList() {
        return studentsList;
    }
}
