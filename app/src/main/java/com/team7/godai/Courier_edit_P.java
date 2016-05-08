package com.team7.godai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by mai on 2016/5/3.
 */
public class Courier_edit_P extends AppCompatActivity{

    Toolbar mToolbar;
    EditText idText;
    EditText phoneText;
    Button mButton;

    public Courier_edit_P() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.be_courier);

        mToolbar = (Toolbar)findViewById(R.id.tool_bar);
        idText = (EditText)findViewById(R.id.be_courier_id);
        phoneText = (EditText)findViewById(R.id.be_courier_phone);
        mButton = (Button)findViewById(R.id.submit_real);

        mToolbar.setTitle("申请快递员");
        mToolbar.setTitleTextColor(0xffffffff);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = idText.getText().toString().trim();
                String phone = phoneText.getText().toString().trim();

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