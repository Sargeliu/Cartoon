package com.example.cartoon;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TrailersList implements Serializable {

    @SerializedName("videos")
    private Videos videos;

    public TrailersList(Videos videos) {
        this.videos = videos;
    }

    public Videos getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "TrailersList{" +
                "videos=" + videos +
                '}';
    }
}