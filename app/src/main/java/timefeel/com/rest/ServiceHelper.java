package timefeel.com.rest;

/**
 * Created by test on 08/01/2017.
 */



import android.util.Log;


import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;


import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timefeel.com.CustomApplication;
import timefeel.com.model.MoviesResponse;


public class ServiceHelper {

    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static final String TAG = ServiceHelper.class.getSimpleName();

    private static OkHttpClient.Builder mclient = new OkHttpClient.Builder();
    private static ServiceHelper minstance = new ServiceHelper();
    private ApiInterface mservice;


    private ServiceHelper(){
        Retrofit retrofit = createAdapter();
        mservice = retrofit.create(ApiInterface.class);

    }
    public static ServiceHelper GetInstance(){
        return minstance;
    }

    private  Retrofit createAdapter() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        /** this path lives in their private storage
         * these files will be deleted when the application is uninstalled*/
        File myCacheDir = new File(CustomApplication.getAppContext().getExternalCacheDir(), "OkHttpCache");
        int cacheSize = 10 * 1024 * 1024;
        Cache cacheDir = new Cache(myCacheDir, cacheSize);
                mclient.cache(cacheDir)
                        //.addNetworkInterceptor(new HeaderInterceptor())
                        //.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                        .addNetworkInterceptor(new LoggingInterceptor());
                        //.addInterceptor(interceptor);
                        //.addNetworkInterceptor(new StethoInterceptor())


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mclient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call<MoviesResponse> getTopRatedMovies(String apikey) {
        return mservice.getTopRatedMovies(apikey);
    }

    /** Dangerous interceptor that rewrites the server's cache-control header. */
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {

        @Override public Response intercept(Interceptor.Chain chain) throws IOException {

            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .addHeader("RWrite", "heeloserv")
                    .build();
        }
    };

    private class LoggingInterceptor implements Interceptor {

        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

     private class HeaderInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();
            request = request.newBuilder()
                    .addHeader("appid", "hello")
                    .addHeader("deviceplatform", "android")
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:38.0) Gecko/20100101 Firefox/38.0")
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    }

}