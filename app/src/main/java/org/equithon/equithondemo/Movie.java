package org.equithon.equithondemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    public String title;
    public String year;
    public String plot;
    public String simplePlot; // Don't show the whole plot in the list
    public String rating;
    public String runtime;
    public String urlIMDB; // Link to IMDB page
    public String urlPoster;
    public String urlPosterPreview; // Smaller icon for the list

    public Movie(String title, String year, String plot, String simplePlot, String rating, String runtime, String urlIMDB, String urlPoster, String urlPosterPreview) {
        this.title = title;
        this.year = year;
        this.plot = plot;
        this.simplePlot = simplePlot;
        this.rating = rating;
        this.runtime = runtime;
        this.urlIMDB = urlIMDB;
        this.urlPoster = urlPoster;
        this.urlPosterPreview = urlPosterPreview;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.plot);
        dest.writeString(this.simplePlot);
        dest.writeString(this.rating);
        dest.writeString(this.runtime);
        dest.writeString(this.urlIMDB);
        dest.writeString(this.urlPoster);
        dest.writeString(this.urlPosterPreview);
    }

    protected Movie(Parcel in) {
        this.title = in.readString();
        this.year = in.readString();
        this.plot = in.readString();
        this.simplePlot = in.readString();
        this.rating = in.readString();
        this.runtime = in.readString();
        this.urlIMDB = in.readString();
        this.urlPoster = in.readString();
        this.urlPosterPreview = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
