package com.example.movies.viewmodels;

import android.graphics.Movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.movies.models.MovieModel;

import java.util.List;

public class MovieListViewModel extends ViewModel {
    private MutableLiveData<List<MovieModel>> mMovies = new MutableLiveData<>();


    public MovieListViewModel() {


    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }
}


