package com.classical.aono.classicalcat.fragment;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.classical.aono.classicalcat.MaterialApplication;
import com.classical.aono.classicalcat.R;

/**
 * Created by gotha on 2017/9/27.
 */

public class DetailHXFragment extends Fragment {

    String aaa = "";
    String bbb = "";

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
//        MaterialApplication ma = (MaterialApplication)getActivity().getApplication();
//        aaa = ma.getextLine();
//        bbb = ma.getextSize();

//        Log.e("aaa",aaa+"aono");
//        Log.e("bbb",bbb+"aono");
        if(aaa == "" || aaa.isEmpty())
        {
            tvInfo.setLineSpacing(1,1.5f);
        }
        else
        {
            tvInfo.setLineSpacing(1,Float.parseFloat(aaa));
        }
        if(bbb == ""|| bbb.isEmpty())
        {
            tvInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
        }
        else
        {
            tvInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, Integer.parseInt(bbb));
        }

        //tvInfo.setLineSpacing(1,1.5f);
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
