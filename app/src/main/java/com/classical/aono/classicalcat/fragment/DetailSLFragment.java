package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/9/27.
 */

public class DetailSLFragment extends Fragment {

    public static DetailSLFragment newInstance(String info) {
        Bundle args = new Bundle();
        DetailSLFragment fragment = new DetailSLFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_sl, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        tvInfo.setText(getArguments().getString("info"));
//        tvInfo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar.make(v,"hello",Snackbar.LENGTH_SHORT).show();
//            }
//        });
        return view;
    }
}
