package com.example.cartoon;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import retrofit2.http.GET;

@Dao
public interface CartoonDao {

    @Query("SELECT * FROM favourite_cartoon")
    LiveData<List<Cartoon>> getAllFavouriteCartoons();

    @Query("SELECT * FROM favourite_cartoon WHERE id = :cartoonId")
    LiveData<Cartoon> getFavouriteCartoon(int cartoonId);

    @Insert
    Completable insertCartoon(Cartoon cartoon);

    @Query("DELETE FROM favourite_cartoon WHERE id = :cartoonId")
    Completable removeCartoon(int cartoonId);
}
