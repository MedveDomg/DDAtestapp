package com.example.medvedomg.ddatest.ui.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.medvedomg.ddatest.App;
import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.component.DaggerMainActivityComponent;
import com.example.medvedomg.ddatest.ui.module.MainActivityModule;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Inject
    StudentManager studentManager;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    MainPresenter mainPresenter;




    private StudentApiInterface studentApiInterface;

    @Inject
    DbModule dbModule;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

//        studentManager.getTwentyStudents();


//        while (students == null) {
//            students = studentManager.getRealStudentList();
//        }
//        if (students.size() != 0) {
//
//        } else {
//            Log.d(TAG, "finish");
//            Log.d(TAG, "finish");
//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:

                Toast.makeText(MainActivity.this, "WAZUP", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
