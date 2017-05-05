package com.timefeel.rxmovies.ui.core;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.timefeel.rxmovies.app.CustomApplication;
import com.timefeel.rxmovies.injection.app.ApplicationComponent;

/**
 * Created by test on 04/05/2017.
 */

public abstract class BaseActivity extends AppCompatActivity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent(CustomApplication.getAppComponent(this));
    }

    protected abstract void setupActivityComponent(ApplicationComponent applicationComponent);
}
