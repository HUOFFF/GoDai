package demo.godai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import database.UserService;

/**
 * Created by Rong on 2016/3/25.
 */
public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        findViews();

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                //Log.i("TAG", name + "_" + pass);
                UserService uService = new UserService(LoginActivity.this);
                boolean flag = uService.login(name, pass);
                if (flag) {
                    Log.i("TAG", "登录成功");
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.setClass(LoginActivity.this, MainActivity.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Log.i("TAG", "登录失败");
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void findViews() {
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        login=(Button) findViewById(R.id.login);
        register=(Button) findViewById(R.id.register);

    }
}
