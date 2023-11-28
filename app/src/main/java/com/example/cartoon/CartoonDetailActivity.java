package com.example.cartoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CartoonDetailActivity extends AppCompatActivity {

    private static final String TAG = "CartoonDetailActivity";
    private static final String EXTRA_CARTOON = "cartoon";
    private ImageView imageViewPoster;
    private ImageView imageViewStar;
    private TextView textViewName;
    private TextView textViewYear;
    private TextView textViewDescription;
    private CartoonDetailViewModel viewModel;
    private ReviewsAdapter reviewsAdapter;
    private RecyclerView recyclerViewReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartoon_detail);
        initViews();

        viewModel = new ViewModelProvider(this).get(CartoonDetailViewModel.class);
        reviewsAdapter = new ReviewsAdapter();
        recyclerViewReviews.setAdapter(reviewsAdapter);
        recyclerViewReviews.setLayoutManager(new LinearLayoutManager(this));

        Cartoon cartoon = (Cartoon) getIntent().getSerializableExtra(EXTRA_CARTOON);
        Glide.with(this)
                .load(cartoon.getPoster().getUrl())
                .into(imageViewPoster);
        textViewName.setText(cartoon.getName());
        textViewYear.setText(String.valueOf(cartoon.getYear()));
        textViewDescription.setText(cartoon.getDescription());

        Drawable starOn = ContextCompat.getDrawable(CartoonDetailActivity.this,
                android.R.drawable.star_big_on);
        Drawable starOff = ContextCompat.getDrawable(CartoonDetailActivity.this,
                android.R.drawable.star_big_off);
        viewModel.getFavouriteCartoon(cartoon.getId()).observe(this, new Observer<Cartoon>() {
            @Override
            public void onChanged(Cartoon cartoonFromDb) {
                if (cartoonFromDb == null) {
                    imageViewStar.setImageDrawable(starOff);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.insertCartoon(cartoon);
                        }
                    });
                } else {
                    imageViewStar.setImageDrawable(starOn);
                    imageViewStar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            viewModel.removeCartoon(cartoon.getId());
                        }
                    });
                }
            }
        });
        viewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviewList) {
                reviewsAdapter.setReviews(reviewList);
            }
        });
        viewModel.loadReviews(cartoon.getId());


        ApiFactory.apiService.loadTrailers(cartoon.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TrailersResponse>() {
                    @Override
                    public void accept(TrailersResponse trailersResponse) throws Throwable {
                        Log.d(TAG, trailersResponse.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d(TAG, throwable.toString());
                    }
                });

    }
    public static Intent newIntent(Context context, Cartoon cartoon) {
        Intent intent = new Intent(context, CartoonDetailActivity.class);
        intent.putExtra(EXTRA_CARTOON, cartoon);
        return intent;
    }
    private void initViews() {
        imageViewPoster = findViewById(R.id.imageViewPoster);
        imageViewStar = findViewById(R.id.imageViewStar);
        textViewName = findViewById(R.id.textViewName);
        textViewYear = findViewById(R.id.textViewYear);
        textViewDescription = findViewById(R.id.textViewDescription);
        recyclerViewReviews = findViewById(R.id.recyclerViewReviews);
    }
}