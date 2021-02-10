package com.example.movies.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, release_date, duration, movie_category;
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieLiestener onMovieLiestener;


    public MovieViewHolder(@NonNull View itemView, OnMovieLiestener onMovieLiestener) {
        super(itemView);

        this.onMovieLiestener = onMovieLiestener;

        title = itemView.findViewById(R.id.movie_title);
        release_date = itemView.findViewById((R.id.movie_category));
        //movie_category = itemView.findViewById(R.id.movie_category);
        duration = itemView.findViewById(R.id.movie_duration);

        imageView = itemView.findViewById(R.id.movie_img);
        ratingBar = itemView.findViewById(R.id.rating_bar);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            onMovieLiestener.onMovieClick(getAdapterPosition());
    }
}
