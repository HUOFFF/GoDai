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
public class AssignmentService {
    private DatabaseHelper dbHelper;
    public AssignmentService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean Pub(Assignment assignment) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into assignment(destination,dormitory,money,user,status,re_OR_not) values(?,?,?,?,?,?)";
        Object obj[] = {assignment.getDestination(), assignment.getDormitory(), assignment.getMoney(),assignment.getUser(),assignment.getStatus(),assignment.getre_OR_not()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public ArrayList GetAssignment() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from assignment";
        Cursor cursor = sdb.rawQuery(sql,null);
        ArrayList<Assignment> assignment = new ArrayList<>();
        while(cursor.moveToNext()){
            Assignment item = new Assignment();
            item.setAssignment_id(cursor.getInt(cursor.getColumnIndex("assignment_id")));
            item.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            item.setDormitory(cursor.getString(cursor.getColumnIndex("dormitory")));
            item.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            item.setUser(cursor.getString(cursor.getColumnIndex("user")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            item.setre_OR_not(cursor.getString(cursor.getColumnIndex("re_OR_not")));
            assignment.add(item);
        }
        cursor.close();
        return assignment;
    }

    public ArrayList GetPub_Assignment(String user) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select assignment_id,destination,dormitory,money,user,status from assignment where user=?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{user});
        ArrayList<Assignment> assignment = new ArrayList<>();
        while(cursor.moveToNext()){
            Assignment item = new Assignment();
            item.setAssignment_id(cursor.getInt(cursor.getColumnIndex("assignment_id")));
            item.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            item.setDormitory(cursor.getString(cursor.getColumnIndex("dormitory")));
            item.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            item.setUser(cursor.getString(cursor.getColumnIndex("user")));
            item.setStatus(cursor.getString(cursor.getColumnIndex("status")));
            assignment.add(item);
        }
        cursor.close();
        return assignment;
    }

    public void changeAssignment_status(String status,String re_OR_not,int assignment_id){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "update assignment set status = '"+status +"' , re_OR_not = '"+ re_OR_not+"' where assignment_id = "+assignment_id+"";
        sdb.execSQL(sql);

    }

    public String getAssignment_status(int assignment_id){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select re_OR_not from assignment where assignment_id = ?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{"" + assignment_id + ""});
        cursor.moveToNext();
        String re_OR_not = cursor.getString(cursor.getColumnIndex("re_OR_not"));
        cursor.close();
        return re_OR_not ;
    }

    public void FinishAssignment(int assignment_id) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "delete from assignment where assignment_id="+assignment_id+"";
        sdb.execSQL(sql);
    }

}
