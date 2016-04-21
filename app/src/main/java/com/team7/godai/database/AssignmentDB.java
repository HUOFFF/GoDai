package com.team7.godai.database;

/**
 * Created by mm on 2016/4/20.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AssignmentDB extends SQLiteOpenHelper {
    static String assignment="assignment.db";
    static int dbVersion=1;
    public AssignmentDB(Context context) {
        super(context, assignment, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql="create table assignment(assignment_id integer primary key autoincrement,destination varchar(20),dormitory varchar(20),money varchar(20))";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
