# Equithon Demo
[Download Completed Project](https://github.com/drampelt/equithon-demo/archive/master.zip)

## Steps
1. [Setup Project](https://github.com/drampelt/equithon-demo/commit/0bbfa0e54bda4e57f677acee01e17110f5562135)
2. [Add Libraries](https://github.com/drampelt/equithon-demo/commit/e3168b13b0861376b2c73adc7977a5f4372d96c9)
3. [Create Movie model](https://github.com/drampelt/equithon-demo/commit/f7e4e012453c66102d2754fdb4b78d6ec4887d87)
4. [Create Movie list item](https://github.com/drampelt/equithon-demo/commit/a01c26bd09e913fe79ffe7af6ceadd43446a385d)
5. [Create MovieAdapter](https://github.com/drampelt/equithon-demo/commit/930c458057e9d95a39c191240c59045e4854ce57)
6. [Setup MovieService and Retrofit](https://github.com/drampelt/equithon-demo/commit/4dab31a706ffe8fa05e4012b6713c9c2e81ac2a0)
7. [Show Movies in RecyclerView](https://github.com/drampelt/equithon-demo/commit/ffc0de48e6d21423f65dfeb34043b9e12384d61a)
8. [Create MovieDetailActivity](https://github.com/drampelt/equithon-demo/commit/61909faeb8f7346402cf5f0a55d753b6f06b843e)
9. [Open MovieDetailActivity and show details when movie is tapped](https://github.com/drampelt/equithon-demo/commit/4e8954fa44eec9b75a1c467a81099ede440e881d)
10. [Open IMDB page when globe is tapped](https://github.com/drampelt/equithon-demo/commit/7114a6bcc6f27cdea5732ded1418fed0e7439cbb)

## Quick File Reference
- [MainActivity.java](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/java/org/equithon/equithondemo/MainActivity.java)
- [Movie.java](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/java/org/equithon/equithondemo/Movie.java)
- [MovieAdapter.java](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/java/org/equithon/equithondemo/MovieAdapter.java)
- [MovieDetailActivity.java](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/java/org/equithon/equithondemo/MovieDetailActivity.java)
- [MovieService.java](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/java/org/equithon/equithondemo/MovieService.java)
- [activity_main.xml](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/res/layout/activity_main.xml)
- [activity_movie_detail.xml](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/res/layout/activity_movie_detail.xml)
- [content_movie_detail.xml](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/res/layout/content_movie_detail.xml)
- [list_item_movie.xml](https://github.com/drampelt/equithon-demo/blob/master/app/src/main/res/layout/list_item_movie.xml)

## Step by Step
### Step 1 - Setup Project ([View Changes](https://github.com/drampelt/equithon-demo/commit/0bbfa0e54bda4e57f677acee01e17110f5562135))
- Create a new project
- Minimum SDK 19
- Empty Activity (Backwards Compatible)

### Step 2 - Add Libraries ([View Changes](https://github.com/drampelt/equithon-demo/commit/e3168b13b0861376b2c73adc7977a5f4372d96c9))
- Add libraries to `app/build.gradle`:

```groovy
compile 'com.android.support:appcompat-v7:25.3.1'
compile 'com.android.support.constraint:constraint-layout:1.0.2'
compile 'com.android.support:recyclerview-v7:25.3.1'
compile 'com.android.support:design:25.3.1'
compile 'com.squareup.retrofit2:retrofit:2.2.0'
compile 'com.squareup.retrofit2:converter-gson:2.2.0'
compile 'com.squareup.picasso:picasso:2.5.2'
```

### Step 3 - Create Movie Model ([View Changes](https://github.com/drampelt/equithon-demo/commit/f7e4e012453c66102d2754fdb4b78d6ec4887d87))
- Create `Movie.java`:

```java
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
```

### Step 4 - Create Movie List Item ([View Changes](https://github.com/drampelt/equithon-demo/commit/a01c26bd09e913fe79ffe7af6ceadd43446a385d))
- Create `list_item_movie.xml` in `res/layouts` folder:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/moviePoster"
        android:layout_width="64dp"
        android:layout_height="64dp"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginTop="8dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@mipmap/ic_launcher" />

    <TextView
        android:id="@+id/movieTitle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="8dp"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="Movie Title (Year)" />

    <TextView
        android:id="@+id/moviePlot"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginBottom="8dp"
        android:layout_marginLeft="8dp"
        android:layout_marginRight="8dp"
        android:layout_marginTop="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/movieTitle"
        app:layout_constraintVertical_bias="0.0"
        tools:text="Movie Plot" />
</android.support.constraint.ConstraintLayout>
```

- Add a `RecyclerView` to `activity_main.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="org.equithon.equithondemo.MainActivity">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="0dp"
        android:layout_marginLeft="0dp"
        android:layout_marginRight="0dp"
        android:layout_marginTop="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:listitem="@layout/list_item_movie" />
</android.support.constraint.ConstraintLayout>
```

### Step 5 - Create MovieAdapter ([View Changes](https://github.com/drampelt/equithon-demo/commit/930c458057e9d95a39c191240c59045e4854ce57))
- Create `MovieAdapter.java`:

```java
package org.equithon.equithondemo;

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

        public void bind(Movie movie) {
            movieTitle.setText(movie.title + " (" + movie.year + ")");
            moviePlot.setText(movie.simplePlot);
            Picasso.with(itemView.getContext())
                    .load(movie.urlPosterPreview)
                    .into(moviePoster);
        }
    }
}
```

### Step 6 - Setup MovieService and Retrofit ([View Changes](https://github.com/drampelt/equithon-demo/commit/4dab31a706ffe8fa05e4012b6713c9c2e81ac2a0))
- Create `MovieService.java`:

```java
package org.equithon.equithondemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieService {
    @GET("https://gist.githubusercontent.com/drampelt/a83916c6d3c32d0732651d656ce0184d/raw/c5ca4d73034bfa9f2b2ccbf8accf6353f45d4661/movies.json")
    Call<List<Movie>> getPopularMovies();
}
```

- Create `Retrofit` and `MovieService` in `MainActivity.java#onCreate` ([View Changes](https://github.com/drampelt/equithon-demo/commit/4dab31a706ffe8fa05e4012b6713c9c2e81ac2a0#diff-b83e5e9a6a9f6d7a719a5b30a98c2c82), [View Full `MainActivity.java`](https://github.com/drampelt/equithon-demo/blob/4dab31a706ffe8fa05e4012b6713c9c2e81ac2a0/app/src/main/java/org/equithon/equithondemo/MainActivity.java))

```java
Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

MovieService movieService = retrofit.create(MovieService.class);

movieService.getPopularMovies().enqueue(new Callback<List<Movie>>() {
    @Override
    public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
        List<Movie> movies = response.body();
    }

    @Override
    public void onFailure(Call<List<Movie>> call, Throwable t) {
        Toast.makeText(MainActivity.this, "Could not get movies!", Toast.LENGTH_SHORT).show();
    }
});
```

### Step 7 - Show Movies in RecyclerView ([View Changes](https://github.com/drampelt/equithon-demo/commit/ffc0de48e6d21423f65dfeb34043b9e12384d61a))
- Add internet permission to `AndroidManifest.xml`: ([View Changes](https://github.com/drampelt/equithon-demo/commit/ffc0de48e6d21423f65dfeb34043b9e12384d61a#diff-c8cc2dd42271f2bf39c6aa81eb6a5529), [View Full `AndroidManifest.xml`](https://github.com/drampelt/equithon-demo/blob/ffc0de48e6d21423f65dfeb34043b9e12384d61a/app/src/main/AndroidManifest.xml))

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

- Add `RecylerView` to `MainActivity.java` and display items: ([View Changes](https://github.com/drampelt/equithon-demo/commit/ffc0de48e6d21423f65dfeb34043b9e12384d61a#diff-b83e5e9a6a9f6d7a719a5b30a98c2c82), [View Full `MainAcitivy.java`](https://github.com/drampelt/equithon-demo/blob/ffc0de48e6d21423f65dfeb34043b9e12384d61a/app/src/main/java/org/equithon/equithondemo/MainActivity.java))

```java
// Above onCreate
private RecyclerView recyclerView;

// Below setContentView
recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
recyclerView.setLayoutManager(new LinearLayoutManager(this));

// In onResponse
MovieAdapter movieAdapter = new MovieAdapter(movies);
recyclerView.setAdapter(movieAdapter);
```

### Step 8 - Create `MovieDetailActivity` ([View Changes](https://github.com/drampelt/equithon-demo/commit/61909faeb8f7346402cf5f0a55d753b6f06b843e))
- Create `MovieDetailActivity` with Right Click -> New -> Activity -> BasicActivity ([View Changes](https://github.com/drampelt/equithon-demo/commit/61909faeb8f7346402cf5f0a55d753b6f06b843e))
- Design `content_movie_detail.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context="org.equithon.equithondemo.MovieDetailActivity"
    tools:showIn="@layout/activity_movie_detail">

    <ImageView
        android:id="@+id/moviePoster"
        android:layout_width="128dp"
        android:layout_height="128dp"
        android:layout_marginLeft="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@mipmap/ic_launcher" />

    <TextView
        android:id="@+id/movieTitle"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="16dp"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="Movie Title" />

    <TextView
        android:id="@+id/movieYear"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="8dp"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/movieTitle"
        tools:text="Release Year: 2017"
        app:layout_constraintHorizontal_bias="0.0" />

    <TextView
        android:id="@+id/movieDuration"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="8dp"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/movieYear"
        tools:text="Duration: 120m"
        app:layout_constraintHorizontal_bias="0.0" />

    <TextView
        android:id="@+id/movieRating"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="8dp"
        app:layout_constraintLeft_toRightOf="@+id/moviePoster"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/movieDuration"
        tools:text="Rating: 8/10" />

    <TextView
        android:id="@+id/moviePlot"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginBottom="16dp"
        android:layout_marginLeft="16dp"
        android:layout_marginRight="16dp"
        android:layout_marginTop="16dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/moviePoster"
        tools:text="Movie Plot" />
</android.support.constraint.ConstraintLayout>
```

### Step 9 - Open MovieDetailActivity when movie is tapped ([View Changes](https://github.com/drampelt/equithon-demo/commit/4e8954fa44eec9b75a1c467a81099ede440e881d))
- Set an `OnClickListener` for the list item in `MovieAdapter.java` ([View Changes](https://github.com/drampelt/equithon-demo/commit/4e8954fa44eec9b75a1c467a81099ede440e881d#diff-8ef43159de49a201153168350d463829), [View Full `MovieAdapter.java`](https://github.com/drampelt/equithon-demo/blob/4e8954fa44eec9b75a1c467a81099ede440e881d/app/src/main/java/org/equithon/equithondemo/MovieAdapter.java))

```java
// Below Picasso.with()...
itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        itemView.getContext().startActivity(intent);
    }
});
```

- Add views to `MovieDetailActivity.java` and display movie details: ([View Changes](https://github.com/drampelt/equithon-demo/commit/4e8954fa44eec9b75a1c467a81099ede440e881d#diff-888e9c277527481f52abe132c9d107a4), [View Full `MovieDetailActivity.java`](https://github.com/drampelt/equithon-demo/blob/4e8954fa44eec9b75a1c467a81099ede440e881d/app/src/main/java/org/equithon/equithondemo/MovieDetailActivity.java))

```java
// Before onCreate
private ImageView moviePoster;
private TextView movieTitle;
private TextView movieYear;
private TextView movieDuration;
private TextView movieRating;
private TextView moviePlot;

// Below setSupportActionBar in onCreate
moviePoster = (ImageView) findViewById(R.id.moviePoster);
movieTitle = (TextView) findViewById(R.id.movieTitle);
movieYear = (TextView) findViewById(R.id.movieYear);
movieDuration = (TextView) findViewById(R.id.movieDuration);
movieRating = (TextView) findViewById(R.id.movieRating);
moviePlot = (TextView) findViewById(R.id.moviePlot);

Movie movie = getIntent().getParcelableExtra("movie");
movieTitle.setText(movie.title);
movieYear.setText("Release Year: " + movie.year);
movieDuration.setText("Duration: " + movie.runtime);
movieRating.setText("Rating: " + movie.rating + "/10");
moviePlot.setText(movie.plot);
Picasso.with(this)
        .load(movie.urlPoster)
        .into(moviePoster);
```

### Step 10 - Open IMDB page when globe icon is tapped ([View Changes](https://github.com/drampelt/equithon-demo/commit/7114a6bcc6f27cdea5732ded1418fed0e7439cbb))
- Add globe icon from Right Click -> New -> Vector Asset, search for 'public', name it `ic_globe.xml`
- Edit icon, change color to `#FFFFFFFF`

```xml
<vector android:height="32dp" android:viewportHeight="24.0"
    android:viewportWidth="24.0" android:width="32dp" xmlns:android="http://schemas.android.com/apk/res/android">
    <path android:fillColor="#FFFFFFFF" android:pathData="M12,2C6.48,2 2,6.48 2,12s4.48,10 10,10 10,-4.48 10,-10S17.52,2 12,2zM11,19.93c-3.95,-0.49 -7,-3.85 -7,-7.93 0,-0.62 0.08,-1.21 0.21,-1.79L9,15v1c0,1.1 0.9,2 2,2v1.93zM17.9,17.39c-0.26,-0.81 -1,-1.39 -1.9,-1.39h-1v-3c0,-0.55 -0.45,-1 -1,-1L8,12v-2h2c0.55,0 1,-0.45 1,-1L11,7h2c1.1,0 2,-0.9 2,-2v-0.41c2.93,1.19 5,4.06 5,7.41 0,2.08 -0.8,3.97 -2.1,5.39z"/>
</vector>
```

- Set `FloatingActionButton` icon to globe icon in `activity_movie_detail.xml`: ([View Changes](https://github.com/drampelt/equithon-demo/commit/7114a6bcc6f27cdea5732ded1418fed0e7439cbb#diff-af2c4d88952e9bd562e3580e42925e3e), [View Full `activity_movie_detail.xml`](https://github.com/drampelt/equithon-demo/blob/7114a6bcc6f27cdea5732ded1418fed0e7439cbb/app/src/main/res/layout/activity_movie_detail.xml))

```xml
<android.support.design.widget.FloatingActionButton
    android:id="@+id/fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_gravity="bottom|end"
    android:layout_margin="@dimen/fab_margin"
    app:srcCompat="@drawable/ic_globe" />
```

- Tell Android Studio to use support library for vector images: ([View Changes](https://github.com/drampelt/equithon-demo/commit/7114a6bcc6f27cdea5732ded1418fed0e7439cbb#diff-39e7d8c00954e920b98e7636f0ac30b2), [View Full `app/build.gradle`](https://github.com/drampelt/equithon-demo/blob/7114a6bcc6f27cdea5732ded1418fed0e7439cbb/app/build.gradle))

```groovy
// Below testInstrumentationRunner "..."
vectorDrawables.useSupportLibrary = true
```

- Open IMDB page when `FloatingActionButton` is tapped: ([View Changes](https://github.com/drampelt/equithon-demo/commit/7114a6bcc6f27cdea5732ded1418fed0e7439cbb#diff-888e9c277527481f52abe132c9d107a4), [View Full `MovieDetailActivity.java`](https://github.com/drampelt/equithon-demo/blob/7114a6bcc6f27cdea5732ded1418fed0e7439cbb/app/src/main/java/org/equithon/equithondemo/MovieDetailActivity.java))

```java
// Replace onClick content with this
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse(movie.urlIMDB));
startActivity(intent);
```

## Other Android Resources
- [Official Android Documentation](https://developer.android.com/guide/index.html)
- [Parcelable Generator Plugin](https://github.com/mcharmas/android-parcelable-intellij-plugin)
- Annoyed with repetitive `findViewById`s? [Butterknife](http://jakewharton.github.io/butterknife/) can help
- Not a fan of Java? [Kotlin](https://kotlinlang.org) works perfectly with Android and is much nicer to work with
