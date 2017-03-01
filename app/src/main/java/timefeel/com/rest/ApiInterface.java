package timefeel.com.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import timefeel.com.model.Configuration;
import timefeel.com.model.MoviesResponse;

/**
 * Created by test on 10/02/2017.
 */

public interface ApiInterface {


    @GET("configuration")
    Call<Configuration> getConfiguration(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);


}
