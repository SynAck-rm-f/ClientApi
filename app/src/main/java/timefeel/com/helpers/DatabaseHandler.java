package timefeel.com.helpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import timefeel.com.model.Movie;

/**
 * Created by test on 26/02/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABSE_NAME = "MoviesDB";
    private static final String TABLE_MOVIES = "movies";


    public DatabaseHandler(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creating Tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Upgrading database
    }

    // Adding new movie
    public void addMovie(Movie movies) {}

    // Getting single movie
    public Movie getMovie(int id) {
        return null;
    }
    // Getting All movies
    public List<Movie> getAllMovies() {
        return null;
    }
    // Getting movies Count
    public int getMoviesCount() {
        return Integer.parseInt(null);
    }
    // Updating single movies
    public int updateMovie(Movie movie) {
        return Integer.parseInt(null);
    }
    // Deleting single movies
    public void deleteContact(Movie movie) {
    }
}
