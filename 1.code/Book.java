package com.example.library;

public class Book {
    private int id;
    private String bookname;
    private double price;
    private int booknum;

    public Book(String bookname, double price, int booknum) {
        this.bookname = bookname;
        this.price = price;
        this.booknum = booknum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBooknum() {
        return booknum;
    }

    public void setBooknum(int booknum) {
        this.booknum = booknum;
    }

    @Override
    public String toString() {
        return "名称：" + bookname + " 价格：" + price + " 数量：" + booknum;
    }
}