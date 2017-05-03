package com.timefeel.rxmovies.app.builder;

import android.content.Context;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.timefeel.rxmovies.app.CustomApplication;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.converter.moshi.MoshiConverterFactory;


/**
 * Created by test on 01/05/2017.
 */

@Module
class NetworkModule {

    @AppScope
    @Provides
    OkHttpClient provideHttpClient(Cache cache, StethoInterceptor stethoInterceptor, LogHeaderInterceptor logHeaderInterceptor ) {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.cache(cache)
                .addNetworkInterceptor(stethoInterceptor)
                .addInterceptor(logHeaderInterceptor);
        return builder.build();
    }
    @AppScope
    @Provides
    Cache provideCache(File myCacheDir){
        int cacheSize = 10 * 1024 * 1024;
        return new Cache(myCacheDir, cacheSize);
    }

    @AppScope
    @Provides
    File provideCacheFile(Context context) {
        return new File (context.getExternalCacheDir(), "OkHttpCache");
    }

    @AppScope
    @Provides
    MoshiConverterFactory provideMoshiClient() {
        return MoshiConverterFactory.create();
    }

    @AppScope
    @Provides
    StethoInterceptor provideStehoInterceptor(){
        return new StethoInterceptor();
    }

    @AppScope
    @Provides
    LogHeaderInterceptor providelogHeaderInterceptor(){
        return new LogHeaderInterceptor();
    }

}
