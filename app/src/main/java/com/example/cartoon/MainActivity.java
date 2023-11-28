package com.example.cartoon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Cartoon";

    private RecyclerView recyclerViewCartoons;
    private ProgressBar progressBar;
    private MainViewModel viewModel;
    private CartoonsAdapter cartoonsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        cartoonsAdapter = new CartoonsAdapter();
        recyclerViewCartoons.setAdapter(cartoonsAdapter);
        recyclerViewCartoons.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel.getCartoons().observe(this, new Observer<List<Cartoon>>() {
            @Override
            public void onChanged(List<Cartoon> cartoons) {
                cartoonsAdapter.setCartoons(cartoons);
            }
        });
        cartoonsAdapter.setOnReachEndListener(new CartoonsAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadCartoons();
            }
        });
        cartoonsAdapter.setOnCartoonClickListener(new CartoonsAdapter.OnCartoonClickListener() {
            @Override
            public void onCartoonClick(Cartoon cartoon) {
                Intent intent = CartoonDetailActivity.newIntent(MainActivity.this, cartoon);
                startActivity(intent);
            }
        });
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
    private void initViews() {
        recyclerViewCartoons = findViewById(R.id.recyclerViewCartoons);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itemFavourite) {
            Intent intent = FavouriteCartoonsActivity.newIntent(MainActivity.this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}