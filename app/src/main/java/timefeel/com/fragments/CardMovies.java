package timefeel.com.fragments;

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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timefeel.com.BuildConfig;
import timefeel.com.CustomApplication;
import timefeel.com.R;
import timefeel.com.activity.MainActivity;
import timefeel.com.adapter.MoviesAdapter;
import timefeel.com.helpers.DatabaseHandler;
import timefeel.com.model.Configuration;
import timefeel.com.model.ImagesSize;
import timefeel.com.model.Movie;
import timefeel.com.model.MoviesResponse;
import timefeel.com.rest.ServiceHelper;
import timefeel.com.utils.UtilsTF;

/**
 * Created by test on 05/03/2017.
 */

public class CardMovies extends Fragment {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APIKEY = BuildConfig.API_KEY;

    private ImagesSize mimagesize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recycler_view_frag, container, false);
        rootView.setTag(TAG);


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

        if (APIKEY.isEmpty()) {
            Toast.makeText(CustomApplication.getAppContext(), "APIKEY is not defined", Toast.LENGTH_SHORT).show();
        }
        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.movies);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        if (UtilsTF.isNetwrkAvailable() | UtilsTF.isOnline()) {
            ServiceHelper.GetInstance().getTopRatedMovies(APIKEY)
                    .enqueue(new Callback<MoviesResponse>() {
                        @Override
                        public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                            int StatusCode = response.code();
                            List<Movie> movies = response.body().getResults();
                            recyclerView.setAdapter(new MoviesAdapter(movies, mimagesize, R.layout.list_item_movie, getActivity().getApplicationContext()));
                        }

                        @Override
                        public void onFailure(Call<MoviesResponse> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                        }
                    });
        } else {
            Log.d(TAG, "--------- Offline ----------");
            DatabaseHandler db = new DatabaseHandler(getActivity().getApplicationContext());
            int count = db.getMoviesCount();
            Log.d(TAG, "Row count = " + count);

        }

        return rootView;
    }
}

