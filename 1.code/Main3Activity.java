package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main3Activity extends AppCompatActivity {
    EditText editTextBookName, editTextPrice, editTextBookNum;
    Button buttonAction;
    DataBase dataBase;
    boolean isUpdate = false;
    String originalBookName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a3);

        editTextBookName = findViewById(R.id.editTextBookName);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextBookNum = findViewById(R.id.editTextBookNum);
        buttonAction = findViewById(R.id.buttonAction);
        dataBase = new DataBase(this);

        Intent intent = getIntent();
        if (intent.hasExtra("bookName")) {
            isUpdate = true;
            originalBookName = intent.getStringExtra("bookName");
            editTextBookName.setText(originalBookName);
            editTextPrice.setText(String.valueOf(intent.getDoubleExtra("price", 0)));
            editTextBookNum.setText(String.valueOf(intent.getIntExtra("bookNum", 0)));
            buttonAction.setText("更新");
        }

        buttonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookName = editTextBookName.getText().toString();
                double price = Double.parseDouble(editTextPrice.getText().toString());
                int bookNum = Integer.parseInt(editTextBookNum.getText().toString());

                if (isUpdate) {
                    // 更新操作
                    long result = dataBase.updateBook(originalBookName, bookName, price, bookNum);
                    if (result != -1) {
                        // 更新成功，返回更新后的信息给界面4
                        Intent updateIntent = new Intent();
                        updateIntent.putExtra("bookName", bookName);
                        updateIntent.putExtra("price", price);
                        updateIntent.putExtra("bookNum", bookNum);
                        setResult(RESULT_OK, updateIntent);
                        finish();
                    } else {
                        Toast.makeText(Main3Activity.this, "更新失败！", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // 添加操作
                    long result = dataBase.addBook(bookName, price, bookNum);
                    if (result != -1) {
                        // 添加成功，返回添加的信息给界面4
                        Intent addIntent = new Intent();
                        addIntent.putExtra("bookName", bookName);
                        addIntent.putExtra("price", price);
                        addIntent.putExtra("bookNum", bookNum);
                        setResult(RESULT_OK, addIntent);
                        finish();
                    } else {
                        Toast.makeText(Main3Activity.this, "添加失败！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
