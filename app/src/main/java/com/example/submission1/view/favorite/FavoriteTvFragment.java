package com.example.submission1.view.favorite;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.submission1.R;
import com.example.submission1.adapter.FavoriteAdapter;
import com.example.submission1.model.MovieModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvFragment extends Fragment implements FavoriteView {
    private View view;
    private FavoritePresenter presenter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView empty_view;

    public FavoriteTvFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        progressBar = view.findViewById(R.id.progress_bar);
        empty_view = view.findViewById(R.id.empty_view);
        presenter = new FavoritePresenter(this);
        return view;
    }

    @Override
    public void showFavorite(ArrayList<MovieModel> resuts) {
        progressBar.setVisibility(View.GONE);
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(getActivity(), resuts, "tv", this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(favoriteAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        empty_view.setVisibility(View.GONE);
    }

    @Override
    public void onEmpty() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getTvFavorites();
    }
}
