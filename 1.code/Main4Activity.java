package com.example.library;

// Main4Activity.java
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<Book> adapter;
    ArrayList<Book> bookList;
    DataBase dataBase;
    Button buttonBackToMain2;
    final static int CONTEXT_MENU_UPDATE = 1;
    final static int CONTEXT_MENU_DELETE = 2;
    final static int UPDATE_BOOK_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a4);

        listView = findViewById(R.id.listView);
        dataBase = new DataBase(this);
        bookList = dataBase.getAllBooks();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookList);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
        buttonBackToMain2 = findViewById(R.id.buttonBackToMain2);
        // 设置返回按钮的点击事件
        buttonBackToMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回界面2
                Intent intent = new Intent(Main4Activity.this, Main2Activity.class);
                startActivity(intent);
                finish(); // 结束当前Activity
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("操作");
        menu.add(0, CONTEXT_MENU_UPDATE, 0, "更新图书");
        menu.add(0, CONTEXT_MENU_DELETE, 1, "删除图书");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case CONTEXT_MENU_UPDATE:
                // 获取选中的图书信息
                Book selectedBook = adapter.getItem(info.position);
                // 跳转到界面3，传递选中的图书信息
                Intent intent = new Intent(Main4Activity.this, Main3Activity.class);
                intent.putExtra("bookName", selectedBook.getBookname());
                intent.putExtra("price", selectedBook.getPrice());
                intent.putExtra("bookNum", selectedBook.getBooknum());
                startActivityForResult(intent, UPDATE_BOOK_REQUEST);
                return true;
            case CONTEXT_MENU_DELETE:
                // 删除选中的图书记录
                Book deletedBook = adapter.getItem(info.position);
                dataBase.deleteBook(deletedBook.getBookname());
                // 更新界面显示
                bookList.remove(deletedBook);
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UPDATE_BOOK_REQUEST && resultCode == RESULT_OK) {
            // 更新界面显示
            bookList.clear();
            bookList.addAll(dataBase.getAllBooks());
            adapter.notifyDataSetChanged();
        }
    }
}

