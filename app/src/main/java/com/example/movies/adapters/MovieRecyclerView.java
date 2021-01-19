package com.example.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieLiestener onMovieLiestener;

    public MovieRecyclerView(OnMovieLiestener onMovieLiestener) {
        this.onMovieLiestener = onMovieLiestener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,
                parent, false);

        return new MovieViewHolder(view, (View.OnClickListener) onMovieLiestener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((MovieViewHolder) holder).title.setText(mMovies.get(i).getTitle());
        ((MovieViewHolder) holder).release_date.setText(mMovies.get(i).getRelease_date());
        ((MovieViewHolder) holder).duration.setText(mMovies.get(i).getRuntime());

        ((MovieViewHolder) holder).ratingBar.setRating((mMovies.get(i).getVote_averege()) / 2);

        Glide.with(holder.itemView.getContext())
                .load(mMovies.get(i).getPoster_path())
                .into((((MovieViewHolder) holder).imageView));
    }


    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }


    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
    }
}
