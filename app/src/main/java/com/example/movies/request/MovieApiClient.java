package com.example.movies.request;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.movies.AppExecutors;
import com.example.movies.models.Movie;
import com.example.movies.models.SearchMovie;
import com.example.movies.untils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    private MutableLiveData<List<Movie>> mMovies;
    private MutableLiveData<Movie> mMovie;

    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMovie = new MutableLiveData<>();
    }


    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }

    public LiveData<Movie> getMovie() {
        return mMovie;
    }

    public void searchMovieApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);
        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {

                myHandler.cancel(true);

            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    public void findMovieById(int id) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(id);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);
    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;
        private int id;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        public RetrieveMoviesRunnable(int id) {
            this.id = id;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                Response response;

                if (id != 0) {
                    response = findMovieById(id).execute();
                    if (response.code() == 200) {
                        fetchMovieResponse(response);
                    } else {
                        String error = response.errorBody().string();
                        Log.v("Tag", "Error " + error);
                        mMovie.postValue(null);
                    }
                } else {
                    response = getMovies(query, pageNumber).execute();
                    if (response.code() == 200) {
                        fetchMovieSearchResponse(response);
                    } else {
                        String error = response.errorBody().string();
                        Log.v("Tag", "Error " + error);
                        mMovies.postValue(null);
                    }
                }

                if (cancelRequest) {
                    return;
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.v("Tag", "Error " + e.getMessage());
                mMovie = null;
                mMovies = null;
            }

            if (cancelRequest) {
                return;
            }
        }

        private void fetchMovieSearchResponse(Response response) {
            List<Movie> list = new ArrayList<>(((SearchMovie) response.body()).getResults());
            if (pageNumber == 1) {
                mMovies.postValue(list);
            } else {
                List<Movie> currentMovies = mMovies.getValue();
                currentMovies.addAll(list);
                mMovies.postValue(currentMovies);
            }
        }

        private void fetchMovieResponse(Response response) {
            Movie movie = ((Movie) response.body());
            mMovie.postValue(movie);
        }

        private Call<SearchMovie> getMovies(String query, int pageNumber) {
            return RetrofitService.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber
            );
        }

        private Call<Movie> findMovieById(int id) {
            return RetrofitService.getMovieApi().getMovie(id, Credentials.API_KEY);
        }

        private void CancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

}
