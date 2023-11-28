package com.example.cartoon;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {
    @SerializedName("site")
    private String site;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;

    public Trailer(String site, String name, String url) {
        this.site = site;
        this.name = name;
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "site='" + site + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}