package com.timefeel.rxmovies.ui.splash.core;

import com.timefeel.rxmovies.api.ApiInterface;
import com.timefeel.rxmovies.models.Movie;
import com.timefeel.rxmovies.models.MoviesResponse;
import com.timefeel.rxmovies.ui.splash.SplashActivity;
import com.timefeel.rxmovies.utils.UtilsTF;

import rx.Observable;


/**
 * Created by test on 02/05/2017.
 */

public class SplashModel {


    ApiInterface apiInterface;
    SplashActivity splashContext;

    public SplashModel(ApiInterface apiInterface, SplashActivity splashContext) {
        this.apiInterface = apiInterface;
        this.splashContext = splashContext;
    }

    Observable<MoviesResponse> provideListMovies(){
        return apiInterface.getListMovies();
    }

    Observable<Boolean> isNetwrkAvailable(){
        return UtilsTF.isNetworkAvailableObservable(splashContext);
    }
}
