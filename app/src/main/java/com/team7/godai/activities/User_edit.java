package com.team7.godai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.team7.godai.R;
import com.team7.godai.Service.UserService;
import com.team7.godai.domain.User;

import java.util.ArrayList;

/**
 * Created by mai on 2016/5/3.
 */
public class User_edit extends AppCompatActivity{

    Toolbar mToolbar;
    Button mButton;

    public User_edit() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);

        mToolbar = (Toolbar)findViewById(R.id.tool_bar);

        mToolbar.setTitle("修改信息");
        mToolbar.setTitleTextColor(0xffffffff);
        initView();
    }


    public void initView(){
        TextView gender = (TextView)findViewById(R.id.user_info_gender);
        TextView user_id = (TextView)findViewById(R.id.user_info_id);
        TextView phone = (TextView)findViewById(R.id.user_info_phone);
        TextView school = (TextView)findViewById(R.id.user_info_school);
        TextView user_type = (TextView)findViewById(R.id.user_info_type);
        mButton = (Button)findViewById(R.id.update_user_info);

        UserService userService = new UserService(getApplicationContext());
        ArrayList<User> user_info = userService.getUser_info(LoginActivity.getUsername());
        gender.setText(user_info.get(0).getGender());
        user_id.setText(user_info.get(0).getUser_id());
        phone.setText(user_info.get(0).getPhone());
        school.setText(user_info.get(0).getSchool());
        if(userService.is_express(LoginActivity.getUsername())){
            user_type.setText("发布者&快递员");
        }
        else  user_type.setText("发布者");

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(User_edit.this, Update_user.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(User_edit.this, ExpActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}