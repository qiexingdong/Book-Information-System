package com.example.library;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DataBase {
    private DataBaseHelper dbHelper;

    public DataBase(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    // 添加用户
    public long addUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_USERNAME, username);
        values.put(DataBaseHelper.COLUMN_PASSWORD, password);
        long result = db.insert(DataBaseHelper.TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // 添加图书
    public long addBook(String bookName, double price, int bookNum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_BOOK_NAME, bookName);
        values.put(DataBaseHelper.COLUMN_PRICE, price);
        values.put(DataBaseHelper.COLUMN_BOOK_NUM, bookNum);
        long result = db.insert(DataBaseHelper.TABLE_BOOKS, null, values);
        db.close();
        return result;
    }
    // 获取所有图书信息
    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(DataBaseHelper.TABLE_BOOKS, null, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String bookName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COLUMN_BOOK_NAME));
                @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex(DataBaseHelper.COLUMN_PRICE));
                @SuppressLint("Range") int bookNum = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_BOOK_NUM));
                bookList.add(new Book(bookName, price, bookNum));
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return bookList;
    }
    // 验证用户
    public boolean validateUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DataBaseHelper.COLUMN_USER_ID};
        String selection = DataBaseHelper.COLUMN_USERNAME + "=? AND " + DataBaseHelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(DataBaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    public void deleteBook(String bookName) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DataBaseHelper.TABLE_BOOKS, DataBaseHelper.COLUMN_BOOK_NAME + "=?", new String[]{bookName});
        db.close();
    }
    public long updateBook(String originalBookName, String newBookName, double price, int bookNum) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COLUMN_BOOK_NAME, newBookName);
        values.put(DataBaseHelper.COLUMN_PRICE, price);
        values.put(DataBaseHelper.COLUMN_BOOK_NUM, bookNum);
        String selection = DataBaseHelper.COLUMN_BOOK_NAME + "=?";
        String[] selectionArgs = { originalBookName };
        long result = db.update(DataBaseHelper.TABLE_BOOKS, values, selection, selectionArgs);
        db.close();
        return result;
    }
}

