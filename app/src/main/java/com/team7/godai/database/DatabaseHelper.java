package com.team7.godai.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "user.db";
    static int dbVersion = 2;

    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    public void onCreate(SQLiteDatabase db) {
        String sql = "create table user(id integer primary key autoincrement,username varchar(20),password varchar(20),gender varchar(5),user_id varchar(20),phone varchar(20),school varchar(20))";
        String sql1 = "create table assignment(assignment_id integer primary key autoincrement,destination varchar(20),dormitory varchar(20),money varchar(20),user varchar(20),status varchar(40),re_OR_not varchar(10))";
        String sql2 = "create table re_assignment(re_assignment_id integer primary key autoincrement,destination varchar(20),dormitory varchar(20),money varchar(20),user varchar(20),re_user varchar(20),status varchar(40))";
        String sql3 = "create table his_assignment(his_assignment_id integer primary key autoincrement,destination varchar(20),dormitory varchar(20),money varchar(20),user varchar(20),re_user varchar(20),status varchar(40))";
        String sql4 = "create table address(address_id integer primary key autoincrement,username varchar(20),address varchar(40))";
        db.execSQL(sql);
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
