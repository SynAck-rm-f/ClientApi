package com.timefeel.rxmovies.ui.movies;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.timefeel.rxmovies.app.CustomApplication;

import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.timefeel.rxmovies.BuildConfig;
import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.activity.MainActivity;
import com.timefeel.rxmovies.ui.movies.core.MoviesAdapter;
import com.timefeel.rxmovies.ui.movies.core.DatabaseHandler;
import com.timefeel.rxmovies.models.Configuration;
import com.timefeel.rxmovies.models.ImagesSize;
import com.timefeel.rxmovies.models.Movie;
import com.timefeel.rxmovies.models.MoviesResponse;
import com.timefeel.rxmovies.app.builder.ServiceHelper;
import com.timefeel.rxmovies.utils.UtilsTF;

/**
 * Created by test on 05/03/2017.
 */

public class MoviesFragment extends Fragment {

    //***********************************************************************************************
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APIKEY = BuildConfig.API_KEY;

    // https://en.wikipedia.org/wiki/ISO_639-1
    private static final String COUNTRY_ISO = "th";

    //***********************************************************************************************
    private ImagesSize mimagesize;
    private String[] mlocales = Locale.getISOCountries();
    private String mcountry;
    private Random mrndcountry = new Random();

    @BindView(R.id.movies) RecyclerView recyclerview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);
        ButterKnife.bind(this,rootView);


        ServiceHelper.GetInstance().getConfiguration(APIKEY).
                enqueue(new Callback<Configuration>() {
                    @Override
                    public void onResponse(Call<Configuration> call, Response<Configuration> response) {
                        mimagesize = response.body().getImagesSize();
                        System.out.printf("----- onresponse");
                    }

                    @Override
                    public void onFailure(Call<Configuration> call, Throwable t) {
                        Log.d(TAG, t.getMessage());
                    }
                });

        //***********************************************************************************************
        if (APIKEY.isEmpty()) {
            Toast.makeText(CustomApplication.getAppContext(), "APIKEY is not defined", Toast.LENGTH_SHORT).show();
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

            /** url iso 639-1 = country code for query param*/
            //mcountry = mlocales[mrndcountry.nextInt(mlocales.length)].toLowerCase();
            mcountry = getActivity().getIntent().getStringExtra("alpha2");
            ServiceHelper.GetInstance().getOriginalLanguage(APIKEY, mcountry)
                    .enqueue(new Callback<MoviesResponse>() {
                        @Override
                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                            int StatusCode = response.code();
                            List<Movie> movies = response.body().getResults();
                            recyclerview.setAdapter(new MoviesAdapter(movies, mimagesize, R.layout.list_item_movie, CustomApplication.getAppContext()));
                        }

                        @Override
                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });
         /*else {
            Log.d(TAG, "--------- Offline ----------");
            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
            int count = db.getMoviesCount();
            Log.d(TAG, "Row count = " + count);

        }*/

        return rootView;
    }
}

