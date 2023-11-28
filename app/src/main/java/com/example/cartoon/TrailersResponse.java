package com.example.cartoon;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TrailersResponse implements Serializable {
    @SerializedName("docs")
    private List<TrailersList> trailersLists;

    public TrailersResponse(List<TrailersList> trailersLists) {
        this.trailersLists = trailersLists;
    }

    public List<TrailersList> getTrailersLists() {
        return trailersLists;
    }

    @Override
    public String toString() {
        return "TrailersResponse{" +
                "trailersLists=" + trailersLists +
                '}';
    }
}