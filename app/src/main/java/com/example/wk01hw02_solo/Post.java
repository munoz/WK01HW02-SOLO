package com.example.wk01hw02_solo;

import com.google.gson.annotations.SerializedName;

public class Post {

    private static int userId;


    private int id;

    private String title;

    @SerializedName("body")
    private String text;

    public static int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
