package com.example.movies.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.models.Movie;
import com.example.movies.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies(){
        return movieRepository.getMovies();
    }

    public LiveData<Movie> getMovie() {
        return movieRepository.getMovie();
    }

    public void searchMovieApi(String query, int pageNumber){
        movieRepository.serachMovieApi(query, pageNumber);
    }

    public void findMovieByIdApi(int id) {
        movieRepository.findMovieByIdApi(id);
    }
}


