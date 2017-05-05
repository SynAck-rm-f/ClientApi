package com.timefeel.rxmovies.ui.main;

import android.content.Intent;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.injection.app.ApplicationComponent;
import com.timefeel.rxmovies.injection.app.builder.LogHeaderInterceptor;
import com.timefeel.rxmovies.injection.app.main.DaggerMainComponent;
import com.timefeel.rxmovies.injection.app.main.MainComponent;
import com.timefeel.rxmovies.injection.app.main.MainModule;
import com.timefeel.rxmovies.ui.core.BaseActivity;
import com.timefeel.rxmovies.ui.widget.ContainersLayout;
import com.timefeel.rxmovies.ui.widget.CustomAppBar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by test on 04/05/2017.
 */

public class MainActivity extends BaseActivity implements MainContract.View, NavigationView.OnNavigationItemSelectedListener {

    @Inject MainContract.Presenter presenter;
    @Inject MainContract.Navigator navigator;

    @BindView(R.id.activity_main__nav) NavigationView navigationView;

    @Nullable
    @BindView(R.id.activity_main__nav_side) NavigationView navigationSideView;
    @Nullable
    @BindView(R.id.activity_main__insets) ViewGroup insetsView;

    @BindView(R.id.activity_main__drawer) DrawerLayout drawer;
    @BindView(R.id.activity_main__custom_appbar) CustomAppBar customAppBar;
    @BindView(R.id.activity_main__containers_layout) ContainersLayout containersLayout;

    private MainComponent mainComponent;
    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (insetsView != null && navigationSideView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(insetsView, new OnApplyWindowInsetsListener() {
                @Override
                public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                    ((ViewGroup.MarginLayoutParams) insetsView.getLayoutParams()).topMargin = insets.getSystemWindowInsetTop();
                    ((ViewGroup.MarginLayoutParams) insetsView.getLayoutParams()).bottomMargin = insets.getSystemWindowInsetBottom();
                    insetsView.requestLayout();
                    ((ViewGroup.MarginLayoutParams) navigationSideView.getLayoutParams()).topMargin = (-insets.getSystemWindowInsetTop());
                    navigationSideView.requestLayout();
                    return insets.consumeSystemWindowInsets();
                }
            });
            navigationSideView.setNavigationItemSelectedListener(this);
        }

        navigationView.setNavigationItemSelectedListener(this);
        customAppBar.setOnNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDrawer();
            }
        });

        presenter.attachView(this);
        if(savedInstanceState == null){
            presenter.clickMovies();
        }
    }

    @Override
    protected void setupActivityComponent(ApplicationComponent applicationComponent) {
        mainComponent = DaggerMainComponent.builder()
                .applicationComponent(applicationComponent)
                .mainModule(new MainModule(this))
                .build();

        mainComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void closeDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.postDelayed(new Runnable() {
                @Override
                public void run() {
                    drawer.closeDrawer(GravityCompat.START);
                }
            }, 100);
        }
    }

    @Override
    public void openDrawer() {
        if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
        else {
            Log.d(TAG, "unable to open drawer");
        }
    }


    public void toggleDrawer() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (drawer != null && !drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_main_nav__settings:
                presenter.clickSettings();
                break;

            default:
                return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer != null && drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!navigator.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public CustomAppBar getCustomAppBar() {
        return customAppBar;
    }

    public ContainersLayout getContainersLayout() {
        return containersLayout;
    }

    public MainContract.Navigator getNavigator() {
        return navigator;
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

}