package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/9/27.
 */

public class DetailHXFragment extends Fragment {

    public static DetailHXFragment newInstance(String info) {
        Bundle args = new Bundle();
        DetailHXFragment fragment = new DetailHXFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_hx, null);
        TextView tvInfo = (TextView) view.findViewById(R.id.tvInfo);
        tvInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        tvInfo.setLineSpacing(1,1.5f);
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
