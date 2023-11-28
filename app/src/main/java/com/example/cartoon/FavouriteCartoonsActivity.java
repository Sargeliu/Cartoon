package com.example.cartoon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.List;

public class FavouriteCartoonsActivity extends AppCompatActivity {

    private FavouriteCartoonsViewModel viewModel;
    private CartoonsAdapter cartoonsAdapter;
    private RecyclerView recyclerViewCartoons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_cartoons);
        initViews();

        viewModel = new ViewModelProvider(this).get(FavouriteCartoonsViewModel.class);
        cartoonsAdapter = new CartoonsAdapter();
        recyclerViewCartoons.setAdapter(cartoonsAdapter);
        recyclerViewCartoons.setLayoutManager(new GridLayoutManager(this, 2));
        viewModel.getAllFavouriteCartoons().observe(this, new Observer<List<Cartoon>>() {
            @Override
            public void onChanged(List<Cartoon> cartoons) {
                cartoonsAdapter.setCartoons(cartoons);
            }
        });
        cartoonsAdapter.setOnCartoonClickListener(new CartoonsAdapter.OnCartoonClickListener() {
            @Override
            public void onCartoonClick(Cartoon cartoon) {
                Intent intent = CartoonDetailActivity.newIntent(FavouriteCartoonsActivity.this,
                        cartoon);
                startActivity(intent);
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FavouriteCartoonsActivity.class);
    }

    private void initViews() {
        recyclerViewCartoons = findViewById(R.id.recyclerViewCartoons);
    }
}