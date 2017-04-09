package timefeel.com.helpers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import timefeel.com.model.Movie;


/**
 * Created by test on 26/02/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABSE_NAME = "Movies.db";
    private static final String TABLE_MOVIES = "movies";

    public final class MovieContract implements BaseColumns {
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
        public static final String COLUMN_NAME_ADULT = "adult";
        public static final String COLUMN_NAME_OVERVIEW = "overview";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        public static final String COLUMN_NAME_GENRE_IDS = "genre_ids";
        public static final String COLUMN_NAME_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_NAME_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BACKDROP_PATH = "basckdrop_path";
        public static final String COLUMN_NAME_POPULARITY = "backdrop_popularity";
        public static final String COLUMN_NAME_VOTE_COUNT = "vote_count";
        public static final String COLUMN_NAME_VIDEO = "video";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
    }

    private static final String CREATE_DATABASE = "CREATE TABLE " + TABLE_MOVIES + "("
            + MovieContract.COLUMN_NAME_ID + " INTEGER PRIMARY KEY," + MovieContract.COLUMN_NAME_POSTER_PATH + " TEXT," + MovieContract.COLUMN_NAME_ADULT + " TEXT," + MovieContract.COLUMN_NAME_OVERVIEW + " TEXT,"
            + MovieContract.COLUMN_NAME_RELEASE_DATE + " TEXT," + MovieContract.COLUMN_NAME_GENRE_IDS + " TEXT," + MovieContract.COLUMN_NAME_ORIGINAL_TITLE + " TEXT," + MovieContract.COLUMN_NAME_ORIGINAL_LANGUAGE + " TEXT,"
            + MovieContract.COLUMN_NAME_TITLE + " TEXT," + MovieContract.COLUMN_NAME_BACKDROP_PATH + " TEXT," + MovieContract.COLUMN_NAME_POPULARITY + " TEXT," + MovieContract.COLUMN_NAME_VOTE_COUNT + " TEXT," + MovieContract.COLUMN_NAME_VIDEO + " TEXT,"
            + MovieContract.COLUMN_NAME_VOTE_AVERAGE + " TEXT" + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DATABASE);
        // Creating Tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Upgrading database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_MOVIES);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onDowngrade(db, oldVersion, newVersion);
    }

    // Getting single movie
    public Movie getMovie(String id) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT" + MovieContract.COLUMN_NAME_TITLE + "FROM" + TABLE_MOVIES + " WHERE id > ?", new String[]{id});
        List itemIds = new ArrayList<>();
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MovieContract._ID));
            itemIds.add(itemId);
        }
        cursor.close();
        return (Movie) itemIds;
    }

    // Getting All movies
    public List<Movie> getAllMovies() {
        List<Movie> moviesList = new ArrayList<Movie>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        // return contact list
        return moviesList;
    }

    // Getting movies Count
    public int getMoviesCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MOVIES;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        // return count
        return count;
    }

}
