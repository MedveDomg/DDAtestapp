package com.example.medvedomg.ddatest.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by medvedomg on 05.01.17.
 */

public class Student {

    @SerializedName("id")
    private String Id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("birthday")
    private int birthday;

    @SerializedName("courses")
    private ArrayList<Course> courses;

    public String getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBirthday() {
        return birthday;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}


