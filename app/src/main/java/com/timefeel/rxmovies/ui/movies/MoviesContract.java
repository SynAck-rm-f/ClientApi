package com.timefeel.rxmovies.ui.movies;

import com.timefeel.rxmovies.ui.core.BaseNavigator;
import com.timefeel.rxmovies.ui.core.BasePresenter;
import com.timefeel.rxmovies.ui.core.BaseView;
import com.timefeel.rxmovies.ui.main.MainContract;

/**
 * Created by test on 05/05/2017.
 */

public interface MoviesContract {

    interface Navigator extends BaseNavigator{

        void goToMoviesDetails();
    }

    interface View extends BaseView{
        void showLoading();

        void hideLoading();
    }
    interface Presenter extends BasePresenter<MainContract.View>{

        void getMovie();
        void clickMovie();

    }
}
