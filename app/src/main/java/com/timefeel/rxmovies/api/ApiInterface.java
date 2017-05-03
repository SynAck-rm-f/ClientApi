package com.timefeel.rxmovies.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

import com.timefeel.rxmovies.models.Configuration;
import com.timefeel.rxmovies.models.MoviesResponse;

/**
 * Created by test on 10/02/2017.
 */

public interface ApiInterface {


    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String apiKey);

    @GET("discover/movie")
    Call<MoviesResponse> getOriginalLanguage(@Query("api_key") String apiKey, @Query("with_original_language") String with_original_language);

    @GET("test")
    Observable<MoviesResponse> getListMovies();

}
