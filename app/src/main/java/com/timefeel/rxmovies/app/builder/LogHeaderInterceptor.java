package com.timefeel.rxmovies.app.builder;

import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by test on 02/05/2017.
 */



public class LogHeaderInterceptor implements Interceptor {

    public static final String TAG = ServiceHelper.class.getSimpleName();

    @Inject
    public LogHeaderInterceptor() {
        // Intentionally blank
    }

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

