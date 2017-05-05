package com.timefeel.rxmovies.injection.app.main;

import com.timefeel.rxmovies.injection.ActivityScope;
import com.timefeel.rxmovies.injection.app.ApplicationComponent;
import com.timefeel.rxmovies.ui.main.MainActivity;

import dagger.Component;

/**
 * Created by test on 04/05/2017.
 */


@ActivityScope
@Component(
        dependencies = ApplicationComponent.class,
        modules = MainModule.class
)
public interface MainComponent {

    void inject(MainActivity activity);
}
