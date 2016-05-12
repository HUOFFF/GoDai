package com.team7.godai.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team7.godai.database.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by keng on 2016/5/7.
 */
public class AddressService {
    private DatabaseHelper dbHelper;
    public AddressService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public void add_address(String username,String address){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql ="insert into address(username,address) values(?,?)";
        Object obj[] = {username,address};
        sdb.execSQL(sql, obj);
    }

    public ArrayList getAddress(String username){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select address from address where username = ?";
        Cursor cursor = sdb.rawQuery(sql,new String[]{username});
        ArrayList address = new ArrayList();
        while(cursor.moveToNext()){
            address.add(cursor.getString(cursor.getColumnIndex("address")));
        }
        cursor.close();
        return address;
    }

}
