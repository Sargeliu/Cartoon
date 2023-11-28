package com.example.cartoon;

import android.app.Application;
import android.text.SegmentFinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartoonDetailViewModel extends AndroidViewModel {

    private static final String TAG = "CartoonDetailViewModel";

    private CartoonDao cartoonDao;
    private MutableLiveData<List<Review>> review = new MutableLiveData<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    public CartoonDetailViewModel(@NonNull Application application) {
        super(application);
        cartoonDao = CartoonsDatabase.getInstance(application).cartoonDao();
    }

    public LiveData<List<Review>> getReviews() {
        return review;
    }

    public LiveData<Cartoon> getFavouriteCartoon(int cartoonId) {
        return cartoonDao.getFavouriteCartoon(cartoonId);
    }

    public void loadReviews(int id) {
        Disposable disposable = ApiFactory.apiService.loadReviews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ReviewsResponse, List<Review>>() {
                    @Override
                    public List<Review> apply(ReviewsResponse reviewsResponse) throws Throwable {
                        return reviewsResponse.getReviews();
                    }
                })
                .subscribe(new Consumer<List<Review>>() {
                    @Override
                    public void accept(List<Review> reviewList) throws Throwable {
                        review.setValue(reviewList);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void insertCartoon(Cartoon cartoon) {
        Disposable disposable = cartoonDao.insertCartoon(cartoon)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void removeCartoon(int cartoonId) {
        Disposable disposable = cartoonDao.removeCartoon(cartoonId)
                .subscribeOn(Schedulers.io())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
