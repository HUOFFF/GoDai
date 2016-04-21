package com.team7.godai.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team7.godai.domain.User;

public class UserService {
	private DatabaseHelper dbHelper;
	public UserService(Context context){
		dbHelper=new DatabaseHelper(context);
	}

	public boolean login(String username,String password){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=? and password=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username,password});
		if(cursor.moveToFirst()==true){
			cursor.close();
			return true;
		}
		return false;
	}

	public boolean register(User user){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="insert into user(username,password) values(?,?)";
		Object obj[]={user.getUsername(),user.getPassword()};
		sdb.execSQL(sql, obj);	
		return true;
	}
}
