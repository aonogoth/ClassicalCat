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
import com.classical.aono.classicalcat.common.ThreadPool;
import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.http.BooksSupplier;
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
    private BookAdapter mAdapter;

    private static final int ANIM_DURATION_FAB = 400;
    private Repository<Result<List<Book>>> booksRepository;
    private CategoryJdmzFragment.SearchObservable searchObservable;
    private BooksSupplier booksSupplier;

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

        mAdapter = new BookAdapter(this, getActivity());
        mRecyclerView.setAdapter(mAdapter);

        setUpRepository();

        return view;
    }

    private RecyclerItemClickListener.OnItemClickListener onItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Book book = mAdapter.getBook(position);
            Intent intent = new Intent(getActivity(), BookDetailActivity.class);
            intent.putExtra("book", book);

            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            view.findViewById(R.id.ivBook), "transition_book_img");

            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());

        }
    };


    public class SearchObservable extends BaseObservable {

        public void doSearch(String key) {
            booksSupplier.setKey(key);
            dispatchUpdate();
        }

    }


    private void setUpRepository() {
        searchObservable = new CategoryJdmzFragment.SearchObservable();
        booksSupplier = new BooksSupplier("Oracle");
        // Set up books repository
        booksRepository = Repositories
                .repositoryWithInitialValue(Result.<List<Book>>absent())
                .observe(searchObservable)
                .onUpdatesPerLoop()
                .goTo(ThreadPool.executor)
                .thenGetFrom(booksSupplier)
                .compile();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //mFabButton.setTranslationY(2 * 56);
        doSearch("Oracle");
    }

    @Override
    public void update() {
        mProgressBar.setVisibility(View.GONE);
        //startFABAnimation();
        if (booksRepository.get().isPresent()) {
            mAdapter.updateItems(booksRepository.get().get(), true);
        }
    }
    //    private void startFABAnimation() {
//        mFabButton.animate()
//                .translationY(0)
//                .setInterpolator(new OvershootInterpolator(1.f))
//                .setStartDelay(500)
//                .setDuration(ANIM_DURATION_FAB)
//                .start();
//    }
    @Override
    public void onResume() {
        super.onResume();
        booksRepository.addUpdatable(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        booksRepository.removeUpdatable(this);
    }

    private void doSearch(String keyword) {
        mProgressBar.setVisibility(View.VISIBLE);
        mAdapter.clearItems();
        searchObservable.doSearch(keyword);
    }

//    private void setUpFAB(View view) {
//        mFabButton = (FloatingActionButton) view.findViewById(R.id.fab_normal);
//        mFabButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new MaterialDialog.Builder(getActivity())
//                        .title(R.string.search)
//                        //.inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
//                        .input(R.string.input_hint, R.string.input_prefill, new MaterialDialog.InputCallback() {
//                            @Override
//                            public void onInput(MaterialDialog dialog, CharSequence input) {
//                                // Do something
//                                if (!TextUtils.isEmpty(input)) {
//                                    doSearch(input.toString());
//                                }
//                            }
//                        }).show();
//            }
//        });
//    }


}