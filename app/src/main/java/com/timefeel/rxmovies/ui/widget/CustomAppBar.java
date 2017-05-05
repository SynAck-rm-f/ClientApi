package com.timefeel.rxmovies.ui.widget;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.ui.main.MainNavigator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by test on 05/05/2017.
 */

public class CustomAppBar extends AppBarLayout{

    private static final String STATE_SUPER = "state_super";
    private static final String STATE_TITLE = "state_title";
    private static final String STATE_TOOLBAR_STATE = "state_toolbar_state";

    @BindView(R.id.view_main_toolbar__toolbar_specific) Toolbar toolbarSpecific;
    @Nullable
    @BindView(R.id.view_main_toolbar__toolbar_general) Toolbar toolbarGeneral;
    @Nullable
    @BindView(R.id.view_main_toolbar__space_toolbar) View space;


    private MainNavigator.State state;

    public CustomAppBar(Context context) {
        super(context);
        init();
    }

    public CustomAppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_main_toolbar, this, true);
        ButterKnife.bind(this);

        if (toolbarGeneral != null) {
            toolbarGeneral.setNavigationIcon(R.drawable.ic_menu_24dp);
        } else {
            toolbarSpecific.setNavigationIcon(R.drawable.ic_menu_24dp);
        }
    }

    public boolean hasGeneralToolbar() {
        return toolbarGeneral != null;
    }

    public void setOnNavigationClickListener(OnClickListener onNavigationClickListener) {
        if (toolbarGeneral != null) {
            toolbarGeneral.setNavigationOnClickListener(onNavigationClickListener);
        } else {
            toolbarSpecific.setNavigationOnClickListener(onNavigationClickListener);
        }
    }

    public void setTitle(String title) {
        toolbarSpecific.setTitle(title);
    }

    public void clearMenu() {
        toolbarSpecific.getMenu().clear();
        if (toolbarGeneral != null) {
            toolbarGeneral.getMenu().clear();
        }
    }

    public void setMenuRes(@MenuRes int menuGeneral, @MenuRes int menuSpecific, @MenuRes int menuMerged) {
        toolbarSpecific.getMenu().clear();
        if (toolbarGeneral != null) {
            toolbarGeneral.getMenu().clear();
            toolbarGeneral.inflateMenu(menuGeneral);
            toolbarSpecific.inflateMenu(menuSpecific);
        } else {
            toolbarSpecific.inflateMenu(menuMerged);
        }
    }

    public void setState(MainNavigator.State state) {
        this.state = state;
        switch (state) {
            case SINGLE_COLUMN_MASTER:
            case SINGLE_COLUMN_DETAILS:
                if (space != null) {
                    space.setVisibility(GONE);
                }
                break;
            case TWO_COLUMNS_EMPTY:
            case TWO_COLUMNS_WITH_DETAILS:
                if (space != null) {
                    space.setVisibility(VISIBLE);
                }
                break;
        }
    }
}
