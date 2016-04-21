package demo.godai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import database.UserService;
import domain.User;
/**
 * Created by Rong on 2016/3/25.
 */
public class RegisterActivity extends AppCompatActivity {
    private Button RI;
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        findViews();
        RI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();
               // Log.i("TAG", name + "_" + pass);
                UserService uService = new UserService(RegisterActivity.this);
                User user = new User();
                user.setUsername(name);
                user.setPassword(pass);
                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(intent);
                RegisterActivity.this.finish();

            }
        });
    }

    private void findViews() {
        username = (EditText) findViewById(R.id.re_username);
        password = (EditText) findViewById(R.id.re_login_paw);
        RI = (Button) findViewById(R.id.register_and_login);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(RegisterActivity.this,LoginActivity.class);
            this.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

