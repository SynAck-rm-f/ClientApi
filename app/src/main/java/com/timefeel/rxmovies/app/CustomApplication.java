package com.timefeel.rxmovies.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.facebook.stetho.Stetho;


public class CustomApplication extends Application {

    private static Context mcontext;
    /**
     * Called when the application is starting, before any other application objects have been created.
     * Overriding this method is totally optional!
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        CustomApplication.mcontext = getApplicationContext();
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
