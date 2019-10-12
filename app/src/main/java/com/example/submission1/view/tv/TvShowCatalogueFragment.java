package com.example.submission1.view.tv;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission1.R;
import com.example.submission1.adapter.MovieAdapter;
import com.example.submission1.model.Genre;
import com.example.submission1.model.MovieModel;
import com.example.submission1.presenter.basecatalogue.CatalogueView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowCatalogueFragment extends Fragment implements CatalogueView {

    private View view;
    private TvShowPresenter presenter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


    public TvShowCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);

        presenter = new TvShowPresenter(this);
        presenter.initGenres();
        return view;
    }


    @Override
    public void showMovieList(ArrayList<MovieModel> movies, ArrayList<Genre> genres) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MovieAdapter(getActivity(), movies,genres,"tv"));
        recyclerView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);

    }
}
