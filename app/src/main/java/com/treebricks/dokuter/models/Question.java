package com.treebricks.dokuter.models;

import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("name")
    private String name;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return
                "Question{" +
                        "name = '" + name + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",id = '" + id + '\'' +
                        ",title = '" + title + '\'' +
                        "}";
    }
}