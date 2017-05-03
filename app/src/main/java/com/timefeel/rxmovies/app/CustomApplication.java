package com.timefeel.rxmovies.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.facebook.stetho.Stetho;
import com.timefeel.rxmovies.app.builder.AppComponent;
import com.timefeel.rxmovies.app.builder.AppContextModule;
import com.timefeel.rxmovies.app.builder.DaggerAppComponent;


import dagger.internal.DaggerCollections;


public class CustomApplication extends Application {

    private static Context mcontext;
    private static AppComponent appComponent;
    /**
     * Called when the application is starting, before any other application objects have been created.
     * Overriding this method is totally optional!
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        CustomApplication.mcontext = getApplicationContext();
        initAppComponent();

    }

    /**
     * pass Context Application with construct of AppContextModule
     * and ready to inject dependency modules to (Application extends CustomApplication(Singleton Android))
     * **/
    private void initAppComponent(){
        appComponent = DaggerAppComponent.builder().appContextModule(new AppContextModule(this)).build();
    }
    /**
     * @return getApplicationContext()
     */
    public static Context getAppContext() {
        return mcontext;
    }

    /**
     * Called by the system when the device configuration changes while your component is running.
     * Overriding this method is totally optional!
     * */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
    /**
     * This is called when the overall system is running low on memory,
     * and would like actively running processes to tighten their belts.
     * Overriding this method is totally optional!
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
