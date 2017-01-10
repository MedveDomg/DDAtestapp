package com.example.medvedomg.ddatest.ui.presenter;

/**
 * Created by medvedomg on 07.01.17.
 */

public interface MainPresenter{

    void loadStudents(String offset);

    void loadStudentCourse();

    void filterStudents();

    void loadFilterStudents(String offset, String course, String mark);
}
