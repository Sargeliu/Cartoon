package com.example.cartoon;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ReviewsResponse implements Serializable {
    @SerializedName("docs")
    private List<Review> reviews;

    public ReviewsResponse(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return "ReviewsResponse{" +
                "reviews=" + reviews +
                '}';
    }
}