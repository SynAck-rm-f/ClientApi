package com.timefeel.rxmovies.utils;

import android.content.Context;

import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

/**
 * Created by test on 28/02/2017.
 */

public interface MyDataSizeModel {
    public String buildUrl(int width, int height);

    public class MyUrlLoader extends BaseGlideUrlLoader<MyDataSizeModel> {

        public MyUrlLoader(Context context) {
            super(context);
        }

        @Override
        protected String getUrl(MyDataSizeModel model, int width, int height) {
            return null;
        }
    }
}
