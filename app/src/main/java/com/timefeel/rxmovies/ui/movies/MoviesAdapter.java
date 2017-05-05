package com.timefeel.rxmovies.ui.movies;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import com.bumptech.glide.Glide;
import com.timefeel.rxmovies.R;
import com.timefeel.rxmovies.app.CustomApplication;
import com.timefeel.rxmovies.models.ImagesSize;
import com.timefeel.rxmovies.models.Movie;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by test on 10/02/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private ImagesSize buildimages;
    private int rowLayout;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.movies_layout) LinearLayout movieslayout;
        @BindView(R.id.title) TextView movietitle;
        @BindView(R.id.subtitle) TextView data;
        @BindView(R.id.description) TextView moviedescription;
        @BindView(R.id.rating) TextView rating;
        @BindView(R.id.imagemovie) ImageView imageview;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public MoviesAdapter(List<Movie> movies, ImagesSize buildimages, int rowLayout, Context context) {
        this.movies = movies;
        this.buildimages = buildimages;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        Glide.with(context)
                .load(buildimages.getBaseurl() + buildimages.getBackdrop_sizes().get(0) + movies.get(position).getBackdropPath())
                .into(holder.imageview);

        holder.movietitle.setText(movies.get(position).getTitle());
        holder.data.setText(movies.get(position).getReleaseDate());
        holder.moviedescription.setText(movies.get(position).getOverview());
        holder.rating.setText(movies.get(position).getVoteAverage().toString());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

}















