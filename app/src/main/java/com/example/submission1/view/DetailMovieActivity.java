package com.example.submission1.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission1.R;
import com.example.submission1.model.Movie;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvGenre;
    private TextView tvDuration;
    private TextView tvDirector;
    private TextView tvRating;
    private TextView tvSinopsis;
    private ImageView imgPoster;
    private ImageView imgThumbnail;
    private ImageView btnBack;
    private ImageView btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        tvTitle = findViewById(R.id.text_title);
        tvDirector = findViewById(R.id.text_director);
        tvGenre = findViewById(R.id.text_genre);
        tvRating = findViewById(R.id.text_rating);
        tvDuration = findViewById(R.id.text_duration);
        tvSinopsis = findViewById(R.id.text_sinopsis);
        imgThumbnail = findViewById(R.id.image_thumbnail);
        imgPoster = findViewById(R.id.image_poster);
        btnBack = findViewById(R.id.button_back);
        btnPlay = findViewById(R.id.button_play);
        btnBack.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        bind((Movie) getIntent().getParcelableExtra("MOVIE"));
    }


    private void bind(Movie movie) {
        tvTitle.setText(movie.title);
        tvGenre.setText(movie.genre);
        tvDuration.setText(movie.duration);
        tvDirector.setText(movie.director);
        tvRating.setText(movie.rating);
        tvSinopsis.setText(movie.sinopsis);
        imgThumbnail.setImageResource(movie.thumbnail);
        Glide.with(this)
                .load(movie.poster)
                .apply(new RequestOptions().transform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(16))))
                .into(imgPoster);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back:
                finish();
                break;

            case R.id.button_play:
                Toast.makeText(this, "Coming Soon...", Toast.LENGTH_SHORT).show();
        }
    }
}
