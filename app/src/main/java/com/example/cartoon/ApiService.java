package com.example.cartoon;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1.4/movie?token=6H5E8VR-C8AME3F-NT73Q1F-GRX4DZV&limit=10&type=cartoon&rating.kp=7-10")
    Single<CartoonResponse> loadCartoons(@Query("page") int page);

    @GET("v1.4/review?token=6H5E8VR-C8AME3F-NT73Q1F-GRX4DZV&page=1&limit=5&sortField=movieId" +
            "&sortType=1&type=Нейтральный")
    Single<ReviewsResponse> loadReviews(@Query("movieId") int id);

    @GET("movie?token=6H5E8VR-C8AME3F-NT73Q1F-GRX4DZV&limit=1&selectFields=videos&sortField" +
            "=videos.trailers.url&sortType=-1&field=id")
    Single<TrailersResponse> loadTrailers(@Query("search") int id);
}
