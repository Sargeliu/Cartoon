package com.example.cartoon;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Videos implements Serializable {
    @SerializedName("trailers")
    private List<Trailer> trailers;

    public Videos(List<Trailer> trailers) {
        this.trailers = trailers;
    }

    public List<Trailer> getTrailers() {
        return trailers;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "trailers=" + trailers +
                '}';
    }
}