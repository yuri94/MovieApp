package com.example.movies.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/*
GET
/movie/{movie_id}
{
  "adult": false - boolean,
  "backdrop_path": "/fCayJrkfRaCRCTh8GqN30f8oyQF.jpg" - string/null,
  "belongs_to_collection": null - object/null,
  "budget": 63000000 - integer,
  "genres": [
    {
      "id": 18,
      "name": "Drama"
    }
  ] - array[genre],
  "homepage": "" - string/null,
  "id": 550 - integer,
  "imdb_id": "tt0137523" - string/null,
  "original_language": "en" - string,
  "original_title": "Fight Club" - string,
  "overview": "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy. Their concept catches on, with underground \"fight clubs\" forming in every town, until an eccentric gets in the way and ignites an out-of-control spiral toward oblivion." - string/null,
  "popularity": 0.5 - number,
  "poster_path": null - string/null,
  "production_companies": [
    {
      "id": 25,
      "logo_path": "/qZCc1lty5FzX30aOCVRBLzaVmcp.png",
      "name": "20th Century Fox",
      "origin_country": "US"
    }
  ] - array[ProductionCompany],
  "production_countries": [
    {
      "iso_3166_1": "US",
      "name": "United States of America"
    }
  ] - array[ProductionCountry],
  "release_date": "1999-10-12" - string,
  "revenue": 100853753 - integer,
  "runtime": 139 - integer,
  "spoken_languages": [
    {
      "iso_639_1": "en",
      "name": "English"
    }
  ] - array[SpokenLanguages],
  "status": "Released" - string,
  "tagline": "How much can you know about yourself if you've never been in a fight?" - string/null,
  "title": "Fight Club" - string,
  "video": false - boolean,
  "vote_average": 7.8 - number,
  "vote_count": 3439 - integer
}
 */

public class Movie implements Parcelable {
// Model Class for our movies

    private int id;
    private boolean adult;

    @SerializedName("backdrop_path")
    private String backdropPath;

    private int budget;

    private List<Genre> genres;

    @SerializedName("homepage")
    private String homePage;

    @SerializedName("imdb_id")
    private String imdbId;

    @SerializedName("original_language")
    private String originalLanguage;

    @SerializedName("original_title")
    private String originalTitle;

    private String overview;
    private double popularity;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("production_companies")
    private List<ProductionCompany> productionCompanies;

    @SerializedName("production_countries")
    private List<ProductionCountry> productionCountries;

    @SerializedName("release_date")
    private String releaseDate;

    private int revenue;
    private int runtime;

    @SerializedName("spoken_languages")
    private List<SpokenLanguage> spokenLanguages;

    private String status;
    private String tagline;
    private String title;
    private boolean video;

    @SerializedName("vote_average")
    private double voteAverege;

    @SerializedName("vote_count")
    private int voteCount;

    protected Movie(Parcel in) {
        id = in.readInt();
        adult = in.readByte() != 0;
        backdropPath = in.readString();
        budget = in.readInt();
        genres = in.createTypedArrayList(Genre.CREATOR);
        homePage = in.readString();
        imdbId = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        overview = in.readString();
        popularity = in.readDouble();
        posterPath = in.readString();
        productionCompanies = in.createTypedArrayList(ProductionCompany.CREATOR);
        productionCountries = in.createTypedArrayList(ProductionCountry.CREATOR);
        releaseDate = in.readString();
        revenue = in.readInt();
        runtime = in.readInt();
        spokenLanguages = in.createTypedArrayList(SpokenLanguage.CREATOR);
        status = in.readString();
        tagline = in.readString();
        title = in.readString();
        video = in.readByte() != 0;
        voteAverege = in.readDouble();
        voteCount = in.readInt();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public int getBudget() {
        return budget;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public String getTagline() {
        return tagline;
    }

    public String getTitle() {
        return title;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVoteAverege() {
        return voteAverege;
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(backdropPath);
        parcel.writeInt(budget);
        parcel.writeTypedList(genres);
        parcel.writeString(homePage);
        parcel.writeString(imdbId);
        parcel.writeString(originalLanguage);
        parcel.writeString(originalTitle);
        parcel.writeString(overview);
        parcel.writeDouble(popularity);
        parcel.writeString(posterPath);
        parcel.writeTypedList(productionCompanies);
        parcel.writeTypedList(productionCountries);
        parcel.writeString(releaseDate);
        parcel.writeInt(revenue);
        parcel.writeInt(runtime);
        parcel.writeTypedList(spokenLanguages);
        parcel.writeString(status);
        parcel.writeString(tagline);
        parcel.writeString(title);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(voteAverege);
        parcel.writeInt(voteCount);
    }
}
