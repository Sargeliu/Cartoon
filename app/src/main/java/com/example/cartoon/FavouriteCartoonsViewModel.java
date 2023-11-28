package com.example.cartoon;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteCartoonsViewModel extends AndroidViewModel {

    private CartoonDao cartoonDao;

    public FavouriteCartoonsViewModel(@NonNull Application application) {
        super(application);
        cartoonDao = CartoonsDatabase.getInstance(application).cartoonDao();
    }

    public LiveData<List<Cartoon>> getAllFavouriteCartoons() {
        return cartoonDao.getAllFavouriteCartoons();
    }
}
