package com.example.movies.repositories;

import androidx.lifecycle.LiveData;

import com.example.movies.models.MovieModel;
import com.example.movies.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }


    public void serachMovieApi(String query, int pageNumber) {
        movieApiClient.searchMovieApi(query, pageNumber);
    }
}


