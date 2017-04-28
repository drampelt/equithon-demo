package org.equithon.equithondemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView moviePoster;
    private TextView movieTitle;
    private TextView movieYear;
    private TextView movieDuration;
    private TextView movieRating;
    private TextView moviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        moviePoster = (ImageView) findViewById(R.id.moviePoster);
        movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieYear = (TextView) findViewById(R.id.movieYear);
        movieDuration = (TextView) findViewById(R.id.movieDuration);
        movieRating = (TextView) findViewById(R.id.movieRating);
        moviePlot = (TextView) findViewById(R.id.moviePlot);

        final Movie movie = getIntent().getParcelableExtra("movie");
        movieTitle.setText(movie.title);
        movieYear.setText("Release Year: " + movie.year);
        movieDuration.setText("Duration: " + movie.runtime);
        movieRating.setText("Rating: " + movie.rating + "/10");
        moviePlot.setText(movie.plot);
        Picasso.with(this)
                .load(movie.urlPoster)
                .into(moviePoster);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(movie.urlIMDB));
                startActivity(intent);
            }
        });

    }

}
