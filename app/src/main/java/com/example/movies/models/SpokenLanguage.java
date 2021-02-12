package com.example.movies.models;

/*
"spoken_languages": [
    {
      "iso_639_1": "en",
      "name": "English"
    }
  ],
 */

import android.os.Parcel;
import android.os.Parcelable;

class SpokenLanguage implements Parcelable {

    private String iso6391;
    private String name;

    public String getIso6391() {
        return iso6391;
    }

    public String getName() {
        return name;
    }

    protected SpokenLanguage(Parcel in) {
        iso6391 = in.readString();
        name = in.readString();
    }

    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
        @Override
        public SpokenLanguage createFromParcel(Parcel in) {
            return new SpokenLanguage(in);
        }

        @Override
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iso6391);
        parcel.writeString(name);
    }
}
