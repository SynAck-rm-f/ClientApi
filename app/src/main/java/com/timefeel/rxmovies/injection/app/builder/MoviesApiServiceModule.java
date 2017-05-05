package com.timefeel.rxmovies.injection.app.builder;

import com.timefeel.rxmovies.api.ApiInterface;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by test on 01/05/2017.
 */

@Module
public class MoviesApiServiceModule {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String TAG = MoviesApiServiceModule.class.getSimpleName();

    @Provides
    ApiInterface provideApiInterface(OkHttpClient client, MoshiConverterFactory moshi)
    {
        Retrofit retrofit =   new Retrofit.Builder().client(client)
                .baseUrl(BASE_URL).addConverterFactory(moshi).build();

        return  retrofit.create(ApiInterface.class);
    }

}
