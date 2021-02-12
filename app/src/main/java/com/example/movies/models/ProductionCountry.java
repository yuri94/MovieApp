package com.example.movies.models;

/*
"production_countries": [
    {
      "iso_3166_1": "US",
      "name": "United States of America"
    }
  ],
 */

import android.os.Parcel;
import android.os.Parcelable;

class ProductionCountry implements Parcelable {

    private String iso31661;
    private String name;


    public String getIso31661() {
        return iso31661;
    }

    public String getName() {
        return name;
    }

    protected ProductionCountry(Parcel in) {
        iso31661 = in.readString();
        name = in.readString();
    }

    public static final Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(iso31661);
        parcel.writeString(name);
    }
}
