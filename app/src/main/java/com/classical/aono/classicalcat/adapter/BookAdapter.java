package com.classical.aono.classicalcat.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.classical.aono.classicalcat.R;
import com.classical.aono.classicalcat.common.Utils;
import com.classical.aono.classicalcat.domain.Book;
import com.classical.aono.classicalcat.fragment.BooksFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/9/27.
 */

public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    //private BooksFragment booksFragment;
    private Fragment fragment;
    private final int mBackground;
    private List<Book> mBooks = new ArrayList<Book>();
    private final TypedValue mTypedValue = new TypedValue();

    private static final int ANIMATED_ITEMS_COUNT = 4;

    private boolean animateItems = false;
    private int lastAnimatedPosition = -1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public BookAdapter(Fragment fragment, Context context) {
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


    public void updateItems(List<Book> books, boolean animated) {
        animateItems = animated;
        lastAnimatedPosition = -1;
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
        Book book = mBooks.get(position);
        holder.tvTitle.setText(book.getTitle());
        String desc = "作者: " + (book.getAuthor().length > 0 ? book.getAuthor()[0] : "") + "\n副标题: " + book.getSubtitle()
                + "\n出版年: " + book.getPubdate() + "\n页数: " + book.getPages() + "\n定价:" + book.getPrice();
        holder.tvDesc.setText(desc);
        Glide.with(holder.ivBook.getContext())
                .load(book.getImage())
                .fitCenter()
                .into(holder.ivBook);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mBooks.size();
    }


    public Book getBook(int pos) {
        return mBooks.get(pos);
    }


}

