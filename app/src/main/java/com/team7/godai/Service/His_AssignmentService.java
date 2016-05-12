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
public class His_AssignmentService {
    private DatabaseHelper dbHelper;
    public His_AssignmentService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean Add_FinishAssignment(Assignment assignment) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into his_assignment(his_assignment_id,destination,dormitory,money,user,re_user,status) values(?,?,?,?,?,?,?)";
        Object obj[] = {assignment.getAssignment_id(),assignment.getDestination(), assignment.getDormitory(), assignment.getMoney(),assignment.getUser(),assignment.getRe_User(),assignment.getStatus()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public ArrayList GetHis_Assignment() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from his_assignment";
        Cursor cursor = sdb.rawQuery(sql, null);
        ArrayList<Assignment> assignment = new ArrayList<>();
        while(cursor.moveToNext()){
            Assignment item = new Assignment();
            item.setAssignment_id(cursor.getInt(cursor.getColumnIndex("his_assignment_id")));
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


    public void FinishAssignment(int assignment_id) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "delete from his_assignment where "+assignment_id+"";
        sdb.execSQL(sql);
    }

}
