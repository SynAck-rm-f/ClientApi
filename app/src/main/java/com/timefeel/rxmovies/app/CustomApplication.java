package com.timefeel.rxmovies.app;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;
import com.timefeel.rxmovies.injection.app.ApplicationComponent;
import com.timefeel.rxmovies.injection.app.ApplicationModule;
import com.timefeel.rxmovies.injection.app.DaggerApplicationComponent;


public class CustomApplication extends Application {

    private static ApplicationComponent applicationComponent;
    /**
     * Called when the application is starting, before any other application objects have been created.
     * Overriding this method is totally optional!
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    public static ApplicationComponent getAppComponent(Context context) {
        return ((CustomApplication) context.getApplicationContext()).applicationComponent;
    }

}
