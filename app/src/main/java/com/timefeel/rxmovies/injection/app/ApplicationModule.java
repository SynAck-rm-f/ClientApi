package com.timefeel.rxmovies.injection.app;

import android.app.Application;
import android.content.Context;


import com.timefeel.rxmovies.injection.AppScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by test on 01/05/2017.
 */

/**
 * All @Provides methods must belong to a module
 * These are just classes with annotations @Module
 * **/
@Module
public class ApplicationModule {

    private final Application application;
    private final Context context;

    public ApplicationModule(Application application){
        this.application = application;
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return context;
    }
}
