package com.example.medvedomg.ddatest.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by medvedomg on 05.01.17.
 */

public class Course {

    @SerializedName("name")
    private String name;

    @SerializedName("mark")
    private int mark;

    public int getMark() {
        return mark;
    }

    public String getName() {
        return name;
    }
}