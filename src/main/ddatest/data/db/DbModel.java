package com.example.medvedomg.ddatest.data.db;

import com.example.medvedomg.ddatest.data.api.StudentManagerModel;
import com.example.medvedomg.ddatest.data.model.Student;

import java.util.List;

/**
 * Created by medvedomg on 07.01.17.
 */

public interface DbModel {
    void getTwentyStudents(DbModule dbModule, ResultListener listener, String offset);

    void getTwentyFilterStudents(DbModule dbModule, ResultListener listener, String offset, String course, String mark);

    interface ResultListener {
        void OnSucces(List<Student> list);
    }
}
