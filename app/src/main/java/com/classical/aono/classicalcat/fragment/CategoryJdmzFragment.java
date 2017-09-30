package com.classical.aono.classicalcat.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.activity.BookDetailActivity;
import com.classical.aono.classicalcat.adapter.BookAdapter;
import com.classical.aono.classicalcat.adapter.WorkAdapter;
import com.classical.aono.classicalcat.common.ThreadPool;
import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.domain.Work;
import com.classical.aono.classicalcat.http.BooksSupplier;
import com.classical.aono.classicalcat.http.WorksSupplier;
import com.classical.aono.classicalcat.widget.RecyclerItemClickListener;
import com.google.android.agera.BaseObservable;
import com.google.android.agera.Repositories;
import com.google.android.agera.Repository;
import com.google.android.agera.Result;
import com.google.android.agera.Updatable;

import java.util.List;

/**
 * Created by gotha on 2017/9/26.
 */

public class CategoryJdmzFragment extends Fragment implements Updatable {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    //private BookAdapter mAdapter;
    private WorkAdapter workAdapter;

    private static final int ANIM_DURATION_FAB = 400;
    //private Repository<Result<List<Book>>> booksRepository;
    private SearchObservable searchObservable;
    //private BooksSupplier booksSupplier;
    private WorksSupplier worksSupplier;
    private Repository<Result<List<Work>>> worksRepository;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_books, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), onItemClickListener));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        workAdapter = new WorkAdapter(this, getActivity());
        mRecyclerView.setAdapter(workAdapter);

        setUpRepository();

        return view;
    }

    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Work book = workAdapter.getBook(position);
            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra("workid", book.getID());

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            view.findViewById(R.id.ivBook), "transition_book_img");

            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

        }
    };


    public class SearchObservable extends BaseObservable {
    }


    private void setUpRepository() {
        searchObservable = new SearchObservable();
        //booksSupplier = new BooksSupplier("All");
        worksSupplier = new WorksSupplier("近代名著");
        // Set up books repository
        worksRepository = Repositories
                .repositoryWithInitialValue(Result.<List<Work>>absent())
                .observe(searchObservable)
                .onUpdatesPerLoop()
                .goTo(ThreadPool.executor)
                .thenGetFrom(worksSupplier)
                .compile();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void update() {
        mProgressBar.setVisibility(View.GONE);
        //startFABAnimation();
        if (worksRepository.get().isPresent()) {
            workAdapter.updateItems(worksRepository.get().get(), true);
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

}