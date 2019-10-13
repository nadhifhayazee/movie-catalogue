package com.example.submission1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.submission1.model.MovieModel;
import com.example.submission1.statics.EndPoint;
import com.example.submission1.view.detail.DetailMovieActivity;
import com.example.submission1.view.favorite.FavoriteView;

import java.util.ArrayList;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MovieModel> movies;
    private String type;
    private FavoriteView favoriteView;

    public FavoriteAdapter(Context context, ArrayList<MovieModel> movies, String type, FavoriteView favoriteView) {
        this.context = context;
        this.movies = movies;
        this.type = type;
        this.favoriteView = favoriteView;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, viewGroup, false);
        return new ViewHolder(view);
    }

    public void removeItem(int movie_id) {
        int i = 0;
        int position = 0;
        for (MovieModel item : movies) {
            if (item.id == movie_id) {
                position = i;
            }
            i++;
        }
        this.movies.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, movies.size());
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (movies.size() != 0) {
            final MovieModel movie = movies.get(position);
            holder.bind(movie);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailMovieActivity.class);
                    intent.putExtra("movie_id", movie.id);
                    intent.putExtra("type", type);
                    context.startActivity(intent);
                }
            });
        } else {
            favoriteView.onEmpty();
        }
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
        private TextView tvRealease;
        private TextView tvRating;
        private ImageView imgPoster;

        ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.text_title);
            tvGenre = view.findViewById(R.id.text_genre);
            tvRating = view.findViewById(R.id.text_rating);
            tvRealease = view.findViewById(R.id.text_date_release);
            imgPoster = view.findViewById(R.id.image_poster);
        }

        void bind(MovieModel movie) {
            if (type.equals("movie")) {
                tvTitle.setText(movie.original_title);
                tvRealease.setText(movie.release_date);
            } else {
                tvTitle.setText(movie.original_name);
                tvRealease.setText(movie.first_air_date);
            }
            tvGenre.setText(movie.getGenre());
            tvRating.setText(movie.vote_average + "/10");
            Glide.with(itemView.getContext())
                    .load(EndPoint.IMAGE_URL + movie.poster_path)
                    .apply(new RequestOptions().transform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(16))))
                    .into(imgPoster);
        }

    }
}

