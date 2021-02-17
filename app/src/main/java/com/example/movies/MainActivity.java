package com.example.movies;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.adapters.MovieRecyclerView;
import com.example.movies.adapters.OnMovieLiestener;
import com.example.movies.models.Movie;
import com.example.movies.viewmodels.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnMovieLiestener {


    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupSearchView();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ConfigureRecyclerView();
        searchMovieApi("fast", 1);
        //findMovieByIdApi(200);
        ObserveAnyChange();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)) {
                    movieListViewModel.searchNextPage();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void setupSearchView() {
        final SearchView searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(query, 1);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void ObserveAnyChange() {
        movieListViewModel.getMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) {
                    Log.v("Movie: ", "Info: " + movie.toString());
                }
            }
        });

        movieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                if (movies != null) {
                    for (Movie movie : movies) {
                        Log.v("Tag", "onChanged: " + movie.getTitle());

                        movieRecyclerAdapter.setmMovies(movies);
                        movieRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void searchMovieApi(String query, int pageNumber) {
        movieListViewModel.searchMovieApi(query, pageNumber);
    }

    private void findMovieByIdApi(int id) {
        movieListViewModel.findMovieByIdApi(id);
    }

    private void ConfigureRecyclerView(){
        movieRecyclerAdapter = new MovieRecyclerView( this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieRecyclerAdapter);
    }

    @Override
    public void onMovieClick(int position) {

    }

    @Override
    public void onCategoryClick(String category) {

    }
}