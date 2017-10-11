package com.classical.aono.classicalcat.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.bumptech.glide.Glide;
import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.common.Utils;
import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.domain.Work;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gotha on 2017/9/29.
 */

public class WorkAdapter extends RecyclerView.Adapter<BookViewHolder> {
    //private BooksFragment booksFragment;
    private Fragment fragment;
    private final int mBackground;
    private List<Work> mBooks = new ArrayList<Work>();
    private final TypedValue mTypedValue = new TypedValue();

    private static final int ANIMATED_ITEMS_COUNT = 4;

    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public WorkAdapter(Fragment fragment, Context context) {
        this.fragment = fragment;
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
    }


    private void runEnterAnimation(View view, int position) {
        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(fragment.getActivity()));
            view.animate()
                    .translationY(0)
                    .setStartDelay(100 * position)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }


    public void updateItems(List<Work> books, boolean animated) {
        animateItems = animated;
        lastAnimatedPosition = -1;
        mBooks.removeAll(mBooks);
        mBooks.addAll(books);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mBooks.clear();
        notifyDataSetChanged();
    }


    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent,
                                             int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        //v.setBackgroundResource(mBackground);
        // set the view's size, margins, paddings and layout parameters
        BookViewHolder vh = new BookViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        runEnterAnimation(holder.itemView, position);
        Work book = mBooks.get(position);
        holder.tvTitle.setText(book.getName());
        if(book.getForeignName() != null && book.getForeignName() != "")
        {
            String desc = "\n作者: " + book.getCountry()+" | "+ book.getAuthor() + "\n\n别称: " + book.getForeignName();
            holder.tvDesc.setText(desc);
        }
        else
        {
            String desc = "\n作者: " + book.getCountry()+" | "+ book.getAuthor() + "\n\n";
            holder.tvDesc.setText(desc);
        }


        Glide.with(holder.ivBook.getContext())
                .load("http://118.178.95.56:8086/UploadFiles/"+book.getImageUrlList())
                .fitCenter()
                .into(holder.ivBook);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBooks.size();
    }


    public Work getBook(int pos) {
        return mBooks.get(pos);
    }


}
