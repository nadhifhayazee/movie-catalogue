package com.example.submission1.view.favorite;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.submission1.R;
import com.example.submission1.adapter.TabAdapter;
import com.example.submission1.database.TableHelper;
import com.google.android.material.tabs.TabLayout;

public class FavoriteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter tabAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        toolbar = findViewById(R.id.simple_toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        toolbar.setTitle(getResources().getString(R.string.favorite));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        TableHelper.getMovieHelper().open();
        TableHelper.getTvHelper().open();
        initTab();
    }

    private void initTab() {
        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new FavoriteMovieFragment(), getResources().getString(R.string.tab_title_movies));
        tabAdapter.addFragment(new FavoriteTvFragment(), getResources().getString(R.string.tab_title_tv_show));
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TableHelper.getMovieHelper().close();
        TableHelper.getTvHelper().close();
    }
}
