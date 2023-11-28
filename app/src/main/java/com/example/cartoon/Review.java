package com.example.cartoon;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {

    @SerializedName("author")
    private final String author;
    @SerializedName("review")
    private final String review;
    @SerializedName("title")
    private final String title;
    @SerializedName("type")
    private final String type;

    public Review(String author, String review, String title, String type) {
        this.author = author;
        this.review = review;
        this.title = title;
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public String getReview() {
        return review;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "DocsItem{" +
                "author='" + author + '\'' +
                ", review='" + review + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}