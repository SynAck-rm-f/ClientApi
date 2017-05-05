package com.timefeel.rxmovies.ui.movies;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.timefeel.rxmovies.injection.app.ApplicationModule;
import com.timefeel.rxmovies.injection.app.DaggerApplicationComponent;
import com.timefeel.rxmovies.injection.app.builder.MoviesApiServiceModule;
import com.timefeel.rxmovies.injection.app.main.MainComponent;
import com.timefeel.rxmovies.models.Configuration;
import com.timefeel.rxmovies.models.ImagesSize;
import com.timefeel.rxmovies.models.Movie;
import com.timefeel.rxmovies.models.MoviesResponse;

/**
 * Created by test on 05/03/2017.
 */

public class MoviesFragment extends Fragment {

    private static final String APIKEY = BuildConfig.API_KEY;
    private static final String TAG = MoviesFragment.class.getSimpleName();

    // https://en.wikipedia.org/wiki/ISO_639-1
    private static final String COUNTRY_ISO = "th";

    private ImagesSize mimagesize;
    private String[] mlocales = Locale.getISOCountries();
    private String mcountry;
    private Random mrndcountry = new Random();
    private Application applicationModule;

    @BindView(R.id.movies) RecyclerView recyclerview;

    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);
        ButterKnife.bind(this,rootView);

        CustomApplication.getAppComponent(getContext()).ApiService().getConfiguration(APIKEY).enqueue(new Callback<Configuration>() {
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


        if (APIKEY.isEmpty()) {
            Toast.makeText(getActivity().getBaseContext(), "APIKEY is not defined", Toast.LENGTH_SHORT).show();
        }
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


            // url iso 639-1 = country code for query param
            //mcountry = mlocales[mrndcountry.nextInt(mlocales.length)].toLowerCase();
            mcountry = getActivity().getIntent().getStringExtra("alpha2");
            CustomApplication.getAppComponent(getContext()).ApiService()
                .getOriginalLanguage(APIKEY, mcountry)
                    .enqueue(new Callback<MoviesResponse>() {
                        @Override
                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                            int StatusCode = response.code();
                            List<Movie> movies = response.body().getResults();
                            recyclerview.setAdapter(new MoviesAdapter(movies, mimagesize, R.layout.list_item_movie, CustomApplication.getAppComponent(getContext()).getAppContext()));
                        }

                        @Override
                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });

        return rootView;
    }
}

