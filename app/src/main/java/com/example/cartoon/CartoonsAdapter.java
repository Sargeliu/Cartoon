package com.example.cartoon;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartoonsAdapter extends RecyclerView.Adapter<CartoonsAdapter.CartoonViewHolder> {


    private List<Cartoon> cartoons = new ArrayList<>();
    private OnReachEndListener onReachEndListener;
    private OnCartoonClickListener onCartoonClickListener;

    public void setOnCartoonClickListener(OnCartoonClickListener onCartoonClickListener) {
        this.onCartoonClickListener = onCartoonClickListener;
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setCartoons(List<Cartoon> cartoons) {
        this.cartoons = cartoons;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartoonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.cartoon_item,
                parent,
                false
        );
        return new CartoonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartoonViewHolder holder, int position) {
        Cartoon cartoon = cartoons.get(position);
        Glide.with(holder.itemView)
                .load(cartoon.getPoster().getUrl())
                .into(holder.imageViewPoster);
        double rating = cartoon.getRating().getKp();
        int backgroundId;
        if (rating > 9) {
            backgroundId = R.drawable.circle_green;
        } else if (rating > 8) {
            backgroundId = R.drawable.circle_orange;
        } else {
            backgroundId = R.drawable.circle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(), backgroundId);
        holder.textViewRating.setBackground(background);
        holder.textViewRating.setText(String.format("%.1f", rating));
        holder.textViewYear.setText(String.valueOf(cartoon.getYear()));
        if (position >= cartoons.size() - 10 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCartoonClickListener != null) {
                    onCartoonClickListener.onCartoonClick(cartoon);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartoons.size();
    }

    interface OnReachEndListener {
        void onReachEnd();
    }

    interface OnCartoonClickListener {
        void onCartoonClick(Cartoon cartoon);
    }

    static class CartoonViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageViewPoster;
        private final TextView textViewRating;
        private final TextView textViewYear;

        public CartoonViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewYear = itemView.findViewById(R.id.textViewYear);
        }
    }
}
