package com.example.movies.repositories;

import androidx.lifecycle.LiveData;

import com.example.movies.models.Movie;
import com.example.movies.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieApiClient movieApiClient;

    String query;
    int pageNumber;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;

    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieApiClient.getMovies();
    }

    public LiveData<Integer> getTotalPage() {
        return movieApiClient.getTotalPage();
    }

    public LiveData<Movie> getMovie() {
        return movieApiClient.getMovie();
    }

    public void serachMovieApi(String query, int pageNumber) {
        this.query = query;
        this.pageNumber = pageNumber;
        movieApiClient.searchMovieApi(query, pageNumber);
    }

    public void searchNextPage() {
        serachMovieApi(query, pageNumber + 1);
    }

    public void findMovieByIdApi(int id) {
        movieApiClient.findMovieById(id);
    }
}


