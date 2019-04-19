package com.example.vinay.fireebase;

public class Items {

    String name;
    String comment;
    String  date;

    public Items(String name, String comment, String date) {
        this.name = name;
        this.comment = comment;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getComment() {
        return comment;
    }

    public String getDate() {
        return date;
    }
}
