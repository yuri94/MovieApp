package com.example.movies.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, releaseDate, duration, vote, rating;
    ImageView imageView;
    RatingBar ratingBar;

    OnMovieLiestener onMovieLiestener;


    public MovieViewHolder(@NonNull View itemView, OnMovieLiestener onMovieLiestener) {
        super(itemView);

        this.onMovieLiestener = onMovieLiestener;

        title = itemView.findViewById(R.id.textViewTitle);
        releaseDate = itemView.findViewById((R.id.textViewReleaseDate));
        imageView = itemView.findViewById(R.id.imageView);
        rating = itemView.findViewById(R.id.textViewRating);
        //ratingBar = itemView.findViewById(R.id.textViewRating);
        ratingBar = itemView.findViewById(R.id.ratingBar);
        vote = itemView.findViewById(R.id.textViewVote);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
            onMovieLiestener.onMovieClick(getAdapterPosition());
    }
}
