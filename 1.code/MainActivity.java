package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
// MainActivity.java
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editTextUsername, editTextPassword;
    Button buttonLogin;
    DataBase dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a1);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        dataBase = new DataBase(this);

        // 添加 root 用户
        long result = dataBase.addUser("root", "123456");
        if (result != -1) {
            Toast.makeText(MainActivity.this, "root 用户添加成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "root 用户添加失败！", Toast.LENGTH_SHORT).show();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();

                if (dataBase.validateUser(username, password)) {
                    // 用户验证通过，跳转到界面2
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                } else {
                    // 用户验证失败，显示错误消息
                    Toast.makeText(MainActivity.this, "用户身份信息错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
