package com.team7.godai;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team7.godai.Service.AddressService;
import com.team7.godai.adapter.Address_Adapter;
import com.team7.godai.adapter.Assignment_RecyclerAdapter;

import java.util.ArrayList;

/**
 * Created by mai on 2016/5/3.
 */
public class Location_edit_P extends AppCompatActivity {

    Toolbar mToolbar;
    Button mButton;
    RecyclerView mRecyclerView;
    Location_edit_P location_edit_p;
    EditText new_address;
    ArrayList<String> address1 = new ArrayList<>();

    public Location_edit_P() {
        // Required empty public constructor
        location_edit_p = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mButton = (Button) findViewById(R.id.add_address);
        mRecyclerView = (RecyclerView) findViewById(R.id.address);

        mToolbar.setTitle("管理地址");
        mToolbar.setTitleTextColor(0xffffffff);

        new_address = new EditText(location_edit_p);
        initView();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(location_edit_p)
                        .setTitle("请输入地址")
                        .setView(new_address)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String address = new_address.getText().toString().trim();
                                AddressService addressService = new AddressService(location_edit_p);
                                addressService.add_address(LoginActivity.getUsername(), address);
                                initView();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
            }
        });
    }

    public void initView() {
        AddressService addressService = new AddressService(location_edit_p);
        address1 = addressService.getAddress(LoginActivity.getUsername());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(new Address_Adapter(location_edit_p, address1));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(Location_edit_P.this, PubActivity.class);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}