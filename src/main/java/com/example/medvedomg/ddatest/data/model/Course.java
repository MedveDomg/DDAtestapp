package com.example.medvedomg.ddatest.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by medvedomg on 05.01.17.
 */

public class Course implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("mark")
    private int mark;



    public int getMark() {
        return mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }


}
