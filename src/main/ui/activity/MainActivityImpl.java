package com.example.medvedomg.ddatest.ui.activity;

import android.app.LoaderManager;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.example.medvedomg.ddatest.data.db.DbModel;
import com.example.medvedomg.ddatest.data.db.DbModule;
import com.example.medvedomg.ddatest.data.model.Course;
import com.example.medvedomg.ddatest.data.model.Student;
import com.example.medvedomg.ddatest.ui.adapter.TestAdapter;
import com.example.medvedomg.ddatest.ui.component.DaggerMainActivityComponent;
import com.example.medvedomg.ddatest.ui.module.MainActivityModule;
import com.example.medvedomg.ddatest.ui.presenter.MainPresenterImpl;
import com.example.medvedomg.ddatest.util.EndlessRecyclerViewScrollListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivityImpl extends AppCompatActivity implements MainView, LoaderManager.LoaderCallbacks<Cursor>, DbModel.ResultListener{

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
    private EndlessRecyclerViewScrollListener scrollListener;
    private static boolean FILTER = false;
    private static String COURSE = "";
    private static String MARK = "";

    @RequiresApi(api = Build.VERSION_CODES.M)
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
        DbModule dbModule1 = new DbModule(this);

        SQLiteDatabase db1 = dbModule1.getWritableDatabase();
        studentManager.getStudentList(dbModule);
        rvMain.setAdapter(adapter);

        rvMain.setLayoutManager(linearLayoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(final int page, int totalItemsCount, RecyclerView view) {
                Log.d(TAG, "page " + page + " ," + " totalItemsCount" + " " + totalItemsCount);
                if (FILTER) {

                    loadFilterStudents(Integer.toString(page * 20), COURSE, MARK);
                } else
                            loadMoreStudents(Integer.toString(page * 20));
            }
        };
        rvMain.setOnScrollListener(scrollListener);

        Bundle bundle = new Bundle();

        Toast.makeText(this,"Loading", Toast.LENGTH_LONG).show();

    }

    public void loadFirstItems() {
        mainPresenter.loadStudents("0");
    }

    private void loadMoreStudents(String offset) {
                mainPresenter.loadStudents(offset);
    }

    private void loadFilterStudents(String offset, String course, String mark) {
        Log.d(TAG, " offset " + offset + " course " + course + " mark " + mark + " FILTER " + FILTER);

        MARK = mark;
        COURSE = course;
        Log.d(TAG, "STATIC MARK " + MARK + " " + "STATIC COURSE " + COURSE);

        mainPresenter.loadFilterStudents(offset,course,mark);
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

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivityImpl.this);
                builder1.setMessage("Filters");
                LayoutInflater inflater = this.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_main_filter, null);
                builder1.setView(dialogView);
                builder1.setCancelable(true);

                String[] spinnerArray = new String[]{"Course-0", "Course-1", "Course-2", "Course-3"};
                final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner_main_dialog_filter);
                ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this,
                        android.R.layout.simple_spinner_dropdown_item,
                        spinnerArray);

                final EditText editText = (EditText) dialogView.findViewById(R.id.edit_main_dialog_mark);

                spinner.setAdapter(spinnerArrayAdapter);
                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                scrollListener.resetState();

                                FILTER = true;
                                loadFilterStudents("0", spinner.getSelectedItem().toString(), editText.getText().toString());
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
    }

    public void setStudentsInMainActivity(List<Student> list) {
        students = list;
        notifyDb();
    }

    private void notifyDb() {
        mainPresenter.askDbToInsertStudents(students);

//        dbModule.insertAllData(students);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbModule.close();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void OnSucces(List<Student> list) {

    }
}
