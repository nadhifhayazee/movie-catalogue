package com.example.submission1.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public String title;
    public String genre;
    public String duration;
    public String director;
    public String rating;
    public int poster;
    public int thumbnail;
    public String sinopsis;

    public Movie(String title, String genre, String duration, String director, String rating, int poster, int thumbnail, String sinopsis) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.director = director;
        this.rating = rating;
        this.poster = poster;
        this.thumbnail = thumbnail;
        this.sinopsis = sinopsis;
    }

    protected Movie(Parcel in) {
        title = in.readString();
        genre = in.readString();
        duration = in.readString();
        director = in.readString();
        rating = in.readString();
        poster = in.readInt();
        thumbnail = in.readInt();
        sinopsis = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(genre);
        parcel.writeString(duration);
        parcel.writeString(director);
        parcel.writeString(rating);
        parcel.writeInt(poster);
        parcel.writeInt(thumbnail);
        parcel.writeString(sinopsis);
    }
}
