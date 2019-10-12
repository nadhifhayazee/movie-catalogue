package com.example.submission1.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.submission1.R;
import com.example.submission1.adapter.TabAdapter;
import com.example.submission1.database.TableHelper;
import com.example.submission1.view.favorite.FavoriteActivity;
import com.example.submission1.view.movie.MovieCatalogueFragment;
import com.example.submission1.view.tv.TvShowCatalogueFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TableHelper.getMovieHelper().open();
        TableHelper.getTvHelper().open();
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar = findViewById(R.id.simple_toolbar);
        toolbar.setTitle("Movie Catalogue");
        toolbar.inflateMenu(R.menu.main_menu);
        setSupportActionBar(toolbar);
        initTab();
    }

    private void initTab() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new MovieCatalogueFragment(), getResources().getString(R.string.tab_title_movies));
        tabAdapter.addFragment(new TvShowCatalogueFragment(), getResources().getString(R.string.tab_title_tv_show));
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        } else {
            startActivity(new Intent(this, FavoriteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TableHelper.getMovieHelper().close();
        TableHelper.getTvHelper().close();
    }
}
