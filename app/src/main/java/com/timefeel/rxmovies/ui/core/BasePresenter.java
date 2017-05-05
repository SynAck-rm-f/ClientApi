package com.timefeel.rxmovies.ui.core;

/**
 * Created by test on 04/05/2017.
 */

public interface BasePresenter<V extends BaseView> {

    void attachView(V view);
    void detachView();
}
