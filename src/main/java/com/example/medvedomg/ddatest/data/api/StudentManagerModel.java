package com.example.medvedomg.ddatest.data.api;

import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;

import java.util.List;

/**
 * Created by medvedomg on 07.01.17.
 */

public interface StudentManagerModel {
    void getTwentyStudents(DbModule dbModule, ResultListener listener);

    interface ResultListener {
        List<Student> OnSucces();
    }
}
