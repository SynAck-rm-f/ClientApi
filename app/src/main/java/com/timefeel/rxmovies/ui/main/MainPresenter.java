package com.timefeel.rxmovies.ui.main;

import com.timefeel.rxmovies.ui.core.BaseView;

import javax.inject.Inject;

/**
 * Created by test on 04/05/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private MainContract.Navigator navigator;

    @Inject
    public MainPresenter(MainContract.Navigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }


    @Override
    public void clickMovies() {
        if(view != null){
            view.closeDrawer();
        }
        navigator.goToMovies();
    }

    @Override
    public void clickSettings() {
        if(view != null) {
            view.closeDrawer();
        }
        navigator.goToSettings();
    }

}