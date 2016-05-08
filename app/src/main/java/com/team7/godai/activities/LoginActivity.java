package com.team7.godai.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.team7.godai.R;
import com.team7.godai.Service.UserService;

/**
 * Created by Rong on 2016/3/25.
 */
public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login, register;
    RadioGroup select;
    static RadioButton radio_pub, radio_exp;
    public static String name, pass;
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        findViews();


        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name = username.getText().toString();
                pass = password.getText().toString();
                //Log.i("TAG", name + "_" + pass);
                UserService uService = new UserService(LoginActivity.this);
                boolean flag = uService.login(name, pass);

                if (uService.user_test(name)) {
                    Log.i("TAG", "该用户不存在，请注册");
                    username.setText("");
                    password.setText("");
                    Toast.makeText(LoginActivity.this, "该用户不存在，请注册", Toast.LENGTH_LONG).show();
                } else if (flag && radio_pub.isChecked()) {
                    Log.i("TAG", "登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, PubActivity.class);
                    LoginActivity.this.startActivity(intent);
                    finish();

                    username.setText("");
                    password.setText("");

                } else if (flag && radio_exp.isChecked()) {
                    UserService userService = new UserService(getApplicationContext());
                    if (userService.is_express(name)) {
                        Log.i("TAG", "登录成功");
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this, ExpActivity.class);
                        LoginActivity.this.startActivity(intent);
                        finish();
                        username.setText("");
                        password.setText("");
                    } else {
                        Log.i("TAG", "尚未成为快递员，请以发布者身份登录");
                        Toast.makeText(LoginActivity.this, "尚未成为快递员，请以发布者身份登录", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    if (!radio_pub.isChecked() && !radio_exp.isChecked() && flag) {
                        Log.i("TAG", "请选择登录身份");
                        Toast.makeText(LoginActivity.this, "请选择登录身份", Toast.LENGTH_SHORT).show();
                        password.setText("");
                    } else {
                        Log.i("TAG", "密码错误，请重新输入");
                        Toast.makeText(LoginActivity.this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
                        password.setText("");
                    }

                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void findViews() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        register = (Button) findViewById(R.id.register);
        select = (RadioGroup) findViewById(R.id.select_radio);
        radio_pub = (RadioButton) findViewById(R.id.radio_pub);
        radio_exp = (RadioButton) findViewById(R.id.radio_exp);
    }

    public static String getUsername() {
        return name;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
