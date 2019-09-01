package com.example.submission1.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submission1.R;
import com.example.submission1.adapter.MovieAdapter;
import com.example.submission1.model.Movie;
import com.example.submission1.presenter.MoviesPresenter;
import com.example.submission1.presenter.MoviesView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieCatalogueFragment extends Fragment implements MoviesView {

    private View view;
    private MoviesPresenter presenter;
    private RecyclerView recyclerView;

    public MovieCatalogueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        presenter = new MoviesPresenter(this, getActivity());
        return view;
    }

    @Override
    public void showMovieList(ArrayList<Movie> movies) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MovieAdapter(getActivity(), movies));
    }
}
