package com.team7.godai.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team7.godai.database.DatabaseHelper;
import com.team7.godai.domain.Assignment;

import java.util.ArrayList;

/**
 * Created by mm on 2016/4/20.
 */
public class Re_AssignmentService {
    private DatabaseHelper dbHelper;
    public Re_AssignmentService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean Re_assignment(Assignment assignment) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into re_assignment(re_assignment_id,destination,dormitory,money,user,re_user,status) values(?,?,?,?,?,?,?)";
        Object obj[] = {assignment.getAssignment_id(),assignment.getDestination(), assignment.getDormitory(), assignment.getMoney(),assignment.getUser(),assignment.getRe_User(),assignment.getStatus()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public String getRe_User(int assignment_id) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select re_user from re_assignment where re_assignment_id=?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{""+assignment_id+""});
        if(!cursor.moveToFirst()){
            return "未被领取";
        }
        String re_user = cursor.getString(cursor.getColumnIndex("re_user"));
        cursor.close();
        return re_user;
    }

    public ArrayList getRe_Assignment(String re_user) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select destination,dormitory,money,user,re_user,status from re_assignment where re_user=?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{re_user});
        ArrayList<Assignment> assignment = new ArrayList<>();
        while(cursor.moveToNext()){
            Assignment item = new Assignment();
            item.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            item.setDormitory(cursor.getString(cursor.getColumnIndex("dormitory")));
            item.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            item.setUser(cursor.getString(cursor.getColumnIndex("user")));
            item.setRe_User(cursor.getString(cursor.getColumnIndex("re_user")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            assignment.add(item);
        }
        cursor.close();
        return assignment;
    }

    public void FinishRe_Assignment(int assignment_id) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "delete from re_assignment where re_assignment_id="+assignment_id+"";
        sdb.execSQL(sql);
    }

}
