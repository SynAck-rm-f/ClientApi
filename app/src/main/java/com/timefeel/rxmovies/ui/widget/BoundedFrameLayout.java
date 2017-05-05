package com.timefeel.rxmovies.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.timefeel.rxmovies.R;

/**
 * Created by test on 05/05/2017.
 */

public class BoundedFrameLayout extends FrameLayout {

    private int boundedWidth;
    private int boundedHeight;

    public BoundedFrameLayout(Context context) {
        super(context);
        boundedWidth = 0;
        boundedHeight = 0;
    }

    public BoundedFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BoundedView);
        boundedWidth = a.getDimensionPixelSize(R.styleable.BoundedView_bounded_width, 0);
        boundedHeight = a.getDimensionPixelSize(R.styleable.BoundedView_bounded_height, 0);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Adjust width as necessary
        int measuredWidth = MeasureSpec.getSize(widthMeasureSpec);
        if (boundedWidth > 0 && boundedWidth < measuredWidth) {
            int measureMode = MeasureSpec.getMode(widthMeasureSpec);
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(boundedWidth, measureMode);
        }
        // Adjust height as necessary
        int measuredHeight = MeasureSpec.getSize(heightMeasureSpec);
        if (boundedHeight > 0 && boundedHeight < measuredHeight) {
            int measureMode = MeasureSpec.getMode(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(boundedHeight, measureMode);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

//    public void setBoundedWidth(int boundedWidth){
//        this.boundedWidth = boundedWidth;
//        requestLayout();
//    }
}

