package com.example.submission1.view.detail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission1.R;
import com.example.submission1.database.TableHelper;
import com.example.submission1.model.MovieModel;
import com.example.submission1.presenter.basedetail.DetailMovieView;
import com.example.submission1.statics.EndPoint;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener, DetailMovieView {

    private TextView tvTitle;
    private TextView tvGenre;
    private TextView tvDuration;
    private TextView tvRelease;
    private TextView tvRating;
    private TextView tvSinopsis;
    private ImageView imgPoster;
    private ImageView imgThumbnail;
    private ImageView btnPlay;
    private Button btnFav;
    private ProgressBar progressBar;
    private String type;
    private int movie_id;
    private DetailMoviePresenter moviePresenter;
    private DetailTvPresenter tvPresenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        tvTitle = findViewById(R.id.text_title);
        tvRelease = findViewById(R.id.text_date_release);
        tvGenre = findViewById(R.id.text_genre);
        tvRating = findViewById(R.id.text_rating);
        tvDuration = findViewById(R.id.text_duration);
        tvSinopsis = findViewById(R.id.text_sinopsis);
        imgThumbnail = findViewById(R.id.image_thumbnail);
        imgPoster = findViewById(R.id.image_poster);
        btnPlay = findViewById(R.id.button_play);
        btnFav = findViewById(R.id.button_favorite);
        progressBar = findViewById(R.id.progress_bar);
        btnPlay.setOnClickListener(this);
        btnFav.setOnClickListener(this);
        toolbar = findViewById(R.id.simple_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        type = getIntent().getStringExtra("type");
        movie_id = getIntent().getIntExtra("movie_id", 0);
        TableHelper.getMovieHelper().open();
        TableHelper.getTvHelper().open();
        if (type.equals("movie")) {
            moviePresenter = new DetailMoviePresenter(this, movie_id);
            moviePresenter.getDetail();
        } else {
            tvPresenter = new DetailTvPresenter(this, movie_id);
            tvPresenter.getDetail();
        }


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_play:
                Toast.makeText(this, getResources().getString(R.string.coming_soon), Toast.LENGTH_SHORT).show();
                break;

            case R.id.button_favorite:
                if (type.equals("movie")) {
                    moviePresenter.onBtnFavoriteClicked();
                } else {
                    tvPresenter.onBtnFavoriteClicked();
                }
                break;
        }
    }

    @Override
    public void bind(MovieModel movie) {
        if (type.equals("movie")) {
            toolbar.setTitle(movie.original_title);
            tvTitle.setText(movie.original_title);
            tvRelease.setText(movie.release_date);
            tvDuration.setText(String.valueOf(movie.runtime));
        } else {
            toolbar.setTitle(movie.original_name);
            tvTitle.setText(movie.original_name);
            tvRelease.setText(movie.first_air_date);
            tvDuration.setText(getResources().getString(R.string.minute));
        }
        tvGenre.setText(movie.getGenre());
        tvRating.setText(movie.vote_average + "/10");
        tvSinopsis.setText(movie.overview);
        if (movie.overview.equals(""))
            tvSinopsis.setText(getResources().getString(R.string.no_review));


        Glide.with(this)
                .load(EndPoint.BACKDROP_URL + movie.backdrop_path)
                .into(imgThumbnail);

        Glide.with(this)
                .load(EndPoint.IMAGE_URL + movie.poster_path)
                .apply(new RequestOptions().transform(new MultiTransformation<Bitmap>(new CenterCrop(), new RoundedCorners(16))))
                .into(imgPoster);
    }


    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onInsertSuccess() {
        Toast.makeText(this, getResources().getString(R.string.insert_success), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertFailed() {
        Toast.makeText(this, getResources().getString(R.string.insert_failed), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void isFavorited() {
        btnFav.setText(getResources().getString(R.string.delete_from_fav));
    }

    @Override
    public void onDeleteSuccess() {
        Toast.makeText(this, getResources().getString(R.string.delete_succes), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeleteFailed() {
        Toast.makeText(this, getResources().getString(R.string.delete_failed), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void isNotFavorited() {
        btnFav.setText(getResources().getString(R.string.add_to_fav));

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TableHelper.getMovieHelper().close();
        TableHelper.getTvHelper().close();
    }
}
