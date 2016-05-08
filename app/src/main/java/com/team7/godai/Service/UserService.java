package com.team7.godai.Service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.team7.godai.database.DatabaseHelper;
import com.team7.godai.domain.User;

import java.util.ArrayList;

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
		String sql="insert into user(username,password,gender,user_id,phone,school) values(?,?,?,?,?,?)";
		Object obj[]={user.getUsername(),user.getPassword(),user.getGender(),user.getUser_id(),user.getPhone(), user.getSchool()};
		sdb.execSQL(sql, obj);	
		return true;
	}

	public boolean user_test(String username){
		SQLiteDatabase sdb=dbHelper.getReadableDatabase();
		String sql="select * from user where username=?";
		Cursor cursor=sdb.rawQuery(sql, new String[]{username});
		if(cursor.moveToFirst()==false){
			cursor.close();
			return true;
		}
		return false;
	}

	public ArrayList getUser_info(String username){
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select gender,user_id,phone,school from user where username=?";
		Cursor cursor = sdb.rawQuery(sql,new String[]{username});
		ArrayList<User> user_info = new ArrayList<>();
		while(cursor.moveToNext()){
			User item = new User();
			item.setGender(cursor.getString(cursor.getColumnIndex("gender")));
			item.setUser_id(cursor.getString(cursor.getColumnIndex("user_id")));
			item.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
			item.setSchool(cursor.getString(cursor.getColumnIndex("school")));
			user_info.add(item);
		}
		cursor.close();
		return user_info;
	}

}
