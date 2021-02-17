package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.Movie;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> mMovies;
    private OnMovieLiestener onMovieLiestener;

    public MovieRecyclerView(OnMovieLiestener onMovieLiestener) {
        this.onMovieLiestener = onMovieLiestener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                parent, false);

        return new MovieViewHolder(view, onMovieLiestener);
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.movie_list_item;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        Movie movie = mMovies.get(i);
        holder.title.setText(movie.getTitle());
        holder.vote.setText(String.valueOf(movie.getVoteCount()));
        holder.releaseDate.setText(movie.getReleaseDate());
        holder.rating.setText(String.valueOf(movie.getPopularity()));
        holder.ratingBar.setRating(movie.getVoteAverege());

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<Movie> mMovies) {
        this.mMovies = mMovies;
    }
}
