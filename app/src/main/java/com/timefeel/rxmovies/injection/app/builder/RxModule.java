package com.timefeel.rxmovies.injection.app.builder;

import com.timefeel.rxmovies.utils.rx.AppRxSchedulers;
import com.timefeel.rxmovies.utils.rx.RxSchedulers;

import dagger.Module;
import dagger.Provides;

/**
 * Created by test on 02/05/2017.
 */


@Module
public class RxModule {

    @Provides
    RxSchedulers provideRxSchedulers() {
        return new AppRxSchedulers();
    }
}