package com.example.submission1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.submission1.R;
import com.example.submission1.adapter.TabAdapter;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private Toolbar toolbar;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar = findViewById(R.id.simple_toolbar);
        initTab();
    }

    private void initTab() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new MovieCatalogueFragment(), "Movies");
        tabAdapter.addFragment(new TvShowCatalogueFragment(), "Tv Show");
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
