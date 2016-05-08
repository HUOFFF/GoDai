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
import android.widget.EditText;

import com.team7.godai.R;
import com.team7.godai.Service.UserService;

/**
 * Created by mai on 2016/5/3.
 */
public class Courier_edit_P extends AppCompatActivity {

    Toolbar mToolbar;
    EditText idText;
    EditText phoneText;
    Button mButton;
    Courier_edit_P courier_edit_p;

    public Courier_edit_P() {
        // Required empty public constructor
        courier_edit_p = this;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.be_courier);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        idText = (EditText) findViewById(R.id.be_courier_id);
        phoneText = (EditText) findViewById(R.id.be_courier_phone);
        mButton = (Button) findViewById(R.id.submit_real);

        mToolbar.setTitle("申请快递员");
        mToolbar.setTitleTextColor(0xffffffff);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(courier_edit_p)
                        .setTitle("申请快递员")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                UserService userService = new UserService(courier_edit_p);
                                String id = idText.getText().toString().trim();
                                String phone = phoneText.getText().toString().trim();
                                userService.add_id_phone(id, phone, LoginActivity.getUsername());
                                idText.setText("");
                                phoneText.setText("");
                                Intent intent = new Intent();
                                intent.setClass(Courier_edit_P.this, PubActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton("取消", null)
                        .show();
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(Courier_edit_P.this, PubActivity.class);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}