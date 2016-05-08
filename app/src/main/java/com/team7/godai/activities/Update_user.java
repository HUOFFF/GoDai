package com.team7.godai.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

/**
 * Created by mai on 2016/5/3.
 */
public class Update_user extends AppCompatActivity {

    Toolbar mToolbar;
    Update_user update_user_p;
    TextView gender;
    TextView password;
    TextView phone;
    TextView school;
    Button mbutton;

    public Update_user() {
        // Required empty public constructor
        update_user_p = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("修改信息");
        mToolbar.setTitleTextColor(0xffffffff);

        initView();
    }

    public void initView() {
        gender = (TextView) findViewById(R.id.ed_gender);
        password = (TextView) findViewById(R.id.ed_password);
        phone = (TextView) findViewById(R.id.ed_phone);
        school = (TextView) findViewById(R.id.ed_school);
        mbutton = (Button) findViewById(R.id.update_user);


        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(update_user_p)
                        .setTitle("修改信息")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String mgender = gender.getText().toString().trim();
                                String mpassword = password.getText().toString().trim();
                                String mphone = phone.getText().toString().trim();
                                String mschool = school.getText().toString().trim();

                                UserService userService = new UserService(update_user_p);
                                userService.update_user(mgender, mpassword, mphone, mschool, LoginActivity.getUsername());
                                Intent intent = new Intent();
                                intent.setClass(Update_user.this, User_edit.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(Update_user.this, PubActivity.class);
            startActivity(intent);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}