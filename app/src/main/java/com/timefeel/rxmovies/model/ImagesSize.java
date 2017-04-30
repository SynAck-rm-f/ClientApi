package com.timefeel.rxmovies.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by test on 28/02/2017.
 */

public class ImagesSize {
    private String base_url;
    private String secure_base_url;
    private List backdrop_sizes = new ArrayList();
    private List logo_sizes = new ArrayList();
    private List poster_sizes = new ArrayList();
    private List profile_sizes = new ArrayList();
    private List still_sizes = new ArrayList();
    @SuppressWarnings("moshiuse")
    public ImagesSize(){

    }
    public ImagesSize(String base_url, String secure_base_url, List backdrop_sizes, List logo_sizes,
                      List poster_sizes, List profile_sizes, List still_sizes){
        this.base_url = base_url;
        this.secure_base_url = secure_base_url;
        this.backdrop_sizes = backdrop_sizes;
        this.logo_sizes = logo_sizes;
        this.poster_sizes = poster_sizes;
        this.profile_sizes = profile_sizes;
        this.still_sizes = still_sizes;
        }
        public String getBaseurl(){
            return base_url;
        }
        public String getSecurebase_url(){
            return secure_base_url;
        }
        public List getBackdrop_sizes(){
            return backdrop_sizes;
        }
        public List getLogo_sizes(){
            return logo_sizes;
        }
        public List getPoster_sizes(){
            return poster_sizes;
        }
        public List getProfile_sizes(){
            return profile_sizes;
        }
        public List getStill_sizes(){
            return still_sizes;
        }
}
