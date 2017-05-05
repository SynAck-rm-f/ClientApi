package com.timefeel.rxmovies.injection.app.builder;

import android.content.Context;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.converter.moshi.MoshiConverterFactory;


/**
 * Created by test on 01/05/2017.
 */

@Module
public class NetworkModule {


    @Provides
    OkHttpClient provideHttpClient(Cache cache, StethoInterceptor stethoInterceptor, LogHeaderInterceptor logHeaderInterceptor ) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.cache(cache)
                .addNetworkInterceptor(stethoInterceptor)
                .addInterceptor(logHeaderInterceptor);
        return builder.build();
    }

    @Provides
    Cache provideCache(File myCacheDir){
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(myCacheDir, cacheSize);
    }


    @Provides
    File provideCacheFile(Context context) {
        return new File (context.getExternalCacheDir(), "OkHttpCache");
    }


    @Provides
    MoshiConverterFactory provideMoshiClient() {
        return MoshiConverterFactory.create();
    }


    @Provides
    StethoInterceptor provideStehoInterceptor(){
        return new StethoInterceptor();
    }


    @Provides
    LogHeaderInterceptor providelogHeaderInterceptor(){
        return new LogHeaderInterceptor();
    }

}
