package org.equithon.equithondemo;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


    class MovieHolder extends RecyclerView.ViewHolder {

        private ImageView moviePoster;
        private TextView movieTitle;
        private TextView moviePlot;

        public MovieHolder(View itemView) {
            super(itemView);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            movieTitle = (TextView) itemView.findViewById(R.id.movieTitle);
            moviePlot = (TextView) itemView.findViewById(R.id.moviePlot);
        }

        public void bind(final Movie movie) {
            movieTitle.setText(movie.title + " (" + movie.year + ")");
            moviePlot.setText(movie.simplePlot);
            Picasso.with(itemView.getContext())
                    .load(movie.urlPosterPreview)
                    .into(moviePoster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                    intent.putExtra("movie", movie);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
