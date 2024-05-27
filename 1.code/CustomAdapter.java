package com.example.library;

// CustomAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Book> {

    public CustomAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 获取数据项位置的数据对象
        Book book = getItem(position);
        // 检查是否已经有视图，如果没有，就充气一个
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        // 查找视图以便设置数据
        TextView textViewBookInfo = convertView.findViewById(R.id.textViewBookInfo);
        // 设置数据
        String bookInfo = String.format("名称：%s\n价格：%s\n数量：%s",
                book.getBookname(), book.getPrice(), book.getBooknum());
        textViewBookInfo.setText(bookInfo);
        // 返回视图以便显示在列表中
        return convertView;
    }
}

