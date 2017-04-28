package org.equithon.equithondemo;

public class Movie {
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
}
