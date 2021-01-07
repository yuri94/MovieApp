package com.example.movies.viewmodels;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.models.MovieModel;
import com.example.movies.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public void searchMovieApi(String query, int pageNumber){
        movieRepository.serachMovieApi(query, pageNumber);
    }
}


