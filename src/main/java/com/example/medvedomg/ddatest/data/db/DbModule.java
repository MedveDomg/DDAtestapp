package com.example.medvedomg.ddatest.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.medvedomg.ddatest.data.api.StudentManager;
import com.example.medvedomg.ddatest.data.model.Course;
import com.example.medvedomg.ddatest.data.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by medvedomg on 05.01.17.
 */
public class DbModule extends SQLiteOpenHelper implements DbModel{
    public static final String TAG = DbModule.class.getSimpleName();

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
    public static final String COLUMN_COURSE_STUDENT_ID = "studentid";

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
                    COLUMN_COURSE_STUDENT_ID + " integer, " +
                    COLUMN_MARK + " integer" + ");";



    DbModule mDbHelper;
    Context mContext;

    SQLiteDatabase mDb;


    List<Student> studentList = new ArrayList<>();

    private ContentValues cv = new ContentValues();

    private byte[] data;
    private ContentValues cv1 = new ContentValues();
    private Student student;
    private SQLiteDatabase db;
    private Cursor cursorCourseTable;
    private Cursor cursorMainTable;
    private String customId;
    private int mark;
    private String name;
    private String firstName;
    private String lastName;
    private StudentManager manager;
    private int birthday;
    private Course course1;
    private ArrayList<Course> courseList;

    public DbModule(Context mContext, StudentManager manager) {

        super(mContext, DB_NAME, null, 1);

        Log.d(TAG, "DbModule onCreate()");
        this.mContext = mContext;
        this.manager = manager;
//        mDbHelper = new HelperModule(mContext);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DB_CREATE_MAIN_TABLE);
        database.execSQL(DB_CREATE_COURSE_TABLE);
        mDb = database;

        manager.getStudentList(this);
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
        db = this.getWritableDatabase();
        for (Student student : studentList) {
            cv.put(COLUMN_CUSTOM_ID, student.getId());
            cv.put(COLUMN_FIRST_NAME, student.getFirstName());
            cv.put(COLUMN_LAST_NAME, student.getLastName());
            cv.put(COLUMN_BIRTHDAY, student.getBirthday());
            db.insert(DB_TABLE_MAIN, null, cv);
            cv.clear();
            for (int i = 0; i < student.getCourses().size(); i++) {
                cv1.put(COLUMN_COURSE_STUDENT_ID,student.getId());
                cv1.put(COLUMN_COURSE_NAME, student.getCourses().get(i).getName());
                cv1.put(COLUMN_MARK, student.getCourses().get(i).getMark());
                db.insert(DB_TABLE_COURSE, null, cv1);
            }
            Log.d(TAG, "inserted");
        }
    }






    @Override
    public void getTwentyStudents(DbModule dbModule, ResultListener listener) {



        db = this.getWritableDatabase();
        cursorMainTable = db.query(DB_TABLE_MAIN, null, null, null, null, null, null, String.valueOf(21));
        if (cursorMainTable != null) {
            while(cursorMainTable.moveToNext()) {
                courseList = new ArrayList<>();
                courseList.clear();
                student = new Student();
                customId = cursorMainTable.getString(cursorMainTable.getColumnIndex(COLUMN_CUSTOM_ID));
                student.setId(customId);
                Log.d(TAG, "students customId " + customId);

                firstName = cursorMainTable.getString(cursorMainTable.getColumnIndex(COLUMN_FIRST_NAME));
                student.setFirstName(firstName);

                lastName = cursorMainTable.getString(cursorMainTable.getColumnIndex(COLUMN_LAST_NAME));
                student.setLastName(lastName);

                birthday = cursorMainTable.getInt(cursorMainTable.getColumnIndex(COLUMN_BIRTHDAY));
                student.setBirthday(birthday);
                Log.d(TAG, "birthday " + birthday);

                student.setCourses(courseList);
                cursorCourseTable = db.rawQuery("SELECT _id, coursename, mark FROM testtabcourse WHERE testtabcourse.studentid = ?", new String[]{customId});
                if(cursorCourseTable != null){

                    while (cursorCourseTable.moveToNext()) {

                        course1 = new Course();
                        mark = cursorCourseTable.getInt(cursorCourseTable.getColumnIndex(COLUMN_MARK));
                        Log.d(TAG, "TAKE MARK " + mark);
                        name = cursorCourseTable.getString(cursorCourseTable.getColumnIndex(COLUMN_COURSE_NAME));
                        Log.d(TAG, "TAKE NAME " + name);
                        course1.setMark(mark);
                        course1.setName(name);
                        courseList.add(course1);
                        Log.d(TAG, student.getFirstName());
                    }
                    Log.d(TAG, "courseList.size() " + courseList.size());
                }
                ;
                Log.d(TAG, courseList.get(0).getMark() + "");
                Log.d(TAG, courseList.get(1).getMark() + "");
                Log.d(TAG, courseList.get(2).getMark() + "");
                Log.d(TAG, courseList.get(3).getMark() + "");
                student.setCourses(courseList);
                studentList.add(student);
                Log.d(TAG,  "--------------------------");
                Log.d(TAG, student.getCourses().get(0).getMark() + "");
                Log.d(TAG, student.getCourses().get(1).getMark() + "");
                Log.d(TAG, student.getCourses().get(2).getMark() + "");
                Log.d(TAG, student.getCourses().get(3).getMark() + "");
//                Log.d(TAG, "student get mark", student.getCourses().get(1).getMark() + "");
            }
        }
        Log.d(TAG, "studentList.size() in DbModule " + studentList.size());
        listener.OnSucces(studentList);
    }
}
