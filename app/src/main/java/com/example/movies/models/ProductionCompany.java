package com.example.movies.models;

/*
GET
/company/{company_id}
{
  "description": "" - string,
  "headquarters": "San Francisco, California, United States" - string,
  "homepage": "http://www.lucasfilm.com" - string,
  "id": 1 - integer,
  "logo_path": "/o86DbpburjxrqAzEDhXZcyE8pDb.png" - string,
  "name": "Lucasfilm" - string,
  "origin_country": "US" - string,
  "parent_company": null/object
}
 */

import android.os.Parcel;
import android.os.Parcelable;

class ProductionCompany implements Parcelable {

    private int id;
    private String description;
    private String headquarters;
    private String homePage;
    private String logoPath;
    private String name;
    private String originCountry;
    private ProductionCompany parentCompany;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getHeadquarters() {
        return headquarters;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getName() {
        return name;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public ProductionCompany getParentCompany() {
        return parentCompany;
    }

    protected ProductionCompany(Parcel in) {
        id = in.readInt();
        description = in.readString();
        headquarters = in.readString();
        homePage = in.readString();
        logoPath = in.readString();
        name = in.readString();
        originCountry = in.readString();
        parentCompany = in.readParcelable(ProductionCompany.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(description);
        dest.writeString(headquarters);
        dest.writeString(homePage);
        dest.writeString(logoPath);
        dest.writeString(name);
        dest.writeString(originCountry);
        dest.writeParcelable(parentCompany, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProductionCompany> CREATOR = new Creator<ProductionCompany>() {
        @Override
        public ProductionCompany createFromParcel(Parcel in) {
            return new ProductionCompany(in);
        }

        @Override
        public ProductionCompany[] newArray(int size) {
            return new ProductionCompany[size];
        }
    };
}
