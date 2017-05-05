package com.timefeel.rxmovies.injection.app.main;

import android.content.Context;

import com.timefeel.rxmovies.injection.ActivityScope;
import com.timefeel.rxmovies.ui.main.MainActivity;
import com.timefeel.rxmovies.ui.main.MainContract;
import com.timefeel.rxmovies.ui.main.MainNavigator;
import com.timefeel.rxmovies.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by test on 04/05/2017.
 */

@Module
public class MainModule {

    private final MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @ActivityScope
    MainContract.Navigator provideMainNavigation(MainNavigator navigation) {
        return navigation;
    }

    @Provides
    @ActivityScope
    MainContract.Presenter provideMainPresenter(MainPresenter presenter) {
        return presenter;
    }

    @Provides
    @ActivityScope
    Context provideContext(){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivity provideMainActivity(){
        return mainActivity;
    }

}
