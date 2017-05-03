package com.timefeel.rxmovies.ui.splash.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.ui.splash.SplashActivity;

import java.nio.BufferUnderflowException;

import butterknife.ButterKnife;

/**
 * Created by test on 02/05/2017.
 */

public class SplashView {

    private View view;

    public SplashView(SplashActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_splash, parent, true);
        ButterKnife.bind(view, context);
    }

    public View constructView(){
        return view;
    }
}
