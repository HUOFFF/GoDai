package com.team7.godai.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team7.godai.domain.Assignment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mm on 2016/4/20.
 */
public class AssignmentService {
    private AssignmentDB dbHelper;
    public AssignmentService(Context context){
        dbHelper=new AssignmentDB(context);
    }

    public boolean Pub(Assignment assignment) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into assignment(destination,dormitory,money) values(?,?,?)";
        Object obj[] = {assignment.getDestination(), assignment.getDormitory(), assignment.getMoney()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public ArrayList GetAssignment() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from assignment";
        //String tb_count = "SELECT COUNT(*) FROM assignment";
        Cursor cursor = sdb.rawQuery(sql,null);
        ArrayList<Assignment> assignment = new ArrayList<>();
        /*while(cursor.moveToFirst()){
            Assignment item = new Assignment();
            item.setDestination(cursor.getString(1));
            item.setDormitory(cursor.getString(2));
            item.setMoney(cursor.getString(3));
        }*/
//        for(int i=0;i<cursor.getCount();i++){
//            Assignment item = new Assignment();
//            item.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
//            item.setDormitory(cursor.getString(cursor.getColumnIndex("dormitory")));
//            item.setMoney(cursor.getString(cursor.getColumnIndex("money")));
//            cursor.moveToNext();
//        }
        while(cursor.moveToNext()){
            Assignment item = new Assignment();
            item.setDestination(cursor.getString(cursor.getColumnIndex("destination")));
            item.setDormitory(cursor.getString(cursor.getColumnIndex("dormitory")));
            item.setMoney(cursor.getString(cursor.getColumnIndex("money")));
            assignment.add(item);
        }
        cursor.close();
        return assignment;
    }

}
