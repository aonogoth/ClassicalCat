package com.classical.aono.classicalcat.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.common.ThreadPool;
import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.domain.Work;
import com.classical.aono.classicalcat.fragment.BooksFragment;
import com.classical.aono.classicalcat.fragment.DetailFragment;
import com.classical.aono.classicalcat.http.WorkDetailSupplier;
import com.classical.aono.classicalcat.http.WorksSupplier;
import com.google.android.agera.BaseObservable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */

public class BookDetailActivity extends AppCompatActivity implements Updatable {
    private ViewPager mViewPager;
    //private Book mBook;
    private String workId = "";
    private Work mBook;

    private WorkDetailSupplier worksSupplier;
    private Repository<Result<Work>> worksRepository;
    private SearchObservable searchObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        workId = getIntent().getStringExtra("workid");
        //mBook = (Book) getIntent().getSerializableExtra("book");
        setUpRepository();
        mBook = worksRepository.get().get();

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mBook.getName());
        //collapsingToolbar.setExpandedTitleColor(Color.BLUE);

        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
        Glide.with(ivImage.getContext())
                .load("http://118.178.95.56:8086/UploadFiles/"+mBook.getImageUrlDetail())
                .fitCenter()
                .into(ivImage);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("速览"));
        tabLayout.addTab(tabLayout.newTab().setText("简介"));
        tabLayout.addTab(tabLayout.newTab().setText("花絮"));
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void update() {
        //mProgressBar.setVisibility(View.GONE);
        //startFABAnimation();
        if (worksRepository.get().isPresent()) {
            //workAdapter.updateItems(worksRepository.get().get(), true);
            mBook = worksRepository.get().get();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        worksRepository.addUpdatable(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        worksRepository.removeUpdatable(this);
    }

    public class SearchObservable extends BaseObservable {

        public void doSearch(String key) {
            worksSupplier.setKey(key);
            dispatchUpdate();
        }
    }


    private void setUpRepository() {
        //searchObservable = new BooksFragment.SearchObservable();
        //booksSupplier = new BooksSupplier("All");
        worksSupplier = new WorkDetailSupplier(workId);
        // Set up books repository
        worksRepository = Repositories
                .repositoryWithInitialValue(Result.<Work>absent())
                .observe(searchObservable)
                .onUpdatesPerLoop()
                .goTo(ThreadPool.executor)
                .thenGetFrom(worksSupplier)
                .compile();
    }


    private void setupViewPager(ViewPager mViewPager) {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(DetailFragment.newInstance(mBook.getSL()), "速览");
        adapter.addFragment(DetailFragment.newInstance(mBook.getJJ()), "简介");
        adapter.addFragment(DetailFragment.newInstance(mBook.getHX()), "花絮");
        mViewPager.setAdapter(adapter);
    }

    static class MyPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

}
