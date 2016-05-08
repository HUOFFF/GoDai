package com.team7.godai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

/**
 * Created by mai on 2016/5/3.
 */
public class User_edit extends AppCompatActivity{

    Toolbar mToolbar;

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
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(User_edit.this, ExpActivity.class);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}