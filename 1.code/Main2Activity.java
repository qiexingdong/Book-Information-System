package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    Button buttonAdd, buttonUpdate;
    ListView listViewBooks;
    CustomAdapter customAdapter;
    ArrayList<Book> bookList;
    DataBase dataBase;
    static final int ADD_BOOK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a2);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        listViewBooks = findViewById(R.id.listViewBooks);
        dataBase = new DataBase(this);
        bookList = dataBase.getAllBooks(); // 获取所有图书数据
        customAdapter = new CustomAdapter(this, bookList); // 创建适配器
        listViewBooks.setAdapter(customAdapter); // 设置适配器

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到Main3Activity
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                startActivity(intent);
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到Main3Activity
                Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_BOOK_REQUEST && resultCode == RESULT_OK) {
            // 添加图书成功，重新加载数据
            bookList = dataBase.getAllBooks();
            customAdapter = new CustomAdapter(this, bookList);
            listViewBooks.setAdapter(customAdapter);
        }
    }
}
