package com.example.medvedomg.ddatest.data.db;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.medvedomg.ddatest.data.model.Student;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by medvedomg on 05.01.17.
 */
@Module
public class DbModule extends SQLiteOpenHelper{
    public static final String TAG = Class.class.getSimpleName();

    private static final String DB_NAME = "TestDb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE_MAIN = "testtab";
    private static final String DB_TABLE_COURSE = "testtabcourse";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CUSTOM_ID = "customid";
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";
    public static final String COLUMN_BIRTHDAY = "birthday";
    public static final String COLUMN_COURSES = "courses";
    public static final String COLUMN_COURSE_NAME = "coursename";
    public static final String COLUMN_MARK = "mark";

    private static final String DB_CREATE_MAIN_TABLE =
            "create table " + DB_TABLE_MAIN + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_CUSTOM_ID + " integer, " +
                    COLUMN_FIRST_NAME + " text, " +
                    COLUMN_LAST_NAME + " text, " +
                    COLUMN_BIRTHDAY + " integer," +
                    COLUMN_COURSES + " integer" +
                    ");";

    private static final String DB_CREATE_COURSE_TABLE =
            "create table " + DB_TABLE_COURSE + "(" +
                    COLUMN_ID + " integer primary key, " +
                    COLUMN_COURSE_NAME + " text, " +
                    COLUMN_MARK + " integer" + ");";



    DbModule mDbHelper;
    Context mContext;

    SQLiteDatabase mDb;




    private ContentValues cv = new ContentValues();

    private byte[] data;
    private ContentValues cv1 = new ContentValues();

    public DbModule(Context mContext) {

        super(mContext, DB_NAME, null, 1);

        Log.d(TAG, "DbModule onCreate()");
        this.mContext = mContext;
//        mDbHelper = new HelperModule(mContext);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DB_CREATE_MAIN_TABLE);
        database.execSQL(DB_CREATE_COURSE_TABLE);
        mDb = database;



//        for (int i = 0; i < 10; i++) {
//            database.insert(DB_TABLE_MAIN, null, cv);
//            Log.d(TAG, "inserted bitmap arrays");
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
    }

    public void insertAllData(List<Student> studentList) {
        cv.clear();
//        Log.d(TAG, "studentList.size() + " + studentList.size());
//        studentList.size();
        SQLiteDatabase db = this.getWritableDatabase();
        for (Student student : studentList) {
            cv.put(COLUMN_CUSTOM_ID, student.getId());
            cv.put(COLUMN_FIRST_NAME, student.getFirstName());
            cv.put(COLUMN_LAST_NAME, student.getLastName());
            cv.put(COLUMN_BIRTHDAY, student.getBirthday());
            db.insert(DB_TABLE_MAIN, null, cv);
            cv.clear();
            for (int i = 0; i < student.getCourses().size(); i++) {
                cv1.put(COLUMN_COURSE_NAME, student.getCourses().get(i).getName());
                cv1.put(COLUMN_MARK, student.getCourses().get(i).getMark());
                db.insert(DB_TABLE_COURSE, null, cv1);
            }
            Log.d(TAG, "inserted");
        }
    }

    @Singleton
    @Provides
    DbModule provideDb(Application application) {
        return new DbModule(application);
    }
}
