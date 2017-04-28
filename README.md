# Equithon Demo
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

## Copy/Paste Resources
### Libraries
```groovy
compile 'com.android.support:appcompat-v7:25.3.1'
compile 'com.android.support.constraint:constraint-layout:1.0.2'
compile 'com.android.support:recyclerview-v7:25.3.1'
compile 'com.android.support:design:25.3.1'
compile 'com.squareup.retrofit2:retrofit:2.2.0'
compile 'com.squareup.retrofit2:converter-gson:2.2.0'
compile 'com.squareup.picasso:picasso:2.5.2'
```

### JSON URL
https://gist.githubusercontent.com/drampelt/a83916c6d3c32d0732651d656ce0184d/raw/c5ca4d73034bfa9f2b2ccbf8accf6353f45d4661/movies.json

### Movie Model Parcelable
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

### MovieDetailActivity Views
```java
// Above onCreate
private ImageView moviePoster;
private TextView movieTitle;
private TextView movieYear;
private TextView movieDuration;
private TextView movieRating;
private TextView moviePlot;

// In onCreate
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
```

## Other Android Resources
- [Official Android Documentation](https://developer.android.com/guide/index.html)
- [Parcelable Generator Plugin](https://github.com/mcharmas/android-parcelable-intellij-plugin)
- Annoyed with repetitive `findViewById`s? [Butterknife](http://jakewharton.github.io/butterknife/) can help
- Not a fan of Java? [Kotlin](https://kotlinlang.org) works perfectly with Android and is much nicer to work with
