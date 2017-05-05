package com.timefeel.rxmovies.ui.main;

import com.timefeel.rxmovies.ui.core.BaseNavigator;
import com.timefeel.rxmovies.ui.core.BasePresenter;
import com.timefeel.rxmovies.ui.core.BaseView;

/**
 * Created by test on 04/05/2017.
 */

public interface MainContract {

    interface Navigator extends BaseNavigator {

        void goToMovies();

        void goToSettings();

        boolean onBackPressed();
    }

    interface View extends BaseView {

        void openDrawer();

        void closeDrawer();

    }

    interface Presenter extends BasePresenter<View> {

        void clickMovies();
        void clickSettings();

    }
}
