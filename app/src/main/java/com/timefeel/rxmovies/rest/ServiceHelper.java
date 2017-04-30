package com.timefeel.rxmovies.rest;

/**
 * Created by test on 08/01/2017.
 */


import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import com.timefeel.rxmovies.app.CustomApplication;
import com.timefeel.rxmovies.model.Configuration;
import com.timefeel.rxmovies.model.MoviesResponse;


public class ServiceHelper {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String TAG = ServiceHelper.class.getSimpleName();

    private static OkHttpClient.Builder sclient = new OkHttpClient.Builder();
    private static ServiceHelper sinstance = new ServiceHelper();
    private  ApiInterface mservice;


    private ServiceHelper() {
        Retrofit retrofit = createAdapter();
        mservice = retrofit.create(ApiInterface.class);

    }

    public static ServiceHelper GetInstance() {
        return sinstance;
    }

    private Retrofit createAdapter() {
        /** this path lives in their private storage
         *  these files will be deleted when the application is uninstalled
         * */
        File myCacheDir = new File(CustomApplication.getAppContext().getExternalCacheDir(), "OkHttpCache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cacheDir = new Cache(myCacheDir, cacheSize);
        sclient.cache(cacheDir)
                .addInterceptor(new LogHeaderInterceptor())
                .addNetworkInterceptor(new StethoInterceptor());

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(sclient.build())
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }


    public Call<MoviesResponse> getOriginalLanguage(String apikey, String with_original_language) {
        return mservice.getOriginalLanguage(apikey, with_original_language);
    }

    public Call<Configuration> getConfiguration(String apikey) {
        return mservice.getConfiguration(apikey);
    }


    private class LogHeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();

            request = request.newBuilder()
                    /*.cacheControl(new CacheControl.Builder().noCache()
                            .build()) // disable cache okhttp*/
                    .addHeader("appid", "hello")
                    .addHeader("deviceplatform", "android")
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                    .build();

            Log.d(TAG, String.format("Sending request for %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t1 = System.nanoTime();

            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

}