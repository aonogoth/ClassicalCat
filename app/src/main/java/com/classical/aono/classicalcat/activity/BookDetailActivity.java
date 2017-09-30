package com.classical.aono.classicalcat.activity;

import android.graphics.Bitmap;
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
import com.bumptech.glide.request.target.BitmapImageViewTarget;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2017/9/27.
 */

public class BookDetailActivity extends AppCompatActivity implements Updatable{
    private ViewPager mViewPager;
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

        setUpRepository();

    }

    @Override
    public void update() {
        if (worksRepository.get().isPresent()) {
            mBook = worksRepository.get().get();

            CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(mBook.getName());

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
    }


    private void setUpRepository() {
        searchObservable = new SearchObservable();
        worksSupplier = new WorkDetailSupplier(workId);
        worksRepository = Repositories
                .repositoryWithInitialValue(Result.<Work>absent())
                .observe(searchObservable)
                .onUpdatesPerLoop()
                .goTo(ThreadPool.executor)
                .thenGetFrom(worksSupplier)
                .compile();
    }

//    private void getRequest() {
//
//        final Request request=new Request.Builder()
//                .get()
//                .tag(this)
//                .url("http://118.178.95.56:8084/api/aono/GetWorkDetail?ID="+workId)
//                .build();
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Response response = null;
//                try {
//                    response = client.newCall(request).execute();
//                    if (response.isSuccessful()) {
////                        Log.i("WY","打印GET响应的数据：" + response.body().string());
//                        JSONObject json = new JSONObject(response.body().string());
//                        Log.e("Hehe",json.toString());
//                        JSONArray jaBooks = json.optJSONArray("data");
//                        Gson gson = new Gson();
//                        List<Work> mBooks = gson.fromJson(jaBooks.toString(), new TypeToken<List<Work>>() {
//                        }.getType());
//                        mBook = mBooks.get(0);
//
//                        try {
//                            Thread.sleep(5000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
//                        collapsingToolbar.setTitle(mBook.getName());
//                        //collapsingToolbar.setExpandedTitleColor(Color.BLUE);
//
////                        ImageView ivImage = (ImageView) findViewById(R.id.ivImage);
////                        Glide.with(ivImage.getContext())
////                                .load("http://118.178.95.56:8086/UploadFiles/"+mBook.getImageUrlDetail())
////                                .fitCenter()
////                                .into(ivImage);
//
////                        Glide.with(getApplicationContext())
////                                .load("http://118.178.95.56:8086/UploadFiles/"+mBook.getImageUrlDetail())
////                                .asBitmap()
////                                .into(new BitmapImageViewTarget(ivImage) {
////                                    @Override
////                                    protected void setResource(Bitmap resource) {
////                                        //Play with bitmap
////                                        super.setResource(resource);
////                                    }
////                                });
//
//                        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//                        setupViewPager(mViewPager);
//
//                        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//                        tabLayout.addTab(tabLayout.newTab().setText("速览"));
//                        tabLayout.addTab(tabLayout.newTab().setText("简介"));
//                        tabLayout.addTab(tabLayout.newTab().setText("花絮"));
//                        tabLayout.setupWithViewPager(mViewPager);
//
//                    } else {
//                        throw new IOException("Unexpected code " + response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }


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
