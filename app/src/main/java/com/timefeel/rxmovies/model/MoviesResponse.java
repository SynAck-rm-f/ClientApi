package com.timefeel.rxmovies.model;

/**
 * Created by test on 08/01/2017.
 */



import java.util.List;


public class MoviesResponse {
    private int page;
    private List<Movie> results;
    private int totalResults;
    private int totalPages;

    @SuppressWarnings("moshiuse")
    public MoviesResponse(){

    }
    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

}