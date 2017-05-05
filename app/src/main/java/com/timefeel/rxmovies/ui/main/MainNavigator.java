package com.timefeel.rxmovies.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.ui.movies.MoviesFragment;

import javax.inject.Inject;

/**
 * Created by test on 04/05/2017.
 */

public class MainNavigator implements MainContract.Navigator {

    private static final String TAG_DETAILS = "tag_details";
    private static final String TAG_MASTER = "tag_master";
    private MainActivity mainActivity;

    public enum State {
        SINGLE_COLUMN_MASTER, SINGLE_COLUMN_DETAILS, TWO_COLUMNS_EMPTY, TWO_COLUMNS_WITH_DETAILS
    }

    @Inject
    public MainNavigator(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private boolean clearDetails() {
        final Fragment details = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_DETAILS);
        if (details != null) {
            mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .remove(details)
                    .commitNow();
            return true;
        }
        return false;
    }

    private void clearMaster() {
        Fragment master = mainActivity.getSupportFragmentManager().findFragmentByTag(TAG_MASTER);
        if (master != null) {
            mainActivity.getSupportFragmentManager().beginTransaction().remove(master).commitNow();
        }
    }


    @Override
    public void goToMovies() {
        mainActivity.getCustomAppBar().setState(State.TWO_COLUMNS_EMPTY);
        mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
        MoviesFragment master = MoviesFragment.newInstance();
        mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.activity_main__frame_master, master, TAG_MASTER).commitNow();
    }

    @Override
    public void goToSettings() {
        Context context = mainActivity.getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public boolean onBackPressed() {
        State state = mainActivity.getContainersLayout().getState();
        if (state.equals(State.TWO_COLUMNS_WITH_DETAILS) && !mainActivity.getContainersLayout().hasTwoColumns()) {
            if (clearDetails()) {
                mainActivity.getContainersLayout().setState(State.TWO_COLUMNS_EMPTY);
                return true;
            }
        }
        return false;
    }
}
