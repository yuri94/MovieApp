package com.example.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
// Model Class for our movies
    private String title;
    private String poster_path;
    private String release_date;
    private int movie_id;
    private float vote_averege;
    private String movie_overview;


    //Constructor


    public MovieModel(String title, String poster_path, String release_date, int movie_id, float vote_averege, String movie_overview) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.movie_id = movie_id;
        this.vote_averege = vote_averege;
        this.movie_overview = movie_overview;
    }


    //Getters

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        release_date = in.readString();
        movie_id = in.readInt();
        vote_averege = in.readFloat();
        movie_overview = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public float getVote_averege() {
        return vote_averege;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    @Override
    public int describeContents() {
            return  0;
    }





    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(poster_path);
        dest.writeString(release_date);
        dest.writeInt(movie_id);
        dest.writeFloat(vote_averege);
        dest.writeString(movie_overview);
    }
}
