package com.classical.aono.classicalcat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.classical.aono.classicalcat.R;

/**
 * Created by admin on 2017/9/27.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public ImageView ivBook;
    public TextView tvTitle;
    public TextView tvDesc;

    public int position;

    public BookViewHolder(View v) {
        super(v);
        ivBook = (ImageView) v.findViewById(R.id.ivBook);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        tvDesc = (TextView) v.findViewById(R.id.tvDesc);
    }
}
