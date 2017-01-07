package com.example.medvedomg.ddatest.ui.activity;

import com.example.medvedomg.ddatest.data.model.Student;

import java.util.List;

/**
 * Created by medvedomg on 07.01.17.
 */

public interface MainView {
    void setStudents(List<Student> list);

    void onFilterClick();

    void setFilter();

    void loadCourses();

    void setCourses();
}
