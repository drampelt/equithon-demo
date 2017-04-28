package org.equithon.equithondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("https://gist.githubusercontent.com/drampelt/a83916c6d3c32d0732651d656ce0184d/raw/c5ca4d73034bfa9f2b2ccbf8accf6353f45d4661/movies.json")
    Call<List<Movie>> getPopularMovies();
}
