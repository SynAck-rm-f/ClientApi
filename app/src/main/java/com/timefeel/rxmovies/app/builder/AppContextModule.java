package com.timefeel.rxmovies.app.builder;

import android.app.Application;
import android.content.Context;


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
public class AppContextModule {

    private final Context context;

    public AppContextModule(Context pcontext){
        this.context = pcontext.getApplicationContext();
    }

    @AppScope
    @Provides
    public Context provideAppContext(){
        return context;
    }
    /**
     * By convention @Provides methods are named with prefix provide
     * It’s possible for @Provides methods to have dependencies of their own
     * return Context <-- Depandency --> Application.
     * **/
    /*@AppScope
    @Provides
    Context provideAppContext(Application context){
        return context.getApplicationContext();
    }*/
}
