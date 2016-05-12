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

	public void add_id_phone(String user_id,String phone,String username){
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		String sql = "update user set user_id = ?,phone = ? where username=?";
		Object obj[]={user_id,phone,username};
		sdb.execSQL(sql, obj);
	}

	public boolean is_express(String username){
		String id ="-1";
		String phone = "-1";
		SQLiteDatabase sdb = dbHelper.getReadableDatabase();
		String sql = "select user_id,phone from user where username=?";
		Cursor cursor = sdb.rawQuery(sql,new String[]{username});
		while(cursor.moveToNext()){
			id = cursor.getString(cursor.getColumnIndex("user_id"));
			phone = cursor.getString(cursor.getColumnIndex("phone"));
		}
		cursor.close();
		if(id.equals("")||phone.equals("")) return false;
		else return true;
	}

	public void update_user(String gender,String password,String phone,String school,String username){
		SQLiteDatabase sdb = dbHelper.getWritableDatabase();
		String sql = "update user set gender = ?,password = ?,phone = ?,school = ? where username=?";
		Object obj[]={gender,password,phone,school,username};
		sdb.execSQL(sql, obj);
	}

}
