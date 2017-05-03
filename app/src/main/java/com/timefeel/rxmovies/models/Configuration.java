package com.timefeel.rxmovies.models;

import java.util.List;

/**
 * Created by test on 28/02/2017.
 */

public class Configuration {

    private ImagesSize images;
    private List change_keys;

    @SuppressWarnings("moshiuse")
    public Configuration(){

    }
    public ImagesSize getImagesSize(){
        return images;
    }
    }
