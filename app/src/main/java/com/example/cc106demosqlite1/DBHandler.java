package com.example.cc106demosqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import kotlin.jvm.internal.PropertyReference0Impl;

public class DBHandler extends SQLiteOpenHelper {

    //properties
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "DBstudents";
    private static final String TABLE_NAME = "tblstudents";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOC = "location";
    private static final String KEY_COURSE = "course";

    //constructor
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //other methods
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_NAME + " TEXT, "
                + KEY_LOC + " TEXT, "
                + KEY_COURSE + " TEXT "
                + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //CRUD OPERATION
    boolean saveStudentInfo(String id, String name, String loc, String course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues studValues = new ContentValues();
        studValues.put(KEY_NAME, name);
        studValues.put(KEY_LOC, loc);
        studValues.put(KEY_COURSE, course);

        // If id is not null, it means we're updating an existing student
        if (id != null) {
            // Update existing student
            db.update(TABLE_NAME, studValues, KEY_ID + "=?", new String[]{id});
            return false; // return false for update
        } else {
            // Insert new student
            db.insert(TABLE_NAME, null, studValues);
            return true; // return true for insert
        }
    }

    //Read operation
    ArrayList<String> readStudent() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> data = new ArrayList<String>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{"id", "name", "location", "course"}, null, null, null, null, null);

        String studData = null;
        while (cursor.moveToNext()) {
            studData = cursor.getString(0) + ". " + cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3);
            data.add(studData);
        }
        cursor.close();

        return data;
    }

}