package com.example.medvedomg.ddatest.ui.activity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.medvedomg.ddatest.App;
import com.example.medvedomg.ddatest.R;
import com.example.medvedomg.ddatest.data.api.StudentApiInterface;
import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.adapter.TestAdapter;
import com.example.medvedomg.ddatest.ui.component.DaggerMainActivityComponent;
import com.example.medvedomg.ddatest.ui.module.MainActivityModule;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenterImpl;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityImpl extends AppCompatActivity implements MainView{

    public static final String TAG = MainActivityImpl.class.getSimpleName();

    @Inject
    StudentManager studentManager;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    MainPresenterImpl mainPresenter;

    @Inject
    TestAdapter adapter;

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    private StudentApiInterface studentApiInterface;

    @Inject
    DbModule dbModule;
    private List<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

        SQLiteDatabase db = dbModule.getWritableDatabase();
        rvMain.setAdapter(adapter);

        rvMain.setLayoutManager(linearLayoutManager);

        rvMain.setHasFixedSize(true);
        Toast.makeText(this,"Loading", Toast.LENGTH_LONG).show();
        mainPresenter.loadStudents();
//        studentManager.getTwentyStudents();
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

//                Toast.makeText(MainActivityImpl.this, "WAZUP", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivityImpl.this);
                builder1.setMessage("Filters");
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_main_filter, null);
                builder1.setView(dialogView);
                builder1.setCancelable(true);

                String[] spinnerArray = new String[]{"Course-0", "Course-1", "Course-2", "Course-3"};
                Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_main_dialog_filter);
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        spinnerArray);

                EditText editText = (EditText) dialogView.findViewById(R.id.edit_main_dialog_mark);

                spinner.setAdapter(spinnerArrayAdapter);
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "Clear",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                break;
        }
        return true;
    }

    @Override
    public void setStudents(List<Student> list) {
        Log.d(TAG, "List<Student> list.size() " + list.size());
        adapter.setStudents(list);
        Log.d(TAG, "getMark() " + list.get(0).getCourses().get(0).getMark());
        Log.d(TAG, "getMark() " + list.get(0).getCourses().get(1).getMark());
        Log.d(TAG, "getMark() " + list.get(0).getCourses().get(2).getMark());
        Log.d(TAG, "getMark() " + list.get(0).getCourses().get(3).getMark());
        Log.d(TAG, "++++++++++");
        Log.d(TAG, "getMark() " + list.get(0).getCourses().get(0).getMark());
        Log.d(TAG, "getMark() " + list.get(1).getCourses().get(1).getMark());
        Log.d(TAG, "getMark() " + list.get(2).getCourses().get(2).getMark());
        Log.d(TAG, "getMark() " + list.get(3).getCourses().get(3).getMark());
    }

    @Override
    public void onFilterClick() {

    }

    @Override
    public void setFilter() {

    }

    @Override
    public void loadCourses() {

    }

    @Override
    public void setCourses() {

    }
}
