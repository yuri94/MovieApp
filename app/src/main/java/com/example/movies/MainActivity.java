package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.movies.models.MovieModel;
import com.example.movies.request.Survicey;
import com.example.movies.response.MovieSearchResponse;
import com.example.movies.untils.Credentials;
import com.example.movies.untils.MovieApi;
import com.example.movies.viewmodels.MovieListViewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn;

    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button2);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetRetrofitResponceAccordingToID();
            }
        });
    }





    private  void  GetRetrofitResponse() {
        MovieApi movieApi = Survicey.getMovieApi();
        Call<MovieSearchResponse> responseCall;
        responseCall = movieApi
                .searchMovie(
                        Credentials.API_KEY,
                        "Jack Reacher",
                        1);
        responseCall.enqueue(new Callback<MovieSearchResponse>() {
            @Override
            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
                if(response.code() == 200){

                    //Log.v("Tag" , "the response" + response.body().toString());

                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                    for(MovieModel movie: movies){
                        Log.v("Tag" , "Name"+ movie.getTitle());
                    }

                }
                else
                {
                    try {
                        Log.v("Tag" , "Error" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {

            }
        });
    }


    private void   GetRetrofitResponceAccordingToID(){

        MovieApi movieApi = Survicey.getMovieApi();
        Call<MovieModel> responceCall = movieApi
                .getMovie(
                        343611,
                        Credentials.API_KEY);

        responceCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {

                if(response.code() == 200) {
                    MovieModel movie = response.body();
                    Log.v("Tag" , "The Response" + movie.getTitle());
                }
                else {
                    try {
                        Log.v("Tag", "Error" + response.errorBody().string());

                    } catch (IOException e) {
                            e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });

    }

}