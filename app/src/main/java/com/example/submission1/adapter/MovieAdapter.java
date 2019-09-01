package com.example.submission1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission1.R;
import com.example.submission1.model.Movie;
import com.example.submission1.view.DetailMovieActivity;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.bind(movie);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra("MOVIE", movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvGenre;
        private TextView tvDuration;
        private TextView tvDirector;
        private TextView tvRating;
        private ImageView imgPoster;

        ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.text_title);
            tvDirector = view.findViewById(R.id.text_director);
            tvGenre = view.findViewById(R.id.text_genre);
            tvRating = view.findViewById(R.id.text_rating);
            tvDuration = view.findViewById(R.id.text_duration);
            imgPoster = view.findViewById(R.id.image_poster);
        }

        void bind(Movie movie) {
            tvTitle.setText(movie.title);
            tvGenre.setText(movie.genre);
            tvDuration.setText(movie.duration);
            tvDirector.setText(movie.director);
            tvRating.setText(movie.rating);
            Glide.with(itemView.getContext())
                    .load(movie.poster)
                    .apply(new RequestOptions().transform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(16))))
                    .into(imgPoster);
        }

    }
}

